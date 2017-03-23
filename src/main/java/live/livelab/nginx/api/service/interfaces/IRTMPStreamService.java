package live.livelab.nginx.api.service.interfaces;


import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamListRequest;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamListResponse;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamRequest;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamResponse;

/**
 * Created by kevin on 17/1/30.
 */
public interface IRTMPStreamService {
    GetRTMPStreamResponse getRTMPStream(GetRTMPStreamRequest request);

    GetRTMPStreamListResponse getRTMPStreamList(GetRTMPStreamListRequest request);
}
