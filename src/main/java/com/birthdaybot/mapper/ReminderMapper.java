package com.birthdaybot.mapper;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReminderMapper {

    public ReminderEntity mapToReminderEntity(Map<Integer, String> userHashMap) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthdayPerson(userHashMap.get(1));
        reminderEntity.setBirthdayPersonNickname(userHashMap.getOrDefault(2, " "));
        reminderEntity.setBirthday(userHashMap.get(3));
        return reminderEntity;
    }
}
