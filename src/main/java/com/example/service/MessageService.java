package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Message createMessage(Message newMessage) {
        if (newMessage.getMessageText().length() == 0) {
            return null;
        }
        if (newMessage.getMessageText().length() > 255) {
            return null;
        }

        Account messageAccount = this.accountRepository.getById(newMessage.getPostedBy());
        if (messageAccount == null) {
            return null;
        }
        return messageRepository.save(newMessage);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message getMessage(int messageId) {
        return this.messageRepository.getById(messageId);
    }

    public Message deleteMessage(int messageId) {
        Message deletedMessage = messageRepository.getById(messageId);
        if (deletedMessage != null) {
            this.messageRepository.deleteById(messageId);
        }
        return deletedMessage;
    }

    public Message updateMessage(int messageId, Message newMessage) {
        String newMessageText = newMessage.getMessageText();
        if (newMessageText.length() == 0) {
            return null;
        }
        if (newMessageText.length() > 255) {
            return null;
        }

        newMessage.setMessageId(messageId);
        messageRepository.save(newMessage);
        return messageRepository.getById(messageId);
    }

    public List<Message> getMessageByAccount(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
