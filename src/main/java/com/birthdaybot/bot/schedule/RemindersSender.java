package com.birthdaybot.bot.schedule;

import java.util.List;

public interface RemindersSender {

    void sendReminders(List<Object[]> reminderEntityList);
}
