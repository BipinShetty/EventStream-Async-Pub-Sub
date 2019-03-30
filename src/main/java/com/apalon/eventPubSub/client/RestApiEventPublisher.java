package com.apalon.eventPubSub.client;

import com.apalon.eventPubSub.api.EventDTO;
import com.apalon.eventPubSub.api.EventDTOList;
import com.apalon.eventPubSub.api.MobileEvent;
import com.apalon.eventPubSub.server.EventConsumerThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */

@RestController
public class RestApiEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    static BlockingQueue<EventDTO> queue = new ArrayBlockingQueue<EventDTO>(1024);

    @PostConstruct
    public void init() {
        EventProducerThread producer = new EventProducerThread(queue);
        new Thread(producer).start();
    }

    @RequestMapping(value="/publish/event/stream",method = RequestMethod.POST)
    public void publishEvent(@RequestBody EventDTOList dtoList){
        try {


            dtoList.getEventDTOList().stream().forEach(e -> {
            MobileEvent mobileEvent = new MobileEvent(this, MobileEvent.Event.valueOf(e.getEventName()),e.getMobileIMEI_id(),e.getUserId());
            applicationEventPublisher.publishEvent(mobileEvent);}
            );


        }catch (Exception e){
            System.out.println("Event name is invalid , Valid event names are :");
            Arrays.asList(MobileEvent.Event.values())
                    .forEach(event -> System.out.println(event+","));
            System.out.println("===Sample post Json request===");
            System.out.println("{\n" +
                    "   \"eventDTOList\":[\n" +
                    "   \t\n" +
                    "   \t{\"userId\":\"Jonathan\",\n" +
                    "\t\"eventName\" :\"session_start\",\n" +
                    "\t\"mobileIMEI_id\":\"2323e32sdad\"}]\n" +
                    "}");

        }
    }

}
