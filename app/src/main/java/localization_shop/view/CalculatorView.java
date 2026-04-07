package localization_shop.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class CalculatorView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlURL = getClass().getResource("/GUI.fxml");
        if (fxmlURL == null) {
            throw new IllegalStateException("Could not load /GUI.fxml from classpath.");
        }

        Scene scene = new Scene(FXMLLoader.load(fxmlURL));
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.show();
    }
}
