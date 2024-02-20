package com.birthdaybot.repository;

import com.birthdaybot.TestDataUtil;
import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "scheduler.enabled=false")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReminderRepositoryIntegrationTests {

    private ReminderRepository underTest;

    @Autowired
    public ReminderRepositoryIntegrationTests(ReminderRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatReminderCanBeCreatedAndRecalled(){
        ReminderEntity reminderEntity = TestDataUtil.createReminderEntityA();
        underTest.save(reminderEntity);

        Optional<ReminderEntity> savedReminder = underTest.findById(reminderEntity.getNoticeUuid());

        assertThat(savedReminder).isPresent();
        assertThat(savedReminder.get()).isEqualTo(reminderEntity);
    }

    @Test
    public void testThatReminderCanBeUpdated(){
        ReminderEntity reminderEntity = TestDataUtil.createReminderEntityA();
        underTest.save(reminderEntity);
        reminderEntity.setBirthdayPersonNickname("UPDATED");
        underTest.save(reminderEntity);

        Optional<ReminderEntity> savedReminder = underTest.findById(reminderEntity.getNoticeUuid());

        assertThat(savedReminder).isPresent();
        assertThat(savedReminder.get()).isEqualTo(reminderEntity);
    }

    @Test
    public void testThatMultiplyRemindersCanBeFind(){
        ReminderEntity reminderEntityA = TestDataUtil.createReminderEntityA();
        underTest.save(reminderEntityA);
        ReminderEntity reminderEntityB = TestDataUtil.createReminderEntityB();
        underTest.save(reminderEntityB);
        ReminderEntity reminderEntityC = TestDataUtil.createReminderEntityC();
        underTest.save(reminderEntityC);

        Iterable<ReminderEntity> listOfReminders = underTest.findAll();

        assertThat(listOfReminders)
                .hasSize(3)
                .containsExactly(reminderEntityA, reminderEntityB, reminderEntityC);
    }

    @Test
    public void testThatReminderCanBeDeleted(){
        ReminderEntity reminderEntityA = TestDataUtil.createReminderEntityA();
        underTest.save(reminderEntityA);

        underTest.deleteById(reminderEntityA.getNoticeUuid());

        Optional<ReminderEntity> deletedReminder = underTest.findById(reminderEntityA.getNoticeUuid());

        assertThat(deletedReminder).isEmpty();
    }

    @Test
    public void testThatListOfTodayRemindersCanBeFind(){
        ReminderEntity reminderEntityA = TestDataUtil.createReminderEntityA();
        underTest.save(reminderEntityA);
        ReminderEntity reminderEntityB = TestDataUtil.createReminderEntityB();
        underTest.save(reminderEntityB);
        ReminderEntity reminderEntityC = TestDataUtil.createReminderEntityC();
        underTest.save(reminderEntityC);

        List<AllTodayRemindersDto> todayRemindersDtoList = underTest.findAllTodayReminders();

        assertThat(todayRemindersDtoList).hasSize(3);
    }
}
