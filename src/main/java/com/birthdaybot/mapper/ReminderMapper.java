package com.birthdaybot.mapper;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ReminderMapper {

    public ReminderEntity mapToReminderEntity(Map<Integer, String> userHashMap) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setBirthdayPerson(userHashMap.get(1));
        reminderEntity.setBirthdayPersonNickname(userHashMap.getOrDefault(2, " "));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(userHashMap.get(3), dtf);
        reminderEntity.setBirthday(date);
        return reminderEntity;
    }
}
