package live.livelab.nginx.api.repository.local.xml.implementation;

import live.livelab.nginx.api.entity.VideoMetadata;
import live.livelab.nginx.api.repository.local.xml.interfaces.IVideoMetadataConvertor;
import org.w3c.dom.Node;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class VideoMetadataConvertor extends XmlNodeConvertor implements IVideoMetadataConvertor {

    private static final String xpathWidth = "./width";
    private static final String xpathHeight = "./height";
    private static final String xpathFrameRate = "./frame_rate";
    private static final String xpathCodec = "./codec";
    private static final String xpathProfile = "./profile";
    private static final String xpathCompact = "./compact";
    private static final String xpathLevel = "./level";

    @Override
    public VideoMetadata convertFromXmlNode(Node xmlNode) {
        if(xmlNode == null){
            return null;
        }
        int width = super.getXpathExpressionIntValue(xmlNode, xpathWidth);
        int height = super.getXpathExpressionIntValue(xmlNode, xpathHeight);
        int frameRate = super.getXpathExpressionIntValue(xmlNode, xpathFrameRate);
        String codec = super.getXpathExpressionStringValue(xmlNode, xpathCodec);
        String profile = super.getXpathExpressionStringValue(xmlNode, xpathProfile);
        int compact = super.getXpathExpressionIntValue(xmlNode, xpathCompact);
        String level = super.getXpathExpressionStringValue(xmlNode, xpathLevel);
        VideoMetadata entity = new VideoMetadata(width, height, frameRate, codec, profile, compact, level);
        return entity;
    }
}
