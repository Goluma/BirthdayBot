package com.birthdaybot.mapper;

import com.birthdaybot.domain.entitiy.ReminderEntity;

import java.util.Map;

public class ReminderMapperImpl{

    public ReminderEntity mapToReminderEntity(Map<Integer, String> userHashMap) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthdayPerson(userHashMap.get(1));
        reminderEntity.setBirthdayPersonNickname(userHashMap.getOrDefault(2, " "));
        reminderEntity.setBirthday(userHashMap.get(3));
        return reminderEntity;
    }
}
