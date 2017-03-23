package live.livelab.nginx.api.service.messaging.rtmpServerService;


import live.livelab.infrastructure.messaging.ResponseBase;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPServerStatisticsXmlResponse extends ResponseBase {
    private String statisticsXml;

    public GetRTMPServerStatisticsXmlResponse(boolean success, String message, String statisticsXml) {
        super(success, message);
        this.statisticsXml = statisticsXml;
    }

    public GetRTMPServerStatisticsXmlResponse(boolean success, String message) {
        super(success, message);
    }

    public String getStatisticsXml() {
        return statisticsXml;
    }

    public void setStatisticsXml(String statisticsXml) {
        this.statisticsXml = statisticsXml;
    }
}
