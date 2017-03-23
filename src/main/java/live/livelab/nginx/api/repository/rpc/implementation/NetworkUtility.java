package live.livelab.nginx.api.repository.rpc.implementation;

import live.livelab.configuration.AppSettingsFactory;
import live.livelab.configuration.IAppSettings;
import live.livelab.nginx.api.repository.rpc.messaging.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxiang on 1/22/2016.
 */
public abstract class NetworkUtility {
    protected static final IAppSettings applicationSettings = AppSettingsFactory.getAppSettings();
    protected static final Logger logger = LoggerFactory.getLogger(NetworkUtility.class);

    public static HttpResponse postData(String url, byte[] data){
        Map<String, String> headerProperty = new HashMap<>();
        headerProperty.put("Content-Type", "application/octet-stream");
        return postData(url, data, headerProperty);
    }

    public static HttpResponse postData(String url, byte[] data, Map<String, String> requestHeaderProperty) {
        if (url == null || url.isEmpty()) {
            return new HttpResponse(false, "illegal request url");
        }
        URL remoteUrl;
        try {
            remoteUrl = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("An error occurred while creating URL", e);
            return new HttpResponse(false, "An error occurred while creating URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) remoteUrl.openConnection();
        } catch (IOException e) {
            logger.error("An error occurred while opening connection", e);
            return new HttpResponse(false, "An error occurred while opening connection");
        }
        //  add request header
        try {
            connection.setRequestMethod("POST");
            setRequestHeaderProperty(connection, requestHeaderProperty);
            connection.setRequestProperty("charset", "utf-8");
        } catch (ProtocolException e) {
            logger.error("An error occurred while setting request method", e);
            return new HttpResponse(false, "An error occurred while setting request method");
        }

        //  send post request
        connection.setDoInput(true);
        connection.setDoOutput(true);
        DataOutputStream outputStream = null;
        int responseCode;
        try {
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(data);
            outputStream.flush();

        } catch (IOException e) {
            String message = MessageFormat.format("An error occurred while getting output stream, url: {0}", url);
            logger.error(message, e);
            return new HttpResponse(false, "An error occurred while setting request method");
        }
        finally {
            if(outputStream != null) {
                IOUtils.closeQuietly(outputStream);
            }
        }
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            logger.error("An error occurred while getting response code", e);
            return new HttpResponse(false, "An error occurred while getting response code");
        }
        InputStream stream = null;
        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            logger.error("An error occurred while getting response input stream", e);
            return new HttpResponse(false, "An error occurred while getting response input stream");
        }
        if (stream == null) {
            return new HttpResponse(false, "An error occurred while getting response input stream");
        }

