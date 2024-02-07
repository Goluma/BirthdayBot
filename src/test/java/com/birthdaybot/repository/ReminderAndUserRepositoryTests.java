//package com.birthdaybot.repositories;
//
//import com.birthdaybot.TestDataUtil;
//import com.birthdaybot.domain.entities.ReminderEntity;
//import com.birthdaybot.domain.entities.UserEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@TestPropertySource(properties = "scheduler.enabled=false")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class ReminderAndUserRepositoryTests {
//
//    private UserRepository underTestU;
//
//    //private ReminderRepository underTestR;
//
//    @Autowired
//    public ReminderAndUserRepositoryTests(UserRepository underTestU/*, ReminderRepository underTestR*/){
//        this.underTestU = underTestU;
//        //this.underTestR = underTestR;
//    }
//
//    @Test
//    public void testThatCurrentRemindersWithUsersIdCanBeFind(){
//        UserEntity userEntityA = TestDataUtil.createUserEntityA();
//        underTestU.save(userEntityA);
//        UserEntity userEntityB = TestDataUtil.createUserEntityB();
//        underTestU.save(userEntityB);
//
////        ReminderEntity reminderEntityA = TestDataUtil.createReminderEntityA();
////        underTestR.save(reminderEntityA);
////        ReminderEntity reminderEntityB = TestDataUtil.createReminderEntityB();
////        underTestR.save(reminderEntityB);
////        ReminderEntity reminderEntityC = TestDataUtil.createReminderEntityC();
////        underTestR.save(reminderEntityC);
////
////        userEntityA.getListOfReminders().add(reminderEntityA);
////        userEntityA.getListOfReminders().add(reminderEntityB);
////        userEntityB.getListOfReminders().add(reminderEntityC);
////        underTestU.save(userEntityA);
////        underTestU.save(userEntityB);
//
////        List<Object[]> listOfReminders = underTestR.findAllRemindersWithUsersId("19.6");
////
////        assertThat(listOfReminders)
////                .hasSize(3);
//    }
//}
