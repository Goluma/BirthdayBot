package com.birthdaybot.service;

import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;


import java.util.List;
import java.util.UUID;

public interface ReminderService {
    ReminderEntity saveReminder(ReminderEntity reminderEntity);

    void deleteReminder(UUID noticeUuid);

    void deleteAllReminders(List<ReminderEntity> listOfReminders);

    List<AllTodayRemindersDto> findAllTodayReminders();
}
