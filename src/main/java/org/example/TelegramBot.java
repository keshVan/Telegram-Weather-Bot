package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig = new BotConfig();

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Integer msgId = update.getMessage().getMessageId();
            Long chatId = update.getMessage().getChatId();

            CopyMessage cm = CopyMessage
                    .builder()
                    .fromChatId(chatId.toString())
                    .chatId(chatId.toString())
                    .messageId(msgId)
                    .build();

            try {
                execute(cm);
            }
            catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
