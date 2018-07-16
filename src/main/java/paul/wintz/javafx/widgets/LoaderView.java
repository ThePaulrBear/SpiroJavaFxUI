package paul.wintz.javafx.widgets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import paul.wintz.utils.logging.Lg;

import java.io.IOException;

public class LoaderView extends AnchorPane {
    private static final String TAG = Lg.makeTAG(LoaderView.class);

    public <V> V load(String resourceLocation) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(resourceLocation));
        try {
            Parent load = fxmlLoader.load();
            getChildren().add(load);
            return fxmlLoader.getController();
        } catch (IOException e) {
            Lg.e(TAG, "Failed to load "+ resourceLocation, e);
            throw new RuntimeException(e);
        }
    }

}
