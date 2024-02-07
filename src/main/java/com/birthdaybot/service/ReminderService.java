package com.birthdaybot.service;

import com.birthdaybot.domain.entities.ReminderEntity;


import java.util.List;
import java.util.Optional;

public interface ReminderService {
    ReminderEntity saveReminder(ReminderEntity reminderEntity);

    void deleteReminder(Long noticeId);

    void deleteAllReminders(List<ReminderEntity> listOfReminders);

    List<ReminderEntity> findAllReminders(String str);

    List<Object[]> findAllRemindersWithUsersId(String date);
}
