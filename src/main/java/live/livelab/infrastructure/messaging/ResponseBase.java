package live.livelab.infrastructure.messaging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by zhouxiang on 11/27/2015.
 */
public class ResponseBase {
    protected boolean success;
    protected String message;
    protected long timestamp;


    protected int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ResponseBase(){
        this(true, "ok");
    }

    public ResponseBase(boolean success, String message) {
        this(success, message, 0);
    }

    public ResponseBase(boolean success, String message, int statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String toJSON(){
        GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
//        builder.registerTypeAdapter(Timestamp.class, new JsonSerializer<Timestamp>() {
//            @Override
//            public JsonElement serialize(Timestamp timestamp, Type type, JsonSerializationContext jsonSerializationContext) {
//                return new JsonPrimitive(timestamp.getTime());
//            }
//        });
        Gson gson = builder.create();
        return gson.toJson(this);
    }
}
