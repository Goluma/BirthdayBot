package com.birthdaybot.bot.handlers;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

public interface CommandHandler {


    void addReminder(User user, Message msg, int flagToAdd);

    String deleteReminder(User user, Message msg);

    void addUser(User user, Message msg);

    void deleteUser(User user);

    String showReminders(User user, List<ReminderEntity> listOfReminders);
}
