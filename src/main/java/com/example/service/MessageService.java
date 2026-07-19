package com.example.service;

import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;

public class MessageService {

    public Message createMessage(Message newMessage) {
        if (newMessage.getMessageText().length() == 0) {
            return null;
        }
        if (newMessage.getMessageText().length() > 255) {
            return null;
        }

        Account messageAccount = this.accountDAO.getAccountById(newMessage.getPostedBy());
        if (messageAccount == null) {
            return null;
        }
        return messageDAO.insertMessage(newMessage);
    }

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getMessage(int messageId) {
        return this.messageDAO.getMessageById(messageId);
    }

    public Message deleteMessage(int messageId) {
        Message deletedMessage = messageDAO.getMessageById(messageId);
        if (deletedMessage != null) {
            this.messageDAO.deleteMessage(messageId);
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

        messageDAO.updateMessage(messageId, newMessageText);
        return messageDAO.getMessageById(messageId);
    }

    public List<Message> getMessageByAccount(int accountId) {
        return messageDAO.getMessageByAccount(accountId);
    }
}
