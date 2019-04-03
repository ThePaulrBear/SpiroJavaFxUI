package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import paul.wintz.parametricequationdrawer.MainPresenter;
import paul.wintz.utils.logging.Lg;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

//TODO: Rename to FXViews
public class SpiroOptionsJavaFX extends Stage  {
    private static final String TAG = Lg.makeTAG(SpiroOptionsJavaFX.class);

    private final ControlsRoot controlsRoot;

    public SpiroOptionsJavaFX() throws IOException {
        FXMLLoader rootLoader = new FXMLLoader();

        rootLoader.setLocation(getClass().getResource("/controlsRoot.fxml"));

        Parent root = rootLoader.load();
        StackPane pane = new StackPane();
        pane.getChildren().setAll(root);
        setScene(new Scene(pane));

        controlsRoot = checkNotNull(rootLoader.getController());

        show();
    }

    public MainPresenter.View getSpiroUserInterface() {
        return controlsRoot;
    }

}