        return readInputStream(responseCode, stream);
    }

    private static void setRequestHeaderProperty(HttpURLConnection httpURLConnection, Map<String, String> requestHeaderProperty) {
        if (httpURLConnection == null || requestHeaderProperty == null || requestHeaderProperty.isEmpty()) {
            return;
        }
        String key, value;
        for (Map.Entry<String, String> entry : requestHeaderProperty.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key == null || key.isEmpty()) {
                continue;
            }
            httpURLConnection.setRequestProperty(key, value);
        }
    }

    private static String appQueryStringToUrl(Map<String, String> data, String url, boolean encodeValue) {
        if (data == null || data.isEmpty() || url == null || url.isEmpty()) {
            return url;
        }
        String key, value;
        StringBuilder sbQueryString = new StringBuilder();
        for (Map.Entry<String, String> item : data.entrySet()) {
            key = item.getKey();
            value = item.getValue();
            if (encodeValue && value != null && !value.isEmpty()) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("An error occurred while encoding text: " + value, e);
                }
            }
            sbQueryString.append(key);
            sbQueryString.append("=");
            sbQueryString.append(value);
        }
        sbQueryString.insert(0, url.contains("?") ? "&" : "?");
        url = url + sbQueryString.toString();
        return url;
    }

    public static HttpResponse getData(String url) {
        return getData(url, null, null);
    }

    public static HttpResponse getData(String url, Map<String, String> requestHeaderProperty) {
        return getData(url, null, requestHeaderProperty);
    }

    public static HttpResponse getData(String url, Map<String, String> data, Map<String, String> requestHeaderProperty) {
        if (url == null || url.isEmpty()) {
            return new HttpResponse(false, "illegal request url");
        }
        url = appQueryStringToUrl(data, url, true);
        URL remoteUrl;
        try {
            remoteUrl = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("An error occurred while creating URL", e);
            return new HttpResponse(false, "An error occurred while creating URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) remoteUrl.openConnection();
        } catch (IOException e) {
            logger.error("An error occurred while opening connection", e);
            return new HttpResponse(false, "An error occurred while opening connection");
        }
        //  add request header
        try {
            connection.setRequestMethod("GET");
            setRequestHeaderProperty(connection, requestHeaderProperty);
            connection.setRequestProperty("charset", "utf-8");
        } catch (ProtocolException e) {
            logger.error("An error occurred while setting request method", e);
            return new HttpResponse(false, "An error occurred while setting request method");
        }

        //  send get request
        connection.setDoInput(true);
        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            logger.error("An error occurred while getting response code", e);
            return new HttpResponse(false, "An error occurred while getting response code");
        }
        InputStream stream = null;
        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            logger.error("An error occurred while getting response input stream", e);
            return new HttpResponse(false, "An error occurred while getting response input stream");
        }
        if (stream == null) {
            return new HttpResponse(false, "An error occurred while getting response input stream");
        }
        return readInputStream(responseCode, stream);

    }

    private static HttpResponse readInputStream(int responseCode, InputStream stream) {
        if (stream == null) {
            return new HttpResponse(false, "empty input stream");
        }
        String inputLine;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((inputLine = reader.readLine()) != null) {
                buffer.append(inputLine);
            }
        } catch (IOException e) {
            logger.error("An error occurred while reading response input stream", e);
            return new HttpResponse(false, "An error occurred while reading response input stream");
        } finally {
            if (reader != null) {
                IOUtils.closeQuietly(reader);
            }
        }
        return new HttpResponse(true, "ok", responseCode, buffer.toString());
    }

    public static HttpResponse postFormData(String url, Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        if (data != null && !data.isEmpty()) {
            String key;
            Object value;
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key == null || key.isEmpty()) {
                    continue;
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        String urlParameters = sb.toString();
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        Map<String, String> headerProperty = new HashMap<>();
        headerProperty.put("charset", "utf-8");
        headerProperty.put("Content-Type", "application/x-www-form-urlencoded");
        return postData(url, postData, headerProperty);
    }

    public static HttpResponse postJsonData(String url, String json) {
        if (json == null || json.isEmpty()) {
            json = "{}";
        }
        byte[] postData = json.getBytes(StandardCharsets.UTF_8);
        Map<String, String> headerProperty = new HashMap<>();
        headerProperty.put("Content-Type", "application/json");
        return postData(url, postData, headerProperty);
    }

    public static HttpResponse postMultipartEntity(String url, Map<String, Object> textData, Map<String, byte[]> fileData){
        return postMultipartEntity(url, textData, fileData, null, null);
    }
    public static HttpResponse postMultipartEntity(String url, Map<String, Object> textData, Map<String, byte[]> fileData, Map<String, String> fileNameMap) {
        return postMultipartEntity(url, textData, fileData, fileNameMap, null);
    }

    public static HttpResponse postMultipartEntity(String url, Map<String, Object> textData, Map<String, byte[]> fileData, Map<String, String> fileNameMap,
                                                   Map<String, String> requestHeaderProperty) {
        if (url == null || url.isEmpty()) {
            return new HttpResponse(false, "illegal request url");
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        if (requestHeaderProperty != null && !requestHeaderProperty.isEmpty()) {
            String key, value;
            for (Map.Entry<String, String> item : requestHeaderProperty.entrySet()) {
                key = item.getKey();
                value = item.getValue();
                httpPost.setHeader(key, value);
            }
        }
        String name, value, message = "ok";
        boolean success = true;
        byte[] data;
        if (textData != null && !textData.isEmpty()) {
            for (Map.Entry<String, Object> item : textData.entrySet()) {
                if (item == null) {
                    continue;
                }
                name = item.getKey();
                value = String.valueOf(item.getValue());
                if (name == null || value == null || name.isEmpty() || value.isEmpty()) {
                    continue;
                }
                entityBuilder.addTextBody(name, value);
            }
        }
        if (fileData != null && !fileData.isEmpty()) {
            String fileName = null;
            for (Map.Entry<String, byte[]> item : fileData.entrySet()) {
                if (item == null) {
                    continue;
                }
                name = item.getKey();
                data = item.getValue();
                if (name == null || data == null || name.isEmpty() || data.length == 0) {
                    continue;
                }
                fileName = fileNameMap != null ? fileNameMap.get(name) : String.valueOf(System.currentTimeMillis());
                entityBuilder.addBinaryBody(name, data, ContentType.create("application/octet-stream"), fileName);
            }
        }
        HttpEntity requestEntity = entityBuilder.build();
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = null;
        HttpResponse httpResponse = null;
        try {
            response = httpClient.execute(httpPost);
            if (response == null) {
                return new HttpResponse(false, message);
            }
            try {
                HttpEntity responseEntity = null;
                responseEntity = response.getEntity();
                int responseCode = response.getStatusLine().getStatusCode();
                String responseText = responseEntity != null ? EntityUtils.toString(responseEntity, "UTF-8") : null;
                httpResponse = new HttpResponse(success, "ok", responseCode, responseText);
                EntityUtils.consume(responseEntity);
            } catch (IOException e) {
                message = "An error occurred while getting entity from response";
                logger.error(message, e);
                success = false;
                httpResponse = new HttpResponse(success, message);
            } finally {
                IOUtils.closeQuietly(response);
            }
        } catch (IOException e) {
            message = MessageFormat.format("An error occurred while executing http post to url: {0}", url);
            logger.error(message, e);
            success = false;
            httpResponse = new HttpResponse(success, message);
        } finally {
            IOUtils.closeQuietly(httpClient);
        }
        return httpResponse;
    }

    public static HttpResponse head(String url){
        return head(url, null, null);
    }

    public static HttpResponse head(String url, Map<String, String> data){
        return head(url, data, null);
    }

    public static HttpResponse head(String url, Map<String, String> data, Map<String, String> requestHeaderProperty){
        if (url == null || url.isEmpty()) {
            return new HttpResponse(false, "illegal request url");
        }
        url = appendQueryStringToUrl(data, url, true);
        URL remoteUrl;
        try {
            remoteUrl = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("An error occurred while creating URL", e);
            return new HttpResponse(false, "An error occurred while creating URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) remoteUrl.openConnection();
        } catch (IOException e) {
            logger.error("An error occurred while opening connection", e);
            return new HttpResponse(false, "An error occurred while opening connection");
        }
        //  add request header
        try {
            connection.setRequestMethod("HEAD");
            setRequestHeaderProperty(connection, requestHeaderProperty);
            connection.setRequestProperty("charset", "utf-8");
        } catch (ProtocolException e) {
            logger.error("An error occurred while setting request method", e);
            return new HttpResponse(false, "An error occurred while setting request method");
        }

        //  send request
        connection.setDoInput(true);
        int responseCode;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            logger.error("An error occurred while getting response code", e);
            return new HttpResponse(false, "An error occurred while getting response code");
        }
        long lastModifiedTime = connection.getLastModified();
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        return new HttpResponse(true, "ok", responseCode, null, headerFields, lastModifiedTime);
    }

    private static String appendQueryStringToUrl(Map<String, String> data, String url, boolean encodeValue){
        if(data == null || data.isEmpty() || url == null || url.isEmpty()){
            return url;
        }
        String key, value;
        StringBuilder sbQueryString = new StringBuilder();
        for(Map.Entry<String, String> item : data.entrySet()){
            key = item.getKey();
            value = item.getValue();
            if(encodeValue && value != null && !value.isEmpty()){
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("An error occurred while encoding text: " + value, e);
                }
            }
            sbQueryString.append(key);
            sbQueryString.append("=");
            sbQueryString.append(value);
            sbQueryString.append("&");
        }
        sbQueryString.delete(sbQueryString.length() - 1,sbQueryString.length());
        sbQueryString.insert(0, url.contains("?") ? "&" : "?");
        url = url + sbQueryString.toString();
        return url;
    }
}
