package com.apalon.eventPubSub.server;

import com.apalon.eventPubSub.api.EventDTO;

import java.util.concurrent.BlockingQueue;

/**
 * Created by bipinkumar.shetty on 3/27/2019.
 */
public class EventConsumerThread implements Runnable{

    protected BlockingQueue queue = null;

    public EventConsumerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {

            while(true) {
                EventDTO events = (EventDTO) queue.take();
                System.out.println("Consumer End : Events submited by user : (" + events.getUserId() + ")" + "\n" +
                        "Mobile MEI_id -> (" + events.getMobileIMEI_id() + ")" + "\n" +
                        "event name -> (" + events.getEventName()+")" + "\n" +
                        "event Id -> (" + events.getEventId()+")" + "\n" +
                        "event dateTime -> (" + events.getDateTime()+")" + "\n" +
                        "========================================");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
