package live.livelab.configuration;

/**
 * Created by kevin on 15/12/27.
 */
public class AppSettingsFactory {
    protected static IAppSettings appSettings;

    public static void initializeAppSettings(IAppSettings basicSettings){
        AppSettingsFactory.appSettings = basicSettings;
    }

    protected AppSettingsFactory() {
    }

    public static IAppSettings getAppSettings(){
        return AppSettingsFactory.appSettings;
    }
}
