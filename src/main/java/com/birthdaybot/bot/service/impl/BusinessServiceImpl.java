package com.birthdaybot.bot.service.impl;

import com.birthdaybot.bot.service.AuxiliaryService;
import com.birthdaybot.bot.service.BusinessService;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.domain.entitiy.UserEntity;
import com.birthdaybot.mapper.ReminderMapper;
import com.birthdaybot.mapper.UserMapper;
import com.birthdaybot.service.ReminderService;
import com.birthdaybot.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log
public class BusinessServiceImpl implements BusinessService {


    private final UserService userService;
    private final ReminderService reminderService;
    private final ReminderMapper reminderMapper;
    private final UserMapper userMapper;
    private Map<Integer, String> userHashMap;
    private final AuxiliaryService auxiliaryService;

    public BusinessServiceImpl(UserService userService,
                               ReminderService reminderService,
                               ReminderMapper reminderMapper,
                               UserMapper userMapper,
                               AuxiliaryService auxiliaryService){
        this.userService = userService;
        this.reminderService = reminderService;
        this.reminderMapper = reminderMapper;
        this.userMapper = userMapper;
        this.auxiliaryService = auxiliaryService;
        this.userHashMap = new HashMap<>();
    }

    @Override
    public void addReminder(User user, Message msg, int flagToAdd){
        if(flagToAdd == 1){
            userHashMap.put(1, msg.getText());

        } else if (flagToAdd == 2){

            if(!msg.getText().equals("Пропустить")){
                userHashMap.put(2, msg.getText());
            }
        } else if (flagToAdd == 3){
            userHashMap.put(3, msg.getText());
            ReminderEntity reminderEntity = reminderMapper.mapToReminderEntity(userHashMap);
            reminderService.saveReminder(reminderEntity);
            userService.addReminderToUser(reminderEntity, user.getId());
            log.info("Reminder created.");
            userHashMap.clear();
        }
    }

    @Override
    public void addUser(User user, Message msg) {
        if (userService.findUserById(user.getId()).isEmpty()){
            UserEntity mappedUser = userMapper.mapToUserEntity(user.getId(), user.getFirstName(), user.getLastName());
            userService.saveUser(mappedUser);
            log.info("User created.");
        }
    }

    @Override
    public String createStringOfReminders(User user, List<ReminderEntity> listOfReminders) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < listOfReminders.size(); i++){
            str.append(String.format("%s: %s %s %s\n", i,
                    listOfReminders.get(i).getBirthdayPerson(),
                    listOfReminders.get(i).getBirthdayPersonNickname(),
                    listOfReminders.get(i).getBirthday()));
        }
        log.info("String of reminders created.");
        return str.toString();
    }

    @Override
    public String deleteReminder(User user, Message msg){
        List<ReminderEntity> listOfReminders = auxiliaryService.getUsersReminders(user.getId());
        Integer i = Integer.parseInt(msg.getText());
        if(i >= listOfReminders.size()){
            return String.format("Число не должно превышать %s.", listOfReminders.size()-1);
        }
        ReminderEntity reminderEntityToDelete = listOfReminders.get(i);
        reminderService.deleteReminder(reminderEntityToDelete.getNoticeUuid());
        log.info("Reminder deleted.");
        return "Напоминание удалено.";
    }

    @Override
    public void deleteUser(User user) {
        List<ReminderEntity> listOfReminders = auxiliaryService.getUsersReminders(user.getId());
        if (listOfReminders.size() == 0){
            userService.delete(user.getId());
        } else {
            reminderService.deleteAllReminders(listOfReminders);
            userService.delete(user.getId());
        }
        log.info("User deleted.");
    }
}
