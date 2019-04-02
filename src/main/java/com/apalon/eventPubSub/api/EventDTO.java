package com.apalon.eventPubSub.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */

public class EventDTO {
    UUID eventId;
    LocalDateTime dateTime;
    String userId;
    String mobileIMEI_id;
    String app_version;
    String eventName;



    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileIMEI_id() {
        return mobileIMEI_id;
    }

    public void setMobileIMEI_id(String mobileIMEI_id) {
        this.mobileIMEI_id = mobileIMEI_id;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }


    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }



}
