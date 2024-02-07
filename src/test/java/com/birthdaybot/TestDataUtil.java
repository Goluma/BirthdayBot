package com.birthdaybot;

import com.birthdaybot.domain.entities.ReminderEntity;
import com.birthdaybot.domain.entities.UserEntity;

import java.util.ArrayList;


public final class TestDataUtil {

    private TestDataUtil(){}

    public static UserEntity createUserEntityA(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(12345L);
        userEntity.setFirstName("Евгений");
        userEntity.setLastName("Алдонин");
        userEntity.setListOfReminders(new ArrayList<>());
        return userEntity;
    }

    public static UserEntity createUserEntityB(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1234L);
        userEntity.setFirstName("Евгений");
        userEntity.setLastName("Леонов");
        userEntity.setListOfReminders(new ArrayList<>());
        return userEntity;
    }

    public static UserEntity createUserEntityC(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(123L);
        userEntity.setFirstName("Евгений");
        userEntity.setLastName("Гинер");
        return userEntity;
    }

    public static ReminderEntity createReminderEntityA(){
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setNoticeId(1L);
        reminderEntity.setBirthday("19.6");
        reminderEntity.setBirthdayPersonNickname("");
        reminderEntity.setBirthdayPerson("Роман Бабаев");
        return reminderEntity;
    }

    public static ReminderEntity createReminderEntityB(){
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setNoticeId(2L);
        reminderEntity.setBirthday("19.6");
        reminderEntity.setBirthdayPersonNickname("");
        reminderEntity.setBirthdayPerson("Игорь Акинфеев");
        return reminderEntity;
    }

    public static ReminderEntity createReminderEntityC(){
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setNoticeId(3L);
        reminderEntity.setBirthday("19.6");
        reminderEntity.setBirthdayPersonNickname("");
        reminderEntity.setBirthdayPerson("Виктор Давила");
        return reminderEntity;
    }
}
