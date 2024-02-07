package com.birthdaybot.config;

import com.birthdaybot.mappers.ReminderMapper;
import com.birthdaybot.mappers.UserMapper;
import com.birthdaybot.mappers.impl.ReminderMapperImpl;
import com.birthdaybot.mappers.impl.UserMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public UserMapper userMapper(){
        return new UserMapperImpl();
    }

    @Bean
    public ReminderMapper reminderMapper(){
        return new ReminderMapperImpl();
    }
}
