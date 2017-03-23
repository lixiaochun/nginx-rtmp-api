package live.livelab.nginx.api.service.messaging.rtmpApplicationService;


import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.entity.RTMPApplication;

import java.util.List;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPApplicationListResponse extends ResponseBase {
    private List<RTMPApplication> entityList;

    public GetRTMPApplicationListResponse(boolean success, String message, List<RTMPApplication> entityList) {
        super(success, message);
        this.entityList = entityList;
    }

    public GetRTMPApplicationListResponse(boolean success, String message) {
        super(success, message);
    }

    public List<RTMPApplication> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<RTMPApplication> entityList) {
        this.entityList = entityList;
    }
}
