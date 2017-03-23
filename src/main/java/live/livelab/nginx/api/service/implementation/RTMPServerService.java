package live.livelab.nginx.api.service.implementation;

import com.google.inject.Inject;
import live.livelab.infrastructure.exception.RepositoryException;
import live.livelab.nginx.api.entity.IRTMPServerRepository;
import live.livelab.nginx.api.entity.RTMPServer;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.messaging.rtmpServerService.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin on 17/1/30.
 */
public class RTMPServerService implements IRTMPServerService {

    private static final Logger logger = LoggerFactory.getLogger(RTMPServerService.class);

    private final IRTMPServerRepository repository;

    @Inject
    public RTMPServerService(IRTMPServerRepository repository) {
        this.repository = repository;
    }


    @Override
    public GetRTMPServerResponse getRTMPServer(GetRTMPServerRequest request) {
        if (request == null) {
            return new GetRTMPServerResponse(false, "illegal request object");
        }
        boolean success = true;
        String message = "ok";
        RTMPServer entity = null;
        try {
            entity = this.repository.load();
        } catch (RepositoryException e) {
            message = "An error occurred while loading rtmp server entity";
            success = false;
            logger.error(message, e);
        }
        return new GetRTMPServerResponse(success, message, entity);
    }

    @Override
    public GetRTMPServerStatisticsXmlResponse getRTMPServerStatisticsXml(GetRTMPServerStatisticsXmlRequest request) {
        if (request == null) {
            return new GetRTMPServerStatisticsXmlResponse(false, "illegal request object");
        }
        RTMPServerStatisticsXmlSource source = request.getSource();
        if (source == null) {
            return new GetRTMPServerStatisticsXmlResponse(false, "illegal request parameter: source");
        }
        boolean success = true;
        String message = "ok";
        String xml = null;
        try {
            xml = source == RTMPServerStatisticsXmlSource.Cache ? this.repository.loadStatisticsXml()
                    : this.repository.loadStatisticsXmlFromRPC();
        } catch (RepositoryException e) {
            message = "An error occurred while retrieving rtmp server statistics xml";
            success = false;
            logger.error(message, e);
        }
        return new GetRTMPServerStatisticsXmlResponse(success, message, xml);
    }

    @Override
    public UpdateRTMPServerResponse updateRTMPServer(UpdateRTMPServerRequest request) {
        if (request == null) {
            return new UpdateRTMPServerResponse(false, "illegal request object");
        }
        boolean success = true;
        String message = "ok";
        try {
            this.repository.refreshCache();
        } catch (RepositoryException e) {
            message = "An error occurred while refreshing rtmp  server cache";
            success = false;
            logger.error(message, e);
        }
        return new UpdateRTMPServerResponse(success, message);
    }
}
