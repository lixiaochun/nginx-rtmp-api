package live.livelab.configuration;

/**
 * Created by kevin on 15/12/27.
 */
public interface IAppSettings {
    int getServerPort();
    String getNginxRTMPStatisticsXmlUrl();
    int getRTMPStatisticsInfoRefreshInterval();
}
