package live.livelab.nginx.api.service.messaging.rtmpServerService;

/**
 * Created by kevin on 17/1/30.
 */
public enum RTMPServerStatisticsXmlSource {
    Cache(0),
    RPC(1);

    private int value;

    RTMPServerStatisticsXmlSource(int value) {
        this.value = value;
    }
}
