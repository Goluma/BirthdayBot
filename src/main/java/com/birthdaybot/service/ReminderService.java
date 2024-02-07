package com.birthdaybot.service;

import com.birthdaybot.domain.entitiy.ReminderEntity;


import java.util.List;

public interface ReminderService {
    ReminderEntity saveReminder(ReminderEntity reminderEntity);

    void deleteReminder(Long noticeId);

    void deleteAllReminders(List<ReminderEntity> listOfReminders);

    List<ReminderEntity> findAllReminders(String str);

    List<Object[]> findAllRemindersWithUsersId(String date);
}
