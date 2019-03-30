package com.apalon.eventPubSub.client;

import com.apalon.eventPubSub.api.EventDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.springframework.http.HttpMethod.POST;

/**
 * Created by bipinkumar.shetty on 3/29/2019.
 */
public class EventProducerThread  implements Runnable{

    // Re-try 3 times if the message doesnt reach server.
    static private int reTry_Count = 3;
    static public int queue_size=1024;
    static BlockingQueue<EventDTO > queue = new ArrayBlockingQueue<EventDTO>(queue_size);

    public EventProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }
    URI uri = getUri();

    public void run() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        HttpEntity<EventDTO> eventDTOHttpEntity = null;
        EventDTO eventDTO = null;
        while (true) {
            try {
                if(queue.size()>0) {
                    eventDTO = queue.poll();
                    eventDTOHttpEntity = new HttpEntity<>(eventDTO);

                    // Make a REST call to the server.
                    response = restTemplate.exchange(uri, POST, eventDTOHttpEntity, String.class);
                    // If the message is not accepted by server
                    // We re-try for 3 times.
                    if (HttpStatus.ACCEPTED != response.getStatusCode()) {
                        reTry(restTemplate, uri, eventDTO);
                    }
                }
            } catch (RestClientException exception) {
                // We re-try for 3 times in case of client exception or network failure.
                reTry(restTemplate, uri, eventDTO);
            }
        }
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public static void add(EventDTO eventDTO) {
        if(queue.size() < queue_size) {
            queue.add(eventDTO);
        }
    }

    private void reTry(RestTemplate restTemplate, URI uri, EventDTO eventDTO) {
        ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        while (reTry_Count > 0 && HttpStatus.CREATED != response.getStatusCode()) {
            response = restTemplate.postForObject(uri, eventDTO, ResponseEntity.class);
            reTry_Count--;
        }
    }
    private URI getUri() {
        final String baseUrl = "http://localhost:"+8080+"/events";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }
}