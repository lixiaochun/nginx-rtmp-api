package live.livelab.nginx.api.repository.rpc.implementation;

import live.livelab.infrastructure.exception.RepositoryException;
import live.livelab.nginx.api.repository.rpc.interfaces.IRTMPServerRPCRepository;
import live.livelab.nginx.api.repository.rpc.messaging.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.text.MessageFormat;

/**
 * Created by kevin on 17/1/26.
 */
public class RTMPRepository extends Repository implements IRTMPServerRPCRepository {

    private String getNginxRTMPStatisticsUrl() {
        return applicationSettings.getNginxRTMPStatisticsXmlUrl();
    }

    @Override
    public String getStatisticsXml() throws RepositoryException {
        String url = this.getNginxRTMPStatisticsUrl();
        if(StringUtils.isEmpty(url)){
            throw new RepositoryException("url for retrieving statistics xml is empty");
        }
        HttpResponse httpResponse = super.getData(url);
        if(httpResponse == null || httpResponse.getStatusCode() != HttpStatus.SC_OK){
            if(httpResponse != null){
                logger.error(MessageFormat.format("An error occurred while requesting url [{0}], status code: [{1}], text: [{2}]",
                        url, httpResponse.getStatusCode(), httpResponse.getResponseText()));
            }
            return null;
        }
        String responseText = httpResponse.getResponseText();
        return responseText;
    }
}
