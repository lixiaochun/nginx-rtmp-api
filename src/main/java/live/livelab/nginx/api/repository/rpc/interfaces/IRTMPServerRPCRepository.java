package live.livelab.nginx.api.repository.rpc.interfaces;


import live.livelab.infrastructure.exception.RepositoryException;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public interface IRTMPServerRPCRepository {
    String getStatisticsXml() throws RepositoryException;
}
