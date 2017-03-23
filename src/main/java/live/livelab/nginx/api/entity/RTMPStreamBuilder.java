package live.livelab.nginx.api.entity;

import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public final class RTMPStreamBuilder {
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

    private RTMPStreamBuilder() {
    }

    public RTMPStreamBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RTMPStreamBuilder setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public RTMPStreamBuilder setInboundSpeed(long inboundSpeed) {
        this.inboundSpeed = inboundSpeed;
        return this;
    }

    public RTMPStreamBuilder setInboundAmount(long inboundAmount) {
        this.inboundAmount = inboundAmount;
        return this;
    }

    public RTMPStreamBuilder setOutboundSpeed(long outboundSpeed) {
        this.outboundSpeed = outboundSpeed;
        return this;
    }

    public RTMPStreamBuilder setOutboundAmount(long outboundAmount) {
        this.outboundAmount = outboundAmount;
        return this;
    }

    public RTMPStreamBuilder setAudioBitrate(long audioBitrate) {
        this.audioBitrate = audioBitrate;
        return this;
    }

    public RTMPStreamBuilder setVideoBitrate(long videoBitrate) {
        this.videoBitrate = videoBitrate;
        return this;
    }

    public RTMPStreamBuilder setClientList(List<RTMPClient> clientList) {
        this.clientList = clientList;
        return this;
    }

    public RTMPStreamBuilder setMetadata(RTMPMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public RTMPStreamBuilder setClientCount(int clientCount) {
        this.clientCount = clientCount;
        return this;
    }

    public RTMPStreamBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public RTMPStreamBuilder setPublishing(boolean publishing) {
        this.publishing = publishing;
        return this;
    }

    public static RTMPStreamBuilder newBuilder(){
        return new RTMPStreamBuilder();
    }

    public RTMPStream build() {
        RTMPStream rTMPStream = new RTMPStream();
        rTMPStream.setName(name);
        rTMPStream.setDuration(duration);
        rTMPStream.setInboundSpeed(inboundSpeed);
        rTMPStream.setInboundAmount(inboundAmount);
        rTMPStream.setOutboundSpeed(outboundSpeed);
        rTMPStream.setOutboundAmount(outboundAmount);
        rTMPStream.setAudioBitrate(audioBitrate);
        rTMPStream.setVideoBitrate(videoBitrate);
        rTMPStream.setClientList(clientList);
        rTMPStream.setMetadata(metadata);
        rTMPStream.setClientCount(clientCount);
        rTMPStream.setActive(active);
        rTMPStream.setPublishing(publishing);
        return rTMPStream;
    }
}
