package live.livelab.nginx.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPStream implements Serializable{

    private static final long serialVersionUID = -1872041632642074818L;
    private String name;
    private long duration;
    private long inboundSpeed;
    private long inboundAmount;
    private long outboundSpeed;
    private long outboundAmount;
    private long audioBitrate;
    private long videoBitrate;
    private List<RTMPClient> clientList;
    private RTMPMetadata metadata;
    private int clientCount;
    private boolean active;
    private boolean publishing;

    public RTMPStream(String name, long duration, long inboundSpeed, long inboundAmount, long outboundSpeed,
                      long outboundAmount, long audioBitrate, long videoBitrate, List<RTMPClient> clientList,
                      RTMPMetadata metadata, int clientCount, boolean active, boolean publishing) {
        this.name = name;
        this.duration = duration;
        this.inboundSpeed = inboundSpeed;
        this.inboundAmount = inboundAmount;
        this.outboundSpeed = outboundSpeed;
        this.outboundAmount = outboundAmount;
        this.audioBitrate = audioBitrate;
        this.videoBitrate = videoBitrate;
        this.clientList = clientList;
        this.metadata = metadata;
        this.clientCount = clientCount;
        this.active = active;
        this.publishing = publishing;
    }

    public RTMPStream() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getInboundSpeed() {
        return inboundSpeed;
    }

    public void setInboundSpeed(long inboundSpeed) {
        this.inboundSpeed = inboundSpeed;
    }

    public long getInboundAmount() {
        return inboundAmount;
    }

    public void setInboundAmount(long inboundAmount) {
        this.inboundAmount = inboundAmount;
    }

    public long getOutboundSpeed() {
        return outboundSpeed;
    }

    public void setOutboundSpeed(long outboundSpeed) {
        this.outboundSpeed = outboundSpeed;
    }

    public long getOutboundAmount() {
        return outboundAmount;
    }

    public void setOutboundAmount(long outboundAmount) {
        this.outboundAmount = outboundAmount;
    }

    public long getAudioBitrate() {
        return audioBitrate;
    }

    public void setAudioBitrate(long audioBitrate) {
        this.audioBitrate = audioBitrate;
    }

    public long getVideoBitrate() {
        return videoBitrate;
    }

    public void setVideoBitrate(long videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

    public List<RTMPClient> getClientList() {
        return clientList;
    }

    public void setClientList(List<RTMPClient> clientList) {
        this.clientList = clientList;
    }

    public RTMPMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(RTMPMetadata metadata) {
        this.metadata = metadata;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPublishing() {
        return publishing;
    }

    public void setPublishing(boolean publishing) {
        this.publishing = publishing;
    }
}
