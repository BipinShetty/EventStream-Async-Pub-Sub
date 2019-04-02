package com.apalon.eventPubSub.client;

import com.apalon.eventPubSub.api.EventDTO;
import com.apalon.eventPubSub.api.MobileEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpMethod.*;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */
// Get events from the mobile apps or spring events and add to our producer queue thread
@Component
public class MobileEventListener implements ApplicationListener<MobileEvent> {
    @Override
    public void onApplicationEvent(MobileEvent event) {

        // Build EventDTO object and send across server via REST api
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventName(event.getEvent().getEventName());
        eventDTO.setMobileIMEI_id(event.getMobileIMEI_id());
        eventDTO.setUserId(event.getUserId());
        eventDTO.setDateTime(LocalDateTime.now());
        eventDTO.setEventId(UUID.randomUUID());

        // Add the mobile event to producer queue.
        EventProducerThread.add(eventDTO);

    }





}