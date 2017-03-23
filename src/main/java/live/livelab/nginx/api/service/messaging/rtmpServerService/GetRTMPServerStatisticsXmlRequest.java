package live.livelab.nginx.api.service.messaging.rtmpServerService;


import live.livelab.infrastructure.messaging.RequestBase;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPServerStatisticsXmlRequest extends RequestBase {
    private RTMPServerStatisticsXmlSource source;

    public RTMPServerStatisticsXmlSource getSource() {
        return source;
    }

    public void setSource(RTMPServerStatisticsXmlSource source) {
        this.source = source;
    }

    public GetRTMPServerStatisticsXmlRequest(RTMPServerStatisticsXmlSource source) {

        this.source = source;
    }
}
