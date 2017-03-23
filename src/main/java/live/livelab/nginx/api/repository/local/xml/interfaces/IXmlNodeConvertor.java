package live.livelab.nginx.api.repository.local.xml.interfaces;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public interface IXmlNodeConvertor<T extends Serializable> {
    T convertFromXmlNode(Node xmlNode);
    default List<T> convertFromXmlNodeList(NodeList nodeList){
        if(nodeList == null || nodeList.getLength() == 0){
            return null;
        }
        List<T> itemList = new ArrayList<>();
        T item;
        for(int  i = 0; i < nodeList.getLength(); i++){
            item = this.convertFromXmlNode(nodeList.item(i));
            if(item != null){
                itemList.add(item);
            }
        }
        return itemList;
    }
}
