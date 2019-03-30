package com.apalon.eventPubSub.api;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bipinkumar.shetty on 3/26/2019.
 */
public class MobileEvent extends ApplicationEvent {


    public enum Event {
        session_start ("session_start"),
        iap_purchase( "iap_purchase"),
        ads_click ("ads_click"),
        session_end ("session_end");
        private  String eventName;
        Event(String eventName){
            this.eventName=eventName;
        }
        public String getEventName() {
            return eventName;
        }
    }

    private Event eventName;
    private String mobileIMEI_id;

    private String userId;

    public MobileEvent(Object source, Event message,String mobileIMEI_id, String userId) {
        super(source);
        this.eventName = message;
        this.mobileIMEI_id=mobileIMEI_id;
        this.userId=userId;

    }

    public Event getEvent() {
        return eventName;
    }
    public String getMobileIMEI_id() {
        return mobileIMEI_id;
    }
    public void setMobileIMEI_id(String mobileIMEI_id) {
        this.mobileIMEI_id = mobileIMEI_id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
