package live.livelab.nginx.api.entity;

import java.io.Serializable;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class AudioMetadata implements Serializable{

    private static final long serialVersionUID = -3042368113708599912L;
    private String codec;
    private String profile;
    private int channelCount;
    private int sampleRate;

    public AudioMetadata() {
    }

    public String getCodec() {

        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(int channelCount) {
        this.channelCount = channelCount;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public AudioMetadata(String codec, String profile, int channelCount, int sampleRate) {

        this.codec = codec;
        this.profile = profile;
        this.channelCount = channelCount;
        this.sampleRate = sampleRate;
    }
}
