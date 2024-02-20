package com.birthdaybot.bot.service;

import com.birthdaybot.domain.entitiy.ReminderEntity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

public interface BusinessService {

    void deleteUser(User user);

    String deleteReminder(User user, Message msg);

    void addReminder(User user, Message msg, int flagToAdd);

    void addUser(User user, Message msg);

    String createStringOfReminders(User user, List<ReminderEntity> listOfReminders);
}
