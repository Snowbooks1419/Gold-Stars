package goldstars.browser;

import goldstars.*;
import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.stage.Stage;

import java.util.regex.*;

public class Browser implements Application {
    public static final String DEFAULT_URL = "http://www.google.com/";
    public Stage stage;

    private WebEngine webEngine;
    private ContextMenu menu;
    private AppConfig config;

    public Browser() {
        stage = new Stage();
        stage.setTitle("GoldStars Browser");
    }

    @Override
    public void open() {
        init();
        stage.show();
    }

    @Override
    public void close() {
        stage.hide();
    }

    private void init() {

        Group root = new Group();
        stage.setScene(new Scene(root, 1280, 720));

        // Create menu
        final Menu menu1 = new Menu("GoldStars Browser");
        menu1.setStyle("-fx-pref-width: 150px; -fx-base: rgba(0, 0, 0, 0.75);");
        MenuItem menu1one = new MenuItem("Refresh");
        menu1one.setStyle("-fx-pref-width:150px; -fx-base: rgba(0, 0, 0, 0.75);");
        menu1one.setOnAction(event -> webEngine.reload());
        MenuItem menu1two = new MenuItem("Back");
        menu1two.setStyle("-fx-pref-width:150px; -fx-base: rgba(0, 0, 0, 0.75);");
        menu1two.setOnAction(event -> goBack());
        MenuItem menu1three = new MenuItem("Forward");
        menu1three.setStyle("-fx-pref-width:150px; -fx-base: rgba(0, 0, 0, 0.75);");
        menu1three.setOnAction(event -> goForward());
        menu1.getItems().addAll(menu1one, menu1two, menu1three);

        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-pref-width: " + stage.getScene().getWidth() + "px; -fx-base: rgb(0, 0, 0);");
        menuBar.getMenus().add(menu1);

        // Create webpage viewer
        WebView webView = new WebView();
        webView.setPrefSize(1280, 720);

        webEngine = webView.getEngine();
        webEngine.load(DEFAULT_URL);

        final TextField locationField = new TextField(DEFAULT_URL);
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                locationField.setText(newValue);
            }
        });

        EventHandler<ActionEvent> goAction = event -> webEngine.load(processUrl(locationField.getText()));

        locationField.setOnAction(goAction);

        Button goButton = new Button("Go");
        goButton.setDefaultButton(true);
        goButton.setOnAction(goAction);


        // Add everything to the screen
        HBox hBox = new HBox(5);
        hBox.getChildren().setAll(locationField, goButton);
        HBox.setHgrow(locationField, Priority.ALWAYS);

        VBox vBox = new VBox(5);
        vBox.getChildren().setAll(menuBar, hBox, webView);
        VBox.setVgrow(webView, Priority.ALWAYS);

        root.getChildren().add(vBox);


        // Create right-click menu (context menu)
        webView.setContextMenuEnabled(false);
        menu = new ContextMenu();
        MenuItem one = new MenuItem("Back");
        one.setOnAction(event -> goBack());
        MenuItem two = new MenuItem("Forward");
        two.setOnAction(event -> goForward());
        MenuItem three = new MenuItem("Refresh");
        three.setOnAction(event -> webEngine.reload());
        menu.getItems().addAll(one, two, three);

        webView.setOnMouseClicked(this::handleContextMenu);
    }

    public String goBack() {
        final WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();

        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    history.go(-1);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        });
        return entryList.get(currentIndex > 0 ? currentIndex - 1 : currentIndex).getUrl();
    }

    public String goForward() {
        try {
            final WebHistory history = webEngine.getHistory();
            ObservableList<WebHistory.Entry> entryList = history.getEntries();
            int currentIndex = history.getCurrentIndex();

            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        history.go(1);
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            });
            return entryList.get(currentIndex < entryList.size() - 1 ? currentIndex + 1 : currentIndex).getUrl();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private String processUrl(String input) {
        if (!input.startsWith("http://") && !input.startsWith("https://")) {
            input = "http://" + input;
        }
        Pattern p = Pattern.compile("(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");

        Matcher m = p.matcher(input);
        if (!m.matches()) {
            input = "http://www.google.com/search?q=" + input.replaceAll(" ", "+").replaceAll("http://", "");
        }
        return input;
    }

    private void handleContextMenu(MouseEvent mouse) {
        if (mouse.getButton() == MouseButton.SECONDARY) {
            menu.show(stage, mouse.getScreenX(), mouse.getScreenY());
        } else {
            menu.hide();
        }
    }

    @Override
    public void setConfig(AppConfig config) {
        this.config = config;
    }
}