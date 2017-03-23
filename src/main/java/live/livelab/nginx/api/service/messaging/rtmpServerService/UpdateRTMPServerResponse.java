package live.livelab.nginx.api.service.messaging.rtmpServerService;


import live.livelab.infrastructure.messaging.ResponseBase;

/**
 * Created by kevin on 17/1/30.
 */
public class UpdateRTMPServerResponse extends ResponseBase {
    public UpdateRTMPServerResponse(boolean success, String message) {
        super(success, message);
    }
}
