package live.livelab.nginx.api.repository.rpc.implementation;

import live.livelab.configuration.AppSettingsFactory;
import live.livelab.configuration.IAppSettings;
import live.livelab.nginx.api.repository.rpc.messaging.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhouxiang on 2/10/2016.
 */
public class Repository {
    protected static final IAppSettings applicationSettings = AppSettingsFactory.getAppSettings();
    protected static final Logger logger = LoggerFactory.getLogger(Repository.class);

    public HttpResponse postData(String url, byte[] data){
        return NetworkUtility.postData(url, data);
    }

    public HttpResponse getData(String url){
        return NetworkUtility.getData(url);
    }

    public HttpResponse postData(String url, byte[] data, Map<String, String> requestHeaderProperty) {
        return NetworkUtility.postData(url, data, requestHeaderProperty);
    }

    protected HttpResponse postFormData(String url, Map<String, Object> data){
        return NetworkUtility.postFormData(url, data);
    }

    protected HttpResponse postJsonData(String url, String json){
        return NetworkUtility.postJsonData(url, json);
    }
}
