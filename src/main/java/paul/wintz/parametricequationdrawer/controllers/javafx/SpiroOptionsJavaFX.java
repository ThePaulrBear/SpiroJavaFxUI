package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import paul.wintz.parametricequationdrawer.SpiroUserInterface;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.DrawerController;
import paul.wintz.uioptiontypes.FileOption;
import paul.wintz.uioptiontypes.events.EventOption;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static paul.wintz.utils.logging.Lg.makeTAG;

public class SpiroOptionsJavaFX extends Stage implements SpiroUserInterface {
    private static final String TAG = makeTAG(SpiroOptionsJavaFX.class);

    private final CanvasControlsPresenter.View canvasControls;
    private final AnimationControlsPresenter.View animationControls;
    private final AnimationControlsPresenter.SavingControlsView saveControls;

    public SpiroOptionsJavaFX() throws IOException {
        FXMLLoader canvasLoader = new FXMLLoader();
        FXMLLoader animationLoader = new FXMLLoader();
        FXMLLoader rootLoader = new FXMLLoader();

        URL canvasViewUrl = getClass().getResource("/canvasControlsView.fxml");
        canvasLoader.setLocation(canvasViewUrl);

        URL animationViewUrl = getClass().getResource("/animationControlsView.fxml");
        animationLoader.setLocation(animationViewUrl);

        URL rootUrl = getClass().getResource("/spiroRoot.fxml");
        rootLoader.setLocation(rootUrl);

        Parent root = rootLoader.load();
        StackPane pane = new StackPane();
        pane.getChildren().setAll(root);
        setScene(new Scene(pane));

        animationLoader.load();

        SpiroRoot spiroRoot = checkNotNull(rootLoader.<SpiroRoot>getController());

        canvasControls = spiroRoot.getCanvasControlsView();
        animationControls = spiroRoot.getAnimationControlsViewController();
        saveControls = spiroRoot.getSavingControlsViewController();

        show();
    }

    @Override
    public DrawerController getDrawerControllerPresenter() {
        return null;
    }

    @Override
    public CanvasControlsPresenter.View getCanvasController() {
        return canvasControls;
    }

    @Override
    public AnimationControlsPresenter.View getAnimationController() {
        return animationControls;
    }

    @Override
    public AnimationControlsPresenter.SavingControlsView getSavingView() {
        return saveControls;
    }
}
