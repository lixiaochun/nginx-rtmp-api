package live.livelab.nginx.api.entity;

import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public final class RTMPServerBuilder {
    private String nginxVersion;
    private String nginxRTMPVersion;
    private String compiler;
    private String builtTime;
    private int pid;
    private long runtime;
    private int acceptedCount;
    private long inboundSpeed;      //  unit: bits/s
    private long inboundAmount;     //  unit: bytes
    private long outboundSpeed;     //  unit: bits/s
    private long outboundAmount;    //  unit: bytes
    private List<RTMPApplication> applicationList;

    private RTMPServerBuilder() {
    }

    public RTMPServerBuilder setNginxVersion(String nginxVersion) {
        this.nginxVersion = nginxVersion;
        return this;
    }

    public RTMPServerBuilder setNginxRTMPVersion(String nginxRTMPVersion) {
        this.nginxRTMPVersion = nginxRTMPVersion;
        return this;
    }

    public RTMPServerBuilder setCompiler(String compiler) {
        this.compiler = compiler;
        return this;
    }

    public RTMPServerBuilder setBuiltTime(String builtTime) {
        this.builtTime = builtTime;
        return this;
    }

    public RTMPServerBuilder setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public RTMPServerBuilder setRuntime(long runtime) {
        this.runtime = runtime;
        return this;
    }

    public RTMPServerBuilder setAcceptedCount(int acceptedCount) {
        this.acceptedCount = acceptedCount;
        return this;
    }

    public RTMPServerBuilder setInboundSpeed(long inboundSpeed) {
        this.inboundSpeed = inboundSpeed;
        return this;
    }

    public RTMPServerBuilder setInboundAmount(long inboundAmount) {
        this.inboundAmount = inboundAmount;
        return this;
    }

    public RTMPServerBuilder setOutboundSpeed(long outboundSpeed) {
        this.outboundSpeed = outboundSpeed;
        return this;
    }

    public RTMPServerBuilder setOutboundAmount(long outboundAmount) {
        this.outboundAmount = outboundAmount;
        return this;
    }

    public RTMPServerBuilder setApplicationList(List<RTMPApplication> applicationList) {
        this.applicationList = applicationList;
        return this;
    }

    public static RTMPServerBuilder newBuilder(){
        return new RTMPServerBuilder();
    }

    public RTMPServer build() {
        RTMPServer nginxRTMP = new RTMPServer();
        nginxRTMP.setNginxVersion(nginxVersion);
        nginxRTMP.setNginxRTMPVersion(nginxRTMPVersion);
        nginxRTMP.setCompiler(compiler);
        nginxRTMP.setBuiltTime(builtTime);
        nginxRTMP.setPid(pid);
        nginxRTMP.setRuntime(runtime);
        nginxRTMP.setAcceptedCount(acceptedCount);
        nginxRTMP.setInboundSpeed(inboundSpeed);
        nginxRTMP.setInboundAmount(inboundAmount);
        nginxRTMP.setOutboundSpeed(outboundSpeed);
        nginxRTMP.setOutboundAmount(outboundAmount);
        nginxRTMP.setApplicationList(applicationList);
        return nginxRTMP;
    }
}
