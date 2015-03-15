package goldstars;

import java.io.*;
import java.util.HashMap;

/**
 * Holds the settings and files for each application.
 *
 * @author Faizaan Datoo
 */
public class AppConfig {

    private File appFolder;

    private File configFile;
    private HashMap<String, Object> settings;
    private HashMap<String, File> files;

    public AppConfig(File appFolder) {
        this.appFolder = appFolder;
        if (!appFolder.exists()) appFolder.mkdir();

        this.configFile = new File(appFolder, "settings.txt");
        settings = new HashMap<>();
        files = new HashMap<>();
        load();
    }

    // Settings

    private boolean load() {
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("=");
                if (tokens.length > 0) {
                    settings.put(tokens[0], tokens[1]);
                } else {
                    settings.put(tokens[0], "");
                }
            }
            reader.close();
            return true;
        } catch (IOException e) {
            ErrorHandler.exception(e);
            return false;
        }
    }

    public boolean save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(configFile, false));
            for(String key : settings.keySet()) {
                writer.write(key + "=" + settings.get(key));
                writer.newLine();
            }
            writer.close();
            return true;
        } catch(IOException e) {
            ErrorHandler.exception(e);
            return false;
        }
    }

    public boolean reload() {
        return load();
    }

    public boolean set(String key, Object value) {
        if (settings.containsKey(key)) settings.remove(key);
        if(value == null) {
            settings.remove(key);
            return true;
        }
        settings.put(key, value);
        return save();
    }

    public Object get(String key) {
        return settings.get(key);
    }

    public int getInt(String key) {
        return (int) get(key);
    }

    public float getFloat(String key) {
        return (float) get(key);
    }

    public double getDouble(String key) {
        return (double) get(key);
    }

    public boolean getBoolean(String key) {
        return (boolean) get(key);
    }

    public String getString(String key) {
        return (String) get(key);
    }

    // Files

    public void addFile(String name) {
        File file = new File(appFolder, name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ErrorHandler.exception(e);
            }
        }
        files.put(name, file);
    }

    public File getFile(String key) {
        return files.get(key);
    }

}