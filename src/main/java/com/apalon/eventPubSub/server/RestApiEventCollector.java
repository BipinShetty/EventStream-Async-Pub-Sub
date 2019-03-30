package com.apalon.eventPubSub.server;

import com.apalon.eventPubSub.api.EventDTO;
import com.apalon.eventPubSub.api.IProcessEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */

@RestController
public class RestApiEventCollector {

    @Autowired
    IProcessEvents processEvents;

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<String> createEmployee(@RequestBody EventDTO eventDTO )
    {
        try {
            processEvents.processAEvents(eventDTO);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }



}
