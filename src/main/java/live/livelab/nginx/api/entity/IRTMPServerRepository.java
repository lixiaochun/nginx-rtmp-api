package live.livelab.nginx.api.entity;


import live.livelab.infrastructure.exception.RepositoryException;

/**
 * Created by kevin on 17/1/26.
 */
public interface IRTMPServerRepository {
    static final String cacheKey = "local-rtmp-server";

    RTMPServer load() throws RepositoryException;

    RTMPServer loadFromRPC() throws RepositoryException;

    String loadStatisticsXml() throws RepositoryException;

    String loadStatisticsXmlFromRPC() throws RepositoryException;

    void refreshCache() throws RepositoryException;
}
