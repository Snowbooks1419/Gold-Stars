package goldstars;

import goldstars.browser.Browser;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Faizaan Datoo
 */
public class GoldStars extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Browser browser;
    ApplicationManager apps;

    @Override
    public void start(Stage primaryStage) throws Exception {
        apps = new ApplicationManager();

        browser = new Browser();
        apps.registerApp("browser", browser);

        apps.openApp("browser");
    }
}
