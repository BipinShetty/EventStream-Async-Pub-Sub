package com.apalon.eventPubSub.server;

import com.apalon.eventPubSub.api.EventDTO;
import com.apalon.eventPubSub.api.IProcessEvents;
import com.apalon.eventPubSub.server.EventConsumerThread;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by bipinkumar.shetty on 3/27/2019.
 */
@Service
public class ProcessEventsImpl implements IProcessEvents {
    static BlockingQueue<EventDTO> queue = new ArrayBlockingQueue<EventDTO>(1024);

    @PostConstruct
    public void init() {
        EventConsumerThread consumer = new EventConsumerThread(queue);
        new Thread(consumer).start();
    }

    public void processAEvents(EventDTO eventDTO){
        try {
            queue.put(eventDTO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
