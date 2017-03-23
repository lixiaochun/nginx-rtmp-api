package live.livelab.nginx.api.repository.local.xml.interfaces;

import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public interface IXmlDocumentConvertor<T extends Serializable> {
    T convertFromXml(String xmlDocument);
    T convertFromXml(Document document);
}
