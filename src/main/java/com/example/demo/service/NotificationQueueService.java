package com.example.demo.service;

import com.example.demo.model.NotificationQueueItem;
import com.example.demo.model.NotificationTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Service
public class NotificationQueueService {
    private Queue<NotificationQueueItem> queue = new LinkedList<>();

    public void addToQueue(NotificationTemplate notificationTemplate, String email, Map<String, String> placeholders,String ForWhat) {
        NotificationQueueItem item = new NotificationQueueItem(email, notificationTemplate, placeholders, ForWhat);
        queue.offer(item);
    }

    public NotificationQueueItem dequeue() {
        return queue.poll();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
