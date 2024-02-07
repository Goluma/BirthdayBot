package com.birthdaybot.repository;

import com.birthdaybot.TestDataUtil;
import com.birthdaybot.domain.entitiy.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "scheduler.enabled=false")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTests {

    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTests(UserRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserEntityA();
        underTest.save(userEntity);

        Optional<UserEntity> savedReminder = underTest.findById(userEntity.getUuid());

        assertThat(savedReminder).isPresent();
        assertThat(savedReminder.get()).isEqualTo(userEntity);
    }

    @Test
    public void testThatUserCanBeDeleted(){
        UserEntity userEntity = TestDataUtil.createUserEntityA();
        underTest.save(userEntity);
        underTest.deleteById(userEntity.getUuid());

        Optional<UserEntity> savedReminder = underTest.findById(userEntity.getUuid());
        assertThat(savedReminder).isEmpty();
    }

    @Test
    public void testThatMultiplyUsersCanBeFind(){
        UserEntity userEntityA = TestDataUtil.createUserEntityA();
        underTest.save(userEntityA);
        UserEntity userEntityB = TestDataUtil.createUserEntityB();
        underTest.save(userEntityB);
        UserEntity userEntityC = TestDataUtil.createUserEntityC();
        underTest.save(userEntityC);

        Iterable<UserEntity> listOfUsers = underTest.findAll();

        assertThat(listOfUsers)
                .hasSize(3)
                .containsExactly(userEntityA, userEntityB, userEntityC);
    }

    @Test
    public void testThatUserCanBeUpdated(){
        UserEntity userEntityA = TestDataUtil.createUserEntityA();
        underTest.save(userEntityA);
        userEntityA.setFirstName("UPDATED");

        underTest.save(userEntityA);

        Optional<UserEntity> savedUser = underTest.findById(userEntityA.getUuid());

        assertThat(savedUser).isPresent();
        assertThat(savedUser.get()).isEqualTo(userEntityA);
    }

    @Test
    public void testThatUserCanBeFoundByUserId(){
        UserEntity userEntity = TestDataUtil.createUserEntityA();
        underTest.save(userEntity);

        Optional<UserEntity> savedReminder = underTest.findByUserId(userEntity.getUserId());

        assertThat(savedReminder).isPresent();
        assertThat(savedReminder.get()).isEqualTo(userEntity);
    }

    @Test
    public void testThatUserCanBeDeletedByUserId(){
        UserEntity userEntity = TestDataUtil.createUserEntityA();
        underTest.save(userEntity);
        underTest.deleteByUserId(userEntity.getUserId());

        Optional<UserEntity> savedReminder = underTest.findById(userEntity.getUuid());
        assertThat(savedReminder).isEmpty();
    }
}
