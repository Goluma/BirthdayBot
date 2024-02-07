package com.birthdaybot.mappers.impl;

import com.birthdaybot.domain.entities.ReminderEntity;
import com.birthdaybot.mappers.ReminderMapper;

import java.util.Map;

public class ReminderMapperImpl implements ReminderMapper {
    @Override
    public ReminderEntity mapToReminderEntity(Map<Integer, String> userHashMap) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthdayPerson(userHashMap.get(1));
        reminderEntity.setBirthdayPersonNickname(userHashMap.getOrDefault(2, " "));
        reminderEntity.setBirthday(userHashMap.get(3));
        return reminderEntity;
    }
}
