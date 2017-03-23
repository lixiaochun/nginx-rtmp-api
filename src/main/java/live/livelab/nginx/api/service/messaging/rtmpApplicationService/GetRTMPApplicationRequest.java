package live.livelab.nginx.api.service.messaging.rtmpApplicationService;


import live.livelab.infrastructure.messaging.RequestBase;

/**
 * Created by kevin on 17/1/30.
 */
public class GetRTMPApplicationRequest extends RequestBase {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetRTMPApplicationRequest(String name) {

        this.name = name;
    }
}
