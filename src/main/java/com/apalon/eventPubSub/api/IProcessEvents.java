package com.apalon.eventPubSub.api;

import com.apalon.eventPubSub.api.EventDTO;

/**
 * Created by bipinkumar.shetty on 3/27/2019.
 */
public interface IProcessEvents {
    void processAEvents(EventDTO eventDTO);
}
