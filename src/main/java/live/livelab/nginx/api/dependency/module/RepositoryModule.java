package live.livelab.nginx.api.dependency.module;

import com.google.inject.AbstractModule;
import live.livelab.nginx.api.entity.IRTMPServerRepository;
import live.livelab.nginx.api.repository.local.xml.interfaces.*;
import live.livelab.nginx.api.repository.rpc.interfaces.IRTMPServerRPCRepository;

/**
 * Created by kevin on 17/1/26.
 */
public class RepositoryModule extends AbstractModule{
    @Override
    protected void configure() {

        //  xml
        bind(IAudioMetadataConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.AudioMetadataConvertor.class);
        bind(IRTMPApplicationConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.RTMPApplicationConvertor.class);
        bind(IRTMPClientConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.RTMPClientConvertor.class);
        bind(IRTMPMetadataConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.RTMPMetadataConvertor.class);
        bind(IRTMPServerConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.RTMPServerConvertor.class);
        bind(IRTMPStreamConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.RTMPStreamConvertor.class);
        bind(IVideoMetadataConvertor.class).to(live.livelab.nginx.api.repository.local.xml.implementation.VideoMetadataConvertor.class);

        //  rpc
        bind(IRTMPServerRPCRepository.class).to(live.livelab.nginx.api.repository.rpc.implementation.RTMPRepository.class);

        //  cache
        bind(IRTMPServerRepository.class).to(live.livelab.nginx.api.repository.cache.implementation.RTMPServerRepository.class);
    }
}