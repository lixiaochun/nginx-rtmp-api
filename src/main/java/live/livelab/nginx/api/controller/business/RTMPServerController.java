package live.livelab.nginx.api.controller.business;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import live.livelab.infrastructure.messaging.ResponseBase;
import live.livelab.nginx.api.controller.BaseController;
import live.livelab.nginx.api.dependency.DependencyResolver;
import live.livelab.nginx.api.service.interfaces.IRTMPApplicationService;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.interfaces.IRTMPStreamService;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationRequest;
import live.livelab.nginx.api.service.messaging.rtmpApplicationService.GetRTMPApplicationResponse;
import live.livelab.nginx.api.service.messaging.rtmpServerService.*;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamRequest;
import live.livelab.nginx.api.service.messaging.rtmpStreamService.GetRTMPStreamResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

/**
 * Created by kevin on 17/1/30.
 */
@RestController
@RequestMapping("/rtmp-server")
@CrossOrigin(origins = "*")
public class RTMPServerController extends BaseController {

    @ApiOperation(value = "get Nginx-rtmp server details", notes = "get nginx-rtmp server details, data is cached in service", response = GetRTMPServerResponse.class)
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public Callable<GetRTMPServerResponse> json() {
        return () -> {
            IRTMPServerService service = DependencyResolver.getInstance(IRTMPServerService.class);
            GetRTMPServerRequest request = new GetRTMPServerRequest();
            GetRTMPServerResponse response = service.getRTMPServer(request);
            return response;
        };
    }

    @ResponseBody
    @ApiOperation(value = "get Nginx-rtmp server details", notes = "get nginx-rtmp server details, data is cached in service", response = String.class)
    @RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public Callable<String> xml() {
        return () -> {
            IRTMPServerService service = DependencyResolver.getInstance(IRTMPServerService.class);
            GetRTMPServerStatisticsXmlRequest request = new GetRTMPServerStatisticsXmlRequest(
                    RTMPServerStatisticsXmlSource.Cache
            );
            GetRTMPServerStatisticsXmlResponse response = service.getRTMPServerStatisticsXml(request);
            String xml = response != null && response.isSuccess() ? response.getStatisticsXml() : null;
            return xml;
        };
    }

    @ResponseBody
    @ApiOperation(value = "get Nginx-rtmp server details from Nginx-rtmp server directly", notes = "data is not cached", response = String.class)
    @RequestMapping(value = "/xml-rpc", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public Callable<String> xmlViaRPC() {
        return () -> {
            IRTMPServerService service = DependencyResolver.getInstance(IRTMPServerService.class);
            GetRTMPServerStatisticsXmlRequest request = new GetRTMPServerStatisticsXmlRequest(
                    RTMPServerStatisticsXmlSource.RPC
            );
            GetRTMPServerStatisticsXmlResponse response = service.getRTMPServerStatisticsXml(request);
            String xml = response != null && response.isSuccess() ? response.getStatisticsXml() : null;
            return xml;
        };
    }

    @ResponseBody
    @ApiOperation(value = "get Nginx-rtmp application details", notes = "get Nginx-rtmp application details using application from url in path", response = GetRTMPApplicationResponse.class)
    @ApiImplicitParam(name = "application", value = "RTMP application", required = true, paramType = "path", dataType = "string")
    @RequestMapping(value = "/{application}", method = RequestMethod.GET)
    public Callable<GetRTMPApplicationResponse> getRTMPApplication(
            @PathVariable(name = "application", required = true)
                    String applicationName
    ) {
        return () -> {
            IRTMPApplicationService service = DependencyResolver.getInstance(IRTMPApplicationService.class);
            GetRTMPApplicationRequest request = new GetRTMPApplicationRequest(applicationName);
            GetRTMPApplicationResponse response = service.getRTMPApplication(request);
            return response;
        };
    }

    @ResponseBody
    @ApiOperation(value = "get Nginx-rtmp RTMP Stream details", notes = "get Nginx-rtmp RTMP Stream details using application & stream from url in path", response = GetRTMPStreamResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "application", value = "RTMP application", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "stream", value = "RTMP stream", required = true, paramType = "path", dataType = "string")
    })
    @RequestMapping(value = "/{application}/{stream}", method = RequestMethod.GET)
    public Callable<GetRTMPStreamResponse> getRTMPStream(
            @PathVariable(name = "application", required = true)
                    String applicationName,
            @PathVariable(name = "stream", required = true)
                    String streamName
    ) {
        return () -> {
            IRTMPStreamService service = DependencyResolver.getInstance(IRTMPStreamService.class);
            GetRTMPStreamRequest request = new GetRTMPStreamRequest(applicationName, streamName);
            GetRTMPStreamResponse response = service.getRTMPStream(request);
            return response;
        };
    }

    @ResponseBody
    @ApiOperation(hidden = true, value = "drop Nginx-rtmp RTMP Stream", notes = "drop Nginx-rtmp RTMP Stream using application & stream from url in path", response = GetRTMPStreamResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "application", value = "RTMP application", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "stream", value = "RTMP stream", required = true, paramType = "path", dataType = "string")
    })
    @RequestMapping(value = "/{application}/{stream}", method = RequestMethod.DELETE)
    public Callable<ResponseBase> dropRTMPStream(
            @PathVariable(name = "application", required = true)
                    String applicationName,
            @PathVariable(name = "stream", required = true)
                    String streamName
    ) {
        return () -> {
            ResponseBase response = new ResponseBase(true, "ok");
            return response;
        };
    }
}
