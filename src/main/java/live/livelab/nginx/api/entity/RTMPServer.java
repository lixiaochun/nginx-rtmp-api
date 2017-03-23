package live.livelab.nginx.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPServer implements Serializable{

    private static final long serialVersionUID = -2933565814782430405L;
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

    public RTMPServer() {
    }

    public String getNginxVersion() {
        return nginxVersion;
    }

    public RTMPServer(String nginxVersion, String nginxRTMPVersion, String compiler, String builtTime, int pid, long runtime,
                      int acceptedCount, long inboundSpeed, long inboundAmount, long outboundSpeed,
                      long outboundAmount, List<RTMPApplication> applicationList) {
        this.nginxVersion = nginxVersion;
        this.nginxRTMPVersion = nginxRTMPVersion;
        this.compiler = compiler;
        this.builtTime = builtTime;
        this.pid = pid;
        this.runtime = runtime;
        this.acceptedCount = acceptedCount;
        this.inboundSpeed = inboundSpeed;
        this.inboundAmount = inboundAmount;
        this.outboundSpeed = outboundSpeed;
        this.outboundAmount = outboundAmount;
        this.applicationList = applicationList;
    }

    public void setNginxVersion(String nginxVersion) {
        this.nginxVersion = nginxVersion;
    }

    public String getNginxRTMPVersion() {
        return nginxRTMPVersion;
    }

    public void setNginxRTMPVersion(String nginxRTMPVersion) {
        this.nginxRTMPVersion = nginxRTMPVersion;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public String getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(String builtTime) {
        this.builtTime = builtTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public int getAcceptedCount() {
        return acceptedCount;
    }

    public void setAcceptedCount(int acceptedCount) {
        this.acceptedCount = acceptedCount;
    }

    public long getInboundSpeed() {
        return inboundSpeed;
    }

    public void setInboundSpeed(long inboundSpeed) {
        this.inboundSpeed = inboundSpeed;
    }

    public long getInboundAmount() {
        return inboundAmount;
    }

    public void setInboundAmount(long inboundAmount) {
        this.inboundAmount = inboundAmount;
    }

    public long getOutboundSpeed() {
        return outboundSpeed;
    }

    public void setOutboundSpeed(long outboundSpeed) {
        this.outboundSpeed = outboundSpeed;
    }

    public long getOutboundAmount() {
        return outboundAmount;
    }

    public void setOutboundAmount(long outboundAmount) {
        this.outboundAmount = outboundAmount;
    }

    public List<RTMPApplication> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<RTMPApplication> applicationList) {
        this.applicationList = applicationList;
    }
}
