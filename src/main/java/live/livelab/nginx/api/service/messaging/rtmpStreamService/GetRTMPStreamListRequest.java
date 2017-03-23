package live.livelab.nginx.api.service.messaging.rtmpStreamService;


import live.livelab.infrastructure.messaging.RequestBase;

import java.util.List;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPStreamListRequest extends RequestBase {
    private String applicationName;
    private List<String> streamNameList;

    public GetRTMPStreamListRequest(String applicationName, List<String> streamNameList) {
        this.applicationName = applicationName;
        this.streamNameList = streamNameList;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public List<String> getStreamNameList() {
        return streamNameList;
    }

    public void setStreamNameList(List<String> streamNameList) {
        this.streamNameList = streamNameList;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}
