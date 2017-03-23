package live.livelab.nginx.api.entity;

import java.io.Serializable;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class VideoMetadata implements Serializable{

    private static final long serialVersionUID = -7954575532917176280L;
    private int width;
    private int height;
    private int frameRate;
    private String codec;
    private String profile;
    private int compact;
    private String level;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
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

    public int getCompact() {
        return compact;
    }

    public void setCompact(int compact) {
        this.compact = compact;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public VideoMetadata() {

    }

    public VideoMetadata(int width, int height, int frameRate, String codec, String profile, int compact, String level) {

        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        this.codec = codec;
        this.profile = profile;
        this.compact = compact;
        this.level = level;
    }
}
