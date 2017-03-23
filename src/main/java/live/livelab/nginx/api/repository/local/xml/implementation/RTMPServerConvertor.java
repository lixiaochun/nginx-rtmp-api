package live.livelab.nginx.api.repository.local.xml.implementation;

import com.google.inject.Inject;
import live.livelab.nginx.api.entity.RTMPApplication;
import live.livelab.nginx.api.entity.RTMPServer;
import live.livelab.nginx.api.entity.RTMPServerBuilder;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPApplicationConvertor;
import live.livelab.nginx.api.repository.local.xml.interfaces.IRTMPServerConvertor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPServerConvertor extends XmlNodeConvertor implements IRTMPServerConvertor {

    private static final String xpathNginxVersion = "/rtmp/nginx_version";
    private static final String xpathNginxRTMPVersion = "/rtmp/nginx_rtmp_version";
    private static final String xpathCompiler = "/rtmp/compiler";
    private static final String xpathBuiltTime = "/rtmp/built";
    private static final String xpathPid = "/rtmp/pid";
    private static final String xpathRuntime = "/rtmp/uptime";
    private static final String xpathAcceptedCount = "/rtmp/naccepted";
    private static final String xpathInboundSpeed = "/rtmp/bw_in";
    private static final String xpathInboundAmount = "/rtmp/bytes_in";
    private static final String xpathOutboundSpeed = "/rtmp/bw_out";
    private static final String xpathOutboundAmount = "/rtmp/bytes_out";
    private static final String xpathApplication = "/rtmp/server/application";

    private final IRTMPApplicationConvertor rtmpApplicationConvertor;

    @Inject
    public RTMPServerConvertor(IRTMPApplicationConvertor rtmpApplicationConvertor) {
        this.rtmpApplicationConvertor = rtmpApplicationConvertor;
    }

    @Override
    public RTMPServer convertFromXml(String xmlDocument) {
        return this.convertFromXml(super.valueOf(xmlDocument));
    }

    @Override
    public RTMPServer convertFromXml(Document document) {
        if(document == null){
            return null;
        }

        String nginxVersion = super.getXpathExpressionStringValue(document, xpathNginxVersion);
        String nginxRTMPVersion = super.getXpathExpressionStringValue(document, xpathNginxRTMPVersion);
        String compiler = super.getXpathExpressionStringValue(document, xpathCompiler);
        String builtTime = super.getXpathExpressionStringValue(document, xpathBuiltTime);
        int pid = super.getXpathExpressionIntValue(document, xpathPid);
        long runtime = super.getXpathExpressionLongValue(document, xpathRuntime);
        int acceptedCount = super.getXpathExpressionIntValue(document, xpathAcceptedCount);
        long inboundSpeed = super.getXpathExpressionLongValue(document, xpathInboundSpeed);      //  unit: bits/s
        long inboundAmount = super.getXpathExpressionLongValue(document, xpathInboundAmount);     //  unit: bytes
        long outboundSpeed = super.getXpathExpressionLongValue(document, xpathOutboundSpeed);     //  unit: bits/s
        long outboundAmount = super.getXpathExpressionLongValue(document, xpathOutboundAmount);    //  unit: bytes

        NodeList applicationNodeList = super.getXpathExpressionValueAsNodeList(document, xpathApplication);
        List<RTMPApplication> applicationList = this.rtmpApplicationConvertor.convertFromXmlNodeList(applicationNodeList);
        RTMPServer entity = RTMPServerBuilder.newBuilder()
                .setNginxVersion(nginxVersion)
                .setNginxRTMPVersion(nginxRTMPVersion)
                .setCompiler(compiler)
                .setBuiltTime(builtTime)
                .setPid(pid)
                .setRuntime(runtime)
                .setAcceptedCount(acceptedCount)
                .setInboundSpeed(inboundSpeed)
                .setInboundAmount(inboundAmount)
                .setOutboundSpeed(outboundSpeed)
                .setOutboundAmount(outboundAmount)
                .setApplicationList(applicationList)
                .build();
        return entity;
    }
}
