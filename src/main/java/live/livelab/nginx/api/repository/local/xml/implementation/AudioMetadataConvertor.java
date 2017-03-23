package live.livelab.nginx.api.repository.local.xml.implementation;

import live.livelab.nginx.api.entity.AudioMetadata;
import live.livelab.nginx.api.repository.local.xml.interfaces.IAudioMetadataConvertor;
import org.w3c.dom.Node;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class AudioMetadataConvertor extends XmlNodeConvertor implements IAudioMetadataConvertor {

    private static final String xpathCodec = "./codec";
    private static final String xpathProfile = "./profile";
    private static final String xpathChannelCount = "./channels";
    private static final String xpathSampleRate = "./sample_rate";

    @Override
    public AudioMetadata convertFromXmlNode(Node xmlNode) {
        if(xmlNode == null){
            return null;
        }
        String codec = super.getXpathExpressionStringValue(xmlNode, xpathCodec);
        String profile = super.getXpathExpressionStringValue(xmlNode, xpathProfile);
        int channelCount = super.getXpathExpressionIntValue(xmlNode, xpathChannelCount);
        int sampleRate = super.getXpathExpressionIntValue(xmlNode, xpathSampleRate);
        AudioMetadata entity = new AudioMetadata(codec, profile, channelCount, sampleRate);
        return entity;
    }
}
