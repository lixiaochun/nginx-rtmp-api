package live.livelab.nginx.api.service.messaging.rtmpStreamService;


import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.entity.RTMPStream;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPStreamResponse extends ResponseBase {
    private RTMPStream entity;

    public GetRTMPStreamResponse(boolean success, String message, RTMPStream entity) {
        super(success, message);
        this.entity = entity;
    }

    public GetRTMPStreamResponse(boolean success, String message) {
        super(success, message);
    }

    public RTMPStream getEntity() {
        return entity;
    }

    public void setEntity(RTMPStream entity) {
        this.entity = entity;
    }
}
