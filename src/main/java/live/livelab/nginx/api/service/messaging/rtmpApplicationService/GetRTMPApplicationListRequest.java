package live.livelab.nginx.api.service.messaging.rtmpApplicationService;


import live.livelab.infrastructure.messaging.RequestBase;

import java.util.List;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPApplicationListRequest extends RequestBase {
    private List<String> nameList;

    public GetRTMPApplicationListRequest(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
