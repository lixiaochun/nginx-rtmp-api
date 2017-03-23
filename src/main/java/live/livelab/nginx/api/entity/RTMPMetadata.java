package live.livelab.nginx.api.entity;

import java.io.Serializable;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPMetadata implements Serializable{

    private static final long serialVersionUID = 1828692499336575671L;
    private VideoMetadata video;
    private AudioMetadata audio;

    public VideoMetadata getVideo() {
        return video;
    }

    public void setVideo(VideoMetadata video) {
        this.video = video;
    }

    public AudioMetadata getAudio() {
        return audio;
    }

    public void setAudio(AudioMetadata audio) {
        this.audio = audio;
    }

    public RTMPMetadata() {

    }

    public RTMPMetadata(VideoMetadata video, AudioMetadata audio) {

        this.video = video;
        this.audio = audio;
    }
}
