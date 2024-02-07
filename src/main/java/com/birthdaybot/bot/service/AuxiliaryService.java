package com.birthdaybot.bot.service;

import com.birthdaybot.domain.entities.ReminderEntity;

import java.util.List;

public interface AuxiliaryService {

    void setListOfTodayReminders();

    void deleteRemindersFromTheList(Long id);

    List<ReminderEntity> getUsersReminders(Long userId);
}
