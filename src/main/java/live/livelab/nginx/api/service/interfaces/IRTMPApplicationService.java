package live.livelab.nginx.api.service.interfaces;


import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationListRequest;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationListResponse;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationRequest;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationResponse;

/**
 * Created by kevin on 17/1/30.
 */
public interface IRTMPApplicationService {
    GetRTMPApplicationResponse getRTMPApplication(GetRTMPApplicationRequest request);

    GetRTMPApplicationListResponse getRTMPApplicationList(GetRTMPApplicationListRequest request);
}
