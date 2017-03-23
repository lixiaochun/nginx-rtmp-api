package live.livelab.nginx.api.repository.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import live.livelab.nginx.api.repository.rpc.implementation.RTMPRepository;
import live.livelab.nginx.api.repository.rpc.interfaces.IRTMPServerRPCRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import live.livelab.configuration.AppSettingsFactory;
import live.livelab.configuration.IAppSettings;
import live.livelab.infrastructure.exception.RepositoryException;

import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 17/1/26.
 */
public class StatisticsXmlCache {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsXmlCache.class);
    private static IAppSettings appSettings = AppSettingsFactory.getAppSettings();
    private static final LoadingCache<String, String> cache;
    private static final IRTMPServerRPCRepository rpcRepository = new RTMPRepository();

    static {
        cache = initCache();
    }

    private StatisticsXmlCache() {
    }

    private static LoadingCache<String, String> initCache() {
        int cacheRefreshInterval = appSettings.getRTMPStatisticsInfoRefreshInterval();
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(cacheRefreshInterval, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String id) {
                                String xml = loadFromRPC(id);
                                return xml;
                            }
                        }
                );
        return loadingCache;
    }

    private static String loadFromRPC(String key) {
        logger.info("start to load statistics xml via rpc...");
        String xml = null;
        try {
            xml = rpcRepository.getStatisticsXml();
        } catch (RepositoryException e) {
            logger.error("An error occurred while loading statistics xml via rpc: " + e.getMessage());
        }
        return xml;
    }

    public static String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        String xml = null;
        try {
            xml = cache.get(key);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving statistics xml from cache", e);
        }
        return xml;
    }

    public static void updateCache(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        String xml = loadFromRPC(key);
        cache.put(key, xml);
    }

    public static void update(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        cache.put(key, value);
    }
}
