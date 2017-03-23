package live.livelab.nginx.api.service.messaging.rtmpApplicationService;


import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.entity.RTMPApplication;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPApplicationResponse extends ResponseBase {
    private RTMPApplication entity;

    public GetRTMPApplicationResponse(boolean success, String message, RTMPApplication entity) {
        super(success, message);
        this.entity = entity;
    }

    public GetRTMPApplicationResponse(boolean success, String message) {
        super(success, message);
    }

    public RTMPApplication getEntity() {
        return entity;
    }

    public void setEntity(RTMPApplication entity) {
        this.entity = entity;
    }
}
