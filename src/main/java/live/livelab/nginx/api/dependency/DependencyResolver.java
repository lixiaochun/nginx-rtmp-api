package live.livelab.nginx.api.dependency;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import live.livelab.nginx.api.dependency.module.AppSettingsModule;
import live.livelab.nginx.api.dependency.module.RepositoryModule;
import live.livelab.nginx.api.dependency.module.ServiceModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 17/1/26.
 */
public class DependencyResolver {

    private static void setInjector(Injector injector) {
        DependencyResolver.injector = injector;
    }

    private static Injector injector;

    public static void initializeResolver(){
        List<AbstractModule> modules = new ArrayList();
        modules.add(new RepositoryModule());
        modules.add(new ServiceModule());
        modules.add(new AppSettingsModule());
        Injector injector = Guice.createInjector(modules);
        setInjector(injector);
    }

    public static  <T> T getInstance(Class<T> input){
        if (injector == null){
            return null;
        }
        return injector.getInstance(input);
    }
}
