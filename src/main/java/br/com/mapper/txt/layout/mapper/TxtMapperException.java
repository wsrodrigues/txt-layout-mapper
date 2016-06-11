/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wagner
 */
public class TxtMapperException extends Exception {
    private List<Notification> notifications;
    
    private int errorCount;
    private int warningCount;
    
    public TxtMapperException() {
        notifications = new ArrayList<>();
        this.errorCount = 0;
        this.warningCount = 0;
    }

    TxtMapperException(String msg, Notification.Type type) {
        notifications = new ArrayList<>();
        this.notifications.add(new Notification(msg, type));
    }
    
    public void addNotification(String msg, Notification.Type type){
        this.notifications.add(new Notification(msg, type));
        
        if (type.equals(Notification.Type.ERROR))
            errorCount++;
        else if (type.equals(Notification.Type.WARNING))
            warningCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        if (this.notifications.isEmpty())
            return "There is no notifications";
        
        StringBuilder strBuilder = new StringBuilder();
        this.notifications.forEach((notificacao) -> {
            strBuilder.append(notificacao).append("\n");
        });
        
        return strBuilder.toString();
    }
    
    public static class Notification {
        private String msg;
        private Type type;

        public Notification(String msg, Type type) {
            this.msg = msg;
            this.type = type;
        }

        @Override
        public String toString() {
            return "{type=" + type + ", msg=" + msg + '}';
        }
        
        public enum Type {
            ERROR,
            WARNING;
        }
    }
    
}
