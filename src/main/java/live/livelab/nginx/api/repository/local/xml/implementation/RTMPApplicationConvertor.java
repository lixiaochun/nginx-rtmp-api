package live.livelab.nginx.api.repository.local.xml.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.RTMPApplication;
import live.livelab.nginx.api.entity.RTMPStream;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPApplicationConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPStreamConvertor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPApplicationConvertor extends XmlNodeConvertor implements IRTMPApplicationConvertor {

    private static final String xpathName = "./name";
    private static final String xpathStream = "./live/stream";
    private static final String xpathClientCount = "./live/nclients";

    private final IRTMPStreamConvertor rtmpStreamConvertor;

    @Inject
    public RTMPApplicationConvertor(IRTMPStreamConvertor rtmpStreamConvertor) {
        this.rtmpStreamConvertor = rtmpStreamConvertor;
    }

    @Override
    public RTMPApplication convertFromXmlNode(Node xmlNode) {
        if(xmlNode == null){
            return null;
        }
        String name = super.getXpathExpressionStringValue(xmlNode, xpathName);
        int clientCount = super.getXpathExpressionIntValue(xmlNode, xpathClientCount);
        NodeList nodeList = super.getXpathExpressionValueAsNodeList(xmlNode, xpathStream);
        List<RTMPStream> streamList = this.rtmpStreamConvertor.convertFromXmlNodeList(nodeList);
        return new RTMPApplication(name, clientCount, streamList);
    }
}
