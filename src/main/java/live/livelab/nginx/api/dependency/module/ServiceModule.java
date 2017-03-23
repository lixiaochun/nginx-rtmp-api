package live.livelab.nginx.api.dependency.module;

import com.google.inject.AbstractModule;
import live.livelab.nginx.api.service.implementation.RTMPApplicationService;
import live.livelab.nginx.api.service.implementation.RTMPServerService;
import live.livelab.nginx.api.service.implementation.RTMPStreamService;
import live.livelab.nginx.api.service.interfaces.IRTMPApplicationService;
import live.livelab.nginx.api.service.interfaces.IRTMPServerService;
import live.livelab.nginx.api.service.interfaces.IRTMPStreamService;

/**
 * Created by kevin on 17/1/26.
 */
public class ServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(IRTMPServerService.class).to(RTMPServerService.class);
        bind(IRTMPStreamService.class).to(RTMPStreamService.class);
        bind(IRTMPApplicationService.class).to(RTMPApplicationService.class);
    }
}
