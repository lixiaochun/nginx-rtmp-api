package live.livelab.nginx.api.controller;

import live.livelab.infrastructure.extension.HttpRequestUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zhouxiang on 2/3/2016.
 */
public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected Map<String, String> getRequestParameterMap(HttpServletRequest request){
        return this.getRequestParameterMap(request, true);
    }

    protected Map<String, String> getRequestParameterMap(HttpServletRequest request, boolean ignoreNullValue){
        if(request == null){
            return null;
        }
        return HttpRequestUtility.getRequestParameterMap(request, ignoreNullValue);
    }

    protected byte[] getRequestPostBytes(HttpServletRequest request) throws IOException{
        if(request == null){
            return null;
        }
        return HttpRequestUtility.getRequestPostBytes(request);
    }

    protected String getRequestPostString(HttpServletRequest request) throws IOException{
        if(request == null){
            return null;
        }
        return HttpRequestUtility.getRequestPostStr(request);
    }

    protected String getRemoteHost(HttpServletRequest request){
        return HttpRequestUtility.getRemoteHost(request);
    }
}
