package live.livelab.nginx.api.service.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.RTMPApplication;
import live.livelab.nginx.api.entity.RTMPServer;
import live.livelab.nginx.api.entity.RTMPStream;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.interfaces.IRTMPStreamService;
import live.livelab.nginx.api.service.messaging.rtmpServerService.GetRTMPServerRequest;
import live.livelab.nginx.api.service.messaging.rtmpServerService.GetRTMPServerResponse;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamListRequest;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamListResponse;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamRequest;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin on 17/1/30.
 */
public class RTMPStreamService implements IRTMPStreamService {
    private static final Logger logger = LoggerFactory.getLogger(RTMPStreamService.class);
    private final IRTMPServerService rtmpServerService;

    @Inject
    public RTMPStreamService(IRTMPServerService rtmpServerService) {
        this.rtmpServerService = rtmpServerService;
    }

    @Override
    public GetRTMPStreamResponse getRTMPStream(GetRTMPStreamRequest request) {
        if (request == null) {
            return new GetRTMPStreamResponse(false, "illegal request object");
        }
        String application = request.getApplicationName();
        String stream = request.getStreamName();
        if (StringUtils.isEmpty(application)) {
            return new GetRTMPStreamResponse(false, "illegal request parameter: application");
        }
        if (StringUtils.isEmpty(stream)) {
            return new GetRTMPStreamResponse(false, "illegal request parameter: stream");
        }
        boolean success = true;
        String message = "ok";
        RTMPStream entity = null;
        GetRTMPServerResponse getRTMPServerResponse = this.rtmpServerService.getRTMPServer(new GetRTMPServerRequest());
        RTMPServer rtmpServer = getRTMPServerResponse != null ? getRTMPServerResponse.getEntity() : null;
        if (rtmpServer == null) {
            message = "An error occurred while retrieving rtmp server";
            logger.error(message);
            return new GetRTMPStreamResponse(false, message);
        }
        RTMPApplication rtmpApplication = rtmpServer.getApplicationList() == null ? null :
                rtmpServer.getApplicationList()
                        .stream().filter(i -> i.getName() != null && i.getName().equals(application))
                        .findFirst().orElse(null);
        entity = rtmpApplication == null || rtmpApplication.getStreamList() == null ? null :
                rtmpApplication.getStreamList()
                        .stream().filter(i -> i.getName() != null && i.getName().equals(stream))
                        .findFirst().orElse(null);
        return new GetRTMPStreamResponse(success, message, entity);
    }

    @Override
    public GetRTMPStreamListResponse getRTMPStreamList(GetRTMPStreamListRequest request) {
        return null;
    }
}
