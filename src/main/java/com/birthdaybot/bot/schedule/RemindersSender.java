package com.birthdaybot.bot.schedule;

import com.birthdaybot.domain.entities.ReminderEntity;

import java.util.List;

public interface RemindersSender {

    void sendReminders(List<Object[]> reminderEntityList);
}
