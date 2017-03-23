package live.livelab.nginx.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class RTMPApplication implements Serializable{

    private static final long serialVersionUID = 7918819028825817463L;
    private String name;
    private int clientCount;
    private List<RTMPStream> streamList;

    public RTMPApplication(String name, int clientCount, List<RTMPStream> streamList) {
        this.name = name;
        this.clientCount = clientCount;
        this.streamList = streamList;
    }

    public RTMPApplication() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public List<RTMPStream> getStreamList() {
        return streamList;
    }

    public void setStreamList(List<RTMPStream> streamList) {
        this.streamList = streamList;
    }
}
