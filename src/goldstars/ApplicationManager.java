package goldstars;

import java.io.File;
import java.util.HashMap;

/**
 * Contains references to all applications.
 *
 * @author Faizaan Datoo
 */
public class ApplicationManager {

    HashMap<String, Application> applications;

    private File rootFolder;
    private File appdata;
    HashMap<String, AppConfig> appConfigurations;

    public ApplicationManager() {
        // Initialize files
        rootFolder = new File(System.getProperty("user.home") + "/Gold-Stars");
        if(!rootFolder.exists()) rootFolder.mkdir();
        appdata = new File(rootFolder, "AppData");
        if(!appdata.exists()) appdata.mkdir();

        applications = new HashMap<>();
        appConfigurations = new HashMap<>();
    }

    /**
     * Registers an application with an AppConfig and a session.
     *
     * @param app The Application object to initialize.
     */
    public void registerApp(String name, Application app) {
        app.setConfig(getConfig(name));
        applications.put(name, app);
    }

    /**
     * Opens an app.
     *
     * @param name The name of the app.
     */
    public void openApp(String name) {
        if (!applications.containsKey(name)) return;
        applications.get(name).open();
    }

    /**
     * Closes an app.
     *
     * @param name The name of the app.
     */
    public void closeApp(String name) {
        if (!applications.containsKey(name)) return;
        applications.get(name).close();
    }

    /**
     * Get the configuration of the app. If one does not exist, it will be created.
     *
     * @param name The name of the app.
     * @return An AppConfig object.
     */
    public AppConfig getConfig(String name) {
        if (!appConfigurations.containsKey(name)) {
            // Create a new AppConfig if it doesn't exist for that app
            AppConfig conf = new AppConfig(new File(appdata, name));
            appConfigurations.put(name, conf);
        }
        return appConfigurations.get(name);
    }

}
