package live.livelab.nginx.api.service.interfaces;


import live.livelab.nginx.api.service.messaging.rtmpServerService.*;

/**
 * Created by kevin on 17/1/30.
 */
public interface IRTMPServerService {
    GetRTMPServerResponse getRTMPServer(GetRTMPServerRequest request);

    GetRTMPServerStatisticsXmlResponse getRTMPServerStatisticsXml(GetRTMPServerStatisticsXmlRequest request);

    UpdateRTMPServerResponse updateRTMPServer(UpdateRTMPServerRequest request);
}
