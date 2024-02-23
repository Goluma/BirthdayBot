package com.birthdaybot.service.impl;

import com.birthdaybot.domain.dto.AllTodayRemindersDto;
import com.birthdaybot.domain.entitiy.ReminderEntity;
import com.birthdaybot.repository.ReminderRepository;
import org.springframework.stereotype.Service;
import com.birthdaybot.service.ReminderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderServiceImpl(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    @Override
    public ReminderEntity saveReminder(ReminderEntity reminderEntity) {
        reminderRepository.save(reminderEntity);
        return reminderEntity;
    }

    @Override
    public void deleteReminder(UUID noticeUuid) {
        reminderRepository.deleteFromSelect(noticeUuid, LocalDateTime.now());
    }

    @Override
    public void deleteAllReminders(List<ReminderEntity> listOfReminders) {
        listOfReminders.stream().forEach(x ->
                reminderRepository.deleteFromSelect(
                        x.getNoticeUuid(),
                        LocalDateTime.now()));
    }

    @Override
    public List<AllTodayRemindersDto> findAllTodayReminders() {
        return reminderRepository.findAllTodayReminders();
    }
}
