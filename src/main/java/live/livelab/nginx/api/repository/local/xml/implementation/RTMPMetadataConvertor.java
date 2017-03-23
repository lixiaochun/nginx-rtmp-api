package live.livelab.nginx.api.repository.local.xml.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.AudioMetadata;
import live.livelab.nginx.api.entity.RTMPMetadata;
import live.livelab.nginx.api.entity.VideoMetadata;
import live.livelab.nginx.api.repository.local.xml.interfaces.IAudioMetadataConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPMetadataConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IVideoMetadataConvertor;
import org.w3c.dom.Node;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPMetadataConvertor extends XmlNodeConvertor implements IRTMPMetadataConvertor {

    private static final String xpathAudio = "./audio";
    private static final String xpathVideo = "./video";

    private final IVideoMetadataConvertor videoMetadataConvertor;
    private final IAudioMetadataConvertor audioMetadataConvertor;

    @Inject
    public RTMPMetadataConvertor(IVideoMetadataConvertor videoMetadataConvertor, IAudioMetadataConvertor audioMetadataConvertor) {
        this.videoMetadataConvertor = videoMetadataConvertor;
        this.audioMetadataConvertor = audioMetadataConvertor;
    }

    @Override
    public RTMPMetadata convertFromXmlNode(Node xmlNode) {
        if(xmlNode == null){
            return null;
        }
        Node videoNode = super.getXpathExpressionValueAsNode(xmlNode, xpathVideo);
        Node audioNode = super.getXpathExpressionValueAsNode(xmlNode, xpathAudio);
        AudioMetadata audioMetadata = this.audioMetadataConvertor.convertFromXmlNode(audioNode);
        VideoMetadata videoMetadata = this.videoMetadataConvertor.convertFromXmlNode(videoNode);
        RTMPMetadata entity = new RTMPMetadata(videoMetadata, audioMetadata);
        return entity;
    }
}
