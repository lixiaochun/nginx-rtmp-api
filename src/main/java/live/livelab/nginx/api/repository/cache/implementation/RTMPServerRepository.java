package live.livelab.nginx.api.repository.cache.implementation;

import com.google.inject.Inject;
import live.livelab.infrastructure.exception.RepositoryException;
import live.livelab.nginx.api.entity.IRTMPServerRepository;
import live.livelab.nginx.api.entity.RTMPServer;
import live.livelab.nginx.api.repository.cache.StatisticsXmlCache;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPServerConvertor;
import live.livelab.nginx.api.repository.rpc.interfaces.IRTMPServerRPCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kevin on 17/1/26.
 */
public class RTMPServerRepository implements IRTMPServerRepository {

    private static final Logger logger = LoggerFactory.getLogger(RTMPServerRepository.class);

    private final IRTMPServerConvertor xmlConvertor;
    private final IRTMPServerRPCRepository rpcRepository;

    @Inject
    public RTMPServerRepository(IRTMPServerConvertor xmlConvertor, IRTMPServerRPCRepository rpcRepository) {
        this.xmlConvertor = xmlConvertor;
        this.rpcRepository = rpcRepository;
    }

    @Override
    public RTMPServer load() throws RepositoryException {
        RTMPServer item = null;
        try {
            String xml = StatisticsXmlCache.get(cacheKey);
            item = this.xmlConvertor.convertFromXml(xml);
        } catch (Exception e) {
            logger.error("An error occurred while loading RTMPServer entity from cache", e);
            throw new RepositoryException(e);
        }
        return item;
    }

    @Override
    public RTMPServer loadFromRPC() throws RepositoryException {
        RTMPServer item = null;
        try {
            String xml = this.rpcRepository.getStatisticsXml();
            item = this.xmlConvertor.convertFromXml(xml);
        } catch (Exception e) {
            logger.error("An error occurred while loading RTMPServer entity from rpc", e);
            throw new RepositoryException(e);
        }
        return item;
    }

    @Override
    public String loadStatisticsXml() throws RepositoryException {
        String xml = null;
        try {
            xml = StatisticsXmlCache.get(cacheKey);
        } catch (Exception e) {
            logger.error("An error occurred while loading statistics xml from cache", e);
            throw new RepositoryException(e);
        }
        return xml;
    }

    @Override
    public String loadStatisticsXmlFromRPC() throws RepositoryException {
        String xml = null;
        try {
            xml = this.rpcRepository.getStatisticsXml();
        } catch (Exception e) {
            logger.error("An error occurred while loading statistics xml from rpc", e);
            throw new RepositoryException(e);
        }
        return xml;
    }

    @Override
    public void refreshCache() throws RepositoryException {
        logger.info("start to refresh statistics xml cache via rpc...");
        String xml = this.loadStatisticsXmlFromRPC();
        try {
            StatisticsXmlCache.update(cacheKey, xml);
        } catch (Exception e) {
            logger.error("An error occurred while refresh statistics xml cache", e);
            throw new RepositoryException(e);
        }
    }
}
