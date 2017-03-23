package live.livelab.nginx.api.service.messaging.rtmpStreamService;


import live.livelab.infrastructure.messaging.RequestBase;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPStreamRequest extends RequestBase {
    private String applicationName;
    private String streamName;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public GetRTMPStreamRequest(String applicationName, String streamName) {

        this.applicationName = applicationName;
        this.streamName = streamName;
    }
}
