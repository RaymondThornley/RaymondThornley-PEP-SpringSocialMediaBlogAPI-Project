package com.example.service;

import java.util.List;
import java.util.Optional;

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

        Optional<Account> messageAccount = this.accountRepository.findById(newMessage.getPostedBy());
        if (messageAccount.isEmpty()) {
            return null;
        }
        return messageRepository.save(newMessage);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message getMessage(int messageId) {
        Optional<Message> message = this.messageRepository.findById(messageId);
        if (message.isEmpty()) {
            return null;
        }
        return message.get();
    }

    public Integer deleteMessage(int messageId) {
        Optional<Message> deletedMessage = messageRepository.findById(messageId);
        if (deletedMessage.isEmpty()) {
            return null;
        }
        this.messageRepository.deleteById(messageId);
        return 1;
    }

    public Integer updateMessage(int messageId, Message newMessage) {
        String newMessageText = newMessage.getMessageText();
        if (newMessageText.length() == 0) {
            return null;
        }
        if (newMessageText.length() > 255) {
            return null;
        }
        Optional<Message> oldMessageOptional = messageRepository.findById(messageId);
        if (oldMessageOptional.isEmpty()) {
            return null;
        }
        Message oldMessage = oldMessageOptional.get();

        oldMessage.setMessageText(newMessageText);
        messageRepository.save(oldMessage);
        return 1;
    }

    public List<Message> getMessageByAccount(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
