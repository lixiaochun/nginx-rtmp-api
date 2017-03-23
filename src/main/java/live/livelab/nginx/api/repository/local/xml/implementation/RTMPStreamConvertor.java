package live.livelab.nginx.api.repository.local.xml.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.RTMPClient;
import live.livelab.nginx.api.entity.RTMPMetadata;
import live.livelab.nginx.api.entity.RTMPStream;
import live.livelab.nginx.api.entity.RTMPStreamBuilder;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPClientConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPMetadataConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPStreamConvertor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPStreamConvertor extends XmlNodeConvertor implements IRTMPStreamConvertor {

    private static final String xpathName = "./name";
    private static final String xpathDuration = "./time";
    private static final String xpathInboundSpeed = "./bw_in";
    private static final String xpathInboundAmount = "./bytes_in";
    private static final String xpathOutboundSpeed = "./bw_out";
    private static final String xpathOutboundAmount = "./bytes_out";
    private static final String xpathAudioBitrate = "./bw_audio";
    private static final String xpathVideoBitrate = "./bw_video";
    private static final String xpathClient = "./client";
    private static final String xpathMetadata = "./meta";
    private static final String xpathClientCount = "./nclients";
    private static final String xpathActive = "./publishing";
    private static final String xpathPublishing = "./active";

    private final IRTMPClientConvertor rtmpClientConvertor;
    private final IRTMPMetadataConvertor rtmpMetadataConvertor;

    @Inject
    public RTMPStreamConvertor(IRTMPClientConvertor rtmpClientConvertor, IRTMPMetadataConvertor rtmpMetadataConvertor) {
        this.rtmpClientConvertor = rtmpClientConvertor;
        this.rtmpMetadataConvertor = rtmpMetadataConvertor;
    }

    @Override
    public RTMPStream convertFromXmlNode(Node xmlNode) {
        if(xmlNode == null){
            return null;
        }
        String name = super.getXpathExpressionStringValue(xmlNode, xpathName);
        long duration = super.getXpathExpressionLongValue(xmlNode, xpathDuration);
        long inboundSpeed = super.getXpathExpressionLongValue(xmlNode, xpathInboundSpeed);
        long inboundAmount = super.getXpathExpressionLongValue(xmlNode, xpathInboundAmount);
        long outboundSpeed = super.getXpathExpressionLongValue(xmlNode, xpathOutboundSpeed);
        long outboundAmount = super.getXpathExpressionLongValue(xmlNode, xpathOutboundAmount);
        long audioBitrate = super.getXpathExpressionLongValue(xmlNode, xpathAudioBitrate);
        long videoBitrate = super.getXpathExpressionLongValue(xmlNode, xpathVideoBitrate);
        NodeList clientNodeList = super.getXpathExpressionValueAsNodeList(xmlNode, xpathClient);
        List<RTMPClient> clientList = this.rtmpClientConvertor.convertFromXmlNodeList(clientNodeList);
        RTMPMetadata metadata = this.rtmpMetadataConvertor.convertFromXmlNode(super.getXpathExpressionValueAsNode(xmlNode, xpathMetadata));
        int clientCount = super.getXpathExpressionIntValue(xmlNode, xpathClientCount);
        boolean active = super.getXpathExpressionValueAsNode(xmlNode, xpathActive) != null;
        boolean publishing = super.getXpathExpressionValueAsNode(xmlNode, xpathPublishing) != null;
        RTMPStream entity = RTMPStreamBuilder.newBuilder()
                .setName(name)
                .setDuration(duration)
                .setInboundAmount(inboundAmount)
                .setInboundSpeed(inboundSpeed)
                .setOutboundSpeed(outboundSpeed)
                .setOutboundAmount(outboundAmount)
                .setAudioBitrate(audioBitrate)
                .setVideoBitrate(videoBitrate)
                .setClientCount(clientCount)
                .setClientList(clientList)
                .setMetadata(metadata)
                .setActive(active)
                .setPublishing(publishing)
                .build();
        return entity;
    }
}
