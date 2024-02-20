package com.birthdaybot;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.domain.entitiy.UserEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public final class TestDataUtil {

    private TestDataUtil(){}

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        reminderEntity.setBirthday(LocalDate.parse("2004-02-15", dtf));
        reminderEntity.setBirthdayPersonNickname("");
        reminderEntity.setBirthdayPerson("Роман Бабаев");
        return reminderEntity;
    }

    public static ReminderEntity createReminderEntityB(){
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthday(LocalDate.parse("2004-02-15", dtf));
        reminderEntity.setBirthdayPersonNickname("");
        reminderEntity.setBirthdayPerson("Игорь Акинфеев");
        return reminderEntity;
    }

    public static ReminderEntity createReminderEntityC(){
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthday(LocalDate.parse("2004-02-15", dtf));
        reminderEntity.setBirthdayPersonNickname("@goluma04");
        reminderEntity.setBirthdayPerson("Виктор Давила");
        return reminderEntity;
    }
}
