package live.livelab.nginx.api.service.messaging.rtmpServerService;


import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.entity.RTMPServer;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPServerResponse extends ResponseBase {
    private RTMPServer entity;

    public GetRTMPServerResponse(boolean success, String message, RTMPServer entity) {
        super(success, message);
        this.entity = entity;
    }

    public GetRTMPServerResponse(boolean success, String message) {
        super(success, message);
    }

    public RTMPServer getEntity() {
        return entity;
    }

    public void setEntity(RTMPServer entity) {
        this.entity = entity;
    }
}
