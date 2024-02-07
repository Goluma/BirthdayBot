package com.birthdaybot.mappers;

import com.birthdaybot.domain.entities.ReminderEntity;

import java.util.Map;

public interface ReminderMapper {

    ReminderEntity mapToReminderEntity(Map<Integer, String> userHashMap);
}
