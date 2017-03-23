package live.livelab.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by kevin on 15/12/27.
 */
public class AppSettings implements IAppSettings {

    protected static final Logger logger = LoggerFactory.getLogger(AppSettings.class);
    protected static String propertyFileName = "application.properties";
    protected static final Configuration config = getConfigInstance();

    protected static Configuration getConfigInstance() {
        Configurations configs = new Configurations();
        Configuration config = null;
        File propertyFile = new File(propertyFileName);
        try {
            config = configs.properties(propertyFile);
            // access configuration properties
        }
        catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
            logger.error("An error occurred while initializing configuration instance", e);
        }
        if(config == null){
            logger.error("property file: application.properties not found");
        }
        return config;
    }

    @Override
    public int getServerPort() {
        int port = config.getInt("server.port", 8080);
        return port;
    }

    @Override
    public String getNginxRTMPStatisticsXmlUrl() {
        return config.getString("nginx.rtmp.server.statistics.xml.url");
    }

    @Override
    public int getRTMPStatisticsInfoRefreshInterval() {
        return config.getInt("nginx.rtmp.server.statistics.xml.refresh.interval", 5);
    }

}
