package com.birthdaybot.bot.schedule;

import com.birthdaybot.bot.service.impl.AuxiliaryServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Schedule {

    private RemindersSender remindersSender;
    private AuxiliaryServiceImpl auxiliaryServiceImpl;


    public Schedule(RemindersSender remindersSender, AuxiliaryServiceImpl auxiliaryServiceImpl){
        this.remindersSender = remindersSender;
        this.auxiliaryServiceImpl = auxiliaryServiceImpl;
    }

    @Scheduled(cron = "${interval-in-cron}")
    public void sendRemindersFirst(){
        auxiliaryServiceImpl.setListOfTodayReminders();
        remindersSender.sendReminders(auxiliaryServiceImpl.getListOfTodayReminders());
    }

    @Scheduled(cron = "0 36 23 * * *")
    public void sendRemindersSecond(){
        remindersSender.sendReminders(auxiliaryServiceImpl.getListOfTodayReminders());
    }

    @Scheduled(cron = "0 37 23 * * *")
    public void sendRemindersThird(){
        remindersSender.sendReminders(auxiliaryServiceImpl.getListOfTodayReminders());
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void sendRemindersFourth(){
        remindersSender.sendReminders(auxiliaryServiceImpl.getListOfTodayReminders());
    }



}
