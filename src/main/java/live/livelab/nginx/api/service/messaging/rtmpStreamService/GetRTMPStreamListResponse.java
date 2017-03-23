package live.livelab.nginx.api.service.messaging.rtmpStreamService;


import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.entity.RTMPStream;

import java.util.List;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPStreamListResponse extends ResponseBase {
    private List<RTMPStream> entityList;

    public GetRTMPStreamListResponse(boolean success, String message, List<RTMPStream> entityList) {
        super(success, message);
        this.entityList = entityList;
    }

    public GetRTMPStreamListResponse(boolean success, String message) {
        super(success, message);
    }

    public List<RTMPStream> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<RTMPStream> entityList) {
        this.entityList = entityList;
    }
}
