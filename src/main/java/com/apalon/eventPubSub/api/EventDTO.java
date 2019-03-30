package com.apalon.eventPubSub.api;

import java.util.List;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */

public class EventDTO {
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


}
