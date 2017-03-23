package live.livelab.nginx.api.repository.rpc.messaging;

import com.google.gson.Gson;
import live.livelab.infrastructure.messaging.ResponseBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouxiang on 4/6/2016.
 */
public class HttpResponseBase extends ResponseBase {
    protected static final Logger logger = LoggerFactory.getLogger(HttpResponseBase.class);
    public HttpResponseBase(){}
    public static HttpResponseBase convertFromJSON(String json){
        if(json == null || json.isEmpty()){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, HttpResponseBase.class);
    }
}
