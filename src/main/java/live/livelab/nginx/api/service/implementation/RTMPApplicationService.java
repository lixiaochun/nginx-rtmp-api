package live.livelab.nginx.api.service.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.RTMPApplication;
import live.livelab.nginx.api.entity.RTMPServer;
import live.livelab.nginx.api.service.interfaces.IRTMPApplicationService;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationListRequest;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationListResponse;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationRequest;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationResponse;
import live.livelab.nginx.api.service.messaging.rtmpServerService.GetRTMPServerRequest;
import live.livelab.nginx.api.service.messaging.rtmpServerService.GetRTMPServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin on 17/1/30.
 */
public class RTMPApplicationService implements IRTMPApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(RTMPApplicationService.class);

    private final IRTMPServerService rtmpServerService;

    @Inject
    public RTMPApplicationService(IRTMPServerService rtmpServerService) {
        this.rtmpServerService = rtmpServerService;
    }

    @Override
    public GetRTMPApplicationResponse getRTMPApplication(GetRTMPApplicationRequest request) {
        if (request == null) {
            return new GetRTMPApplicationResponse(false, "illegal request object");
        }
        String applicationName = request.getName();
        if (StringUtils.isEmpty(applicationName)) {
            return new GetRTMPApplicationResponse(false, "illegal request parameter: application name");
        }
        boolean success = true;
        String message = "ok";
        RTMPApplication entity = null;
        GetRTMPServerResponse getRTMPServerResponse = this.rtmpServerService.getRTMPServer(new GetRTMPServerRequest());
        RTMPServer rtmpServer = getRTMPServerResponse != null ? getRTMPServerResponse.getEntity() : null;
        if (rtmpServer == null) {
            message = "An error occurred while retrieving rtmp server";
            logger.error(message);
            return new GetRTMPApplicationResponse(false, message);
        }
        entity = rtmpServer.getApplicationList() == null ? null :
                rtmpServer.getApplicationList()
                        .stream().filter(i -> i.getName() != null && i.getName().equals(applicationName))
                        .findFirst().orElse(null);
        return new GetRTMPApplicationResponse(success, message, entity);
    }

    @Override
    public GetRTMPApplicationListResponse getRTMPApplicationList(GetRTMPApplicationListRequest request) {
        return null;
    }
}
