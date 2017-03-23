package live.livelab.nginx.api.dependency.module;

import com.google.inject.AbstractModule;
import live.livelab.configuration.IAppSettings;

/**
 * Created by kevin on 17/1/26.
 */
public class AppSettingsModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(IAppSettings.class).to(live.livelab.configuration.AppSettings.class);
    }
}
