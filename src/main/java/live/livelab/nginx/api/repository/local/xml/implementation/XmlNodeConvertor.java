package live.livelab.nginx.api.repository.local.xml.implementation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by zhouxiang on 1/26/2017.
 */
public class XmlNodeConvertor {
    protected static final Logger logger = LoggerFactory.getLogger(RTMPServerConvertor.class);

    protected XPath xPath;

    public XmlNodeConvertor(XPath xPath) {
        this.xPath = xPath;
    }

    public XmlNodeConvertor() {
        //  create XPathFactory object
        XPathFactory xPathFactory = XPathFactory.newInstance();
        //  create XPath object
        this.xPath = xPathFactory.newXPath();
    }

    protected XPath getXPath() {
        return this.xPath;
    }

    protected Document valueOf(String xmlContent) {
        if (StringUtils.isEmpty(xmlContent)) {
            return null;
        }
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(inputStream);
        } catch (Exception e) {
            logger.error("An error occurred while convert document from xml", e);
        }
        return doc;
    }

    protected String getXpathExpressionValueAsString(Node node, XPath xpath, String xpathExpression) {
        if (node == null || xpath == null || xpathExpression == null || xpathExpression.isEmpty()) {
            return null;
        }
        Object result = null;
        try {
            XPathExpression expression = xpath.compile(xpathExpression);
            result = expression.evaluate(node, XPathConstants.STRING);
        } catch (Exception e) {
            logger.error("An error occurred while compiling xpath: " + xpathExpression, e);
        }
        return result == null ? null : result.toString();
    }

    protected Node getXpathExpressionValueAsNode(Node node, XPath xpath, String xpathExpression){
        if (node == null || xpath == null || xpathExpression == null || xpathExpression.isEmpty()) {
            return null;
        }
        Object result = null;
        try {
            XPathExpression expression = xpath.compile(xpathExpression);
            result = expression.evaluate(node, XPathConstants.NODE);
        } catch (Exception e) {
            logger.error("An error occurred while compiling xpath: " + xpathExpression, e);
        }
        return result == null ? null : (Node)result;
    }

    protected Node getXpathExpressionValueAsNode(Node node,String xpathExpression){
        return this.getXpathExpressionValueAsNode(node, this.xPath, xpathExpression);
    }

    protected NodeList getXpathExpressionValueAsNodeList(Node node, XPath xpath, String xpathExpression){
        if (node == null || xpath == null || xpathExpression == null || xpathExpression.isEmpty()) {
            return null;
        }
        Object result = null;
        try {
            XPathExpression expression = xpath.compile(xpathExpression);
            result = expression.evaluate(node, XPathConstants.NODESET);
        } catch (Exception e) {
            logger.error("An error occurred while compiling xpath: " + xpathExpression, e);
        }
        return result == null ? null : (NodeList)result;
    }

    protected NodeList getXpathExpressionValueAsNodeList(Node node,String xpathExpression){
        return this.getXpathExpressionValueAsNodeList(node, this.xPath, xpathExpression);
    }


    protected String getXpathExpressionStringValue(Node node, XPath xpath, String xpathExpression) {
        String obj = getXpathExpressionValueAsString(node, xpath, xpathExpression);
        return obj;
    }

    protected int getXpathExpressionIntValue(Node node, XPath xpath, String xpathExpression) {
        String obj = getXpathExpressionValueAsString(node, xpath, xpathExpression);
        return obj == null ? -1 : NumberUtils.toInt(obj);
    }

    protected long getXpathExpressionLongValue(Node node, XPath xpath, String xpathExpression) {
        String obj = getXpathExpressionValueAsString(node, xpath, xpathExpression);
        return obj == null ? -1 : NumberUtils.toLong(obj);
    }

    protected Object getXpathExpressionValueAsString(Node node, String xpathExpression){
        return this.getXpathExpressionValueAsString(node, this.xPath, xpathExpression);
    }

    protected String getXpathExpressionStringValue(Node node, String xpathExpression){
        return this.getXpathExpressionStringValue(node, this.xPath, xpathExpression);
    }

    protected long getXpathExpressionLongValue(Node node, String xpathExpression){
        return this.getXpathExpressionLongValue(node, this.xPath, xpathExpression);
    }

    protected int getXpathExpressionIntValue(Node node, String xpathExpression){
        return this.getXpathExpressionIntValue(node, this.xPath, xpathExpression);
    }
}
