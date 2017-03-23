package live.livelab.nginx.api.repository.rpc.messaging;

import live.livelab.infrastructure.messaging.ResponseBase;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouxiang on 2/15/2016.
 */
public class HttpResponse extends ResponseBase {
    private String responseText;
    private long lastModifiedTime;

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(Map<String, List<String>> headerFields) {
        this.headerFields = headerFields;
    }

    private Map<String, List<String>> headerFields;

    public HttpResponse(boolean success, String message) {
        super(success, message);
    }

    public HttpResponse(boolean success, String message, int statusCode, String responseText){
        this(success, message, statusCode, responseText, null, 0);
    }

    public HttpResponse(boolean success, String message, int statusCode, String responseText, Map<String, List<String>> headerFields,
                        long lastModifiedTime) {
        super(success, message, statusCode);
        this.responseText = responseText;
        this.headerFields = headerFields;
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public List<String> getHeaderValue(String headerName){
        if(StringUtils.isEmpty(headerName) || this.headerFields == null || this.headerFields.isEmpty()){
            return null;
        }
        List<String> headerValue = this.headerFields.containsKey(headerName) ? this.headerFields.get(headerName) : null;
        return headerValue;
    }

}
