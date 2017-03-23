package live.livelab.nginx.api.scheduledTask;

import live.livelab.nginx.api.dependency.DependencyResolver;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.messaging.rtmpServerService.UpdateRTMPServerRequest;
import live.livelab.nginx.api.service.messaging.rtmpServerService.UpdateRTMPServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * Created by kevin on 17/1/30.
 */
@Component
public class RTMPServerUpdater {

    private static final Logger logger = LoggerFactory.getLogger(RTMPServerUpdater.class);
    private static final int updateInterval = 1000 * 2;

    @Scheduled(fixedRate = updateInterval)
    public void updateRTMPServerCache() {
        String message = "Starting to update rtmp server cache at " + LocalDateTime.now().toString();
        logger.info(message);
        UpdateRTMPServerRequest request = new UpdateRTMPServerRequest();
        IRTMPServerService service = DependencyResolver.getInstance(IRTMPServerService.class);
        if (service != null) {
            UpdateRTMPServerResponse response = service.updateRTMPServer(request);
            logger.info(MessageFormat.format("finish updating rtmp server cache at {0}, result: {1}",
                    LocalDateTime.now().toString(),
                    response != null && response.isSuccess())
            );
        }
    }
}
