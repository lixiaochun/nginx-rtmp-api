package live.livelab.infrastructure.extension;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouxiang on 2/29/2016.
 */
public abstract class HttpRequestUtility {
    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        if(request == null){
            return null;
        }
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if(contentLength < 0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readLength = request.getInputStream().read(buffer, i, contentLength - i);
            if (readLength == -1) {
                break;
            }
            i += readLength;
        }
        return buffer;
    }

    public static Map<String, String> getRequestParameterMap(HttpServletRequest request){
         return getRequestParameterMap(request, false);
    }

    public static Map<String, String> getRequestParameterMap(HttpServletRequest request, boolean ignoreNullValue){
        if(request == null){
            return null;
        }
        Map<String, String> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        String name, value;
        while (parameterNames.hasMoreElements()) {
            name = parameterNames.nextElement();
            value = request.getParameter(name);
            if(ignoreNullValue && (value == null || value.isEmpty())){
                continue;
            }
            map.put(name, value);
        }
        return map;
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        if(request == null){
            return null;
        }
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        String data = buffer == null ? null : new String(buffer, charEncoding);
        return data;
    }

    public static String getRemoteHost(HttpServletRequest request){
        if(request == null){
            return null;
        }
        String ip = request.getHeader("X-Real-IP");
        if(ip == null){
            ip = request.getHeader("X-Forwarded-For");
        }
        if(ip == null){
            ip = request.getRemoteAddr();
        }
        if(ip == null){
            ip = request.getRemoteHost();
        }
        return ip;
    }
}
