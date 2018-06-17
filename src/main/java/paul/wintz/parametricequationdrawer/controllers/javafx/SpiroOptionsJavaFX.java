package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import paul.wintz.parametricequationdrawer.SpiroUserInterface;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

//TODO: Rename to FXViews
public class SpiroOptionsJavaFX extends Stage implements SpiroUserInterface {

    private final CanvasControlsPresenter.View canvasControls;
    private final AnimationControlsPresenter.View animationControls;
    private final AnimationControlsPresenter.SavingControlsView saveControls;
    private final SpirotechnicControlsPresenter.View spirotechnicControlsView;

    public SpiroOptionsJavaFX() throws IOException {
        FXMLLoader rootLoader = new FXMLLoader();

        rootLoader.setLocation(getClass().getResource("/controlsRoot.fxml"));

        Parent root = rootLoader.load();
        StackPane pane = new StackPane();
        pane.getChildren().setAll(root);
        setScene(new Scene(pane));

        ControlsRoot controlsRoot = checkNotNull(rootLoader.getController());
        canvasControls = controlsRoot.getCanvasController();
        animationControls = controlsRoot.getAnimationController();
        saveControls = controlsRoot.getSavingView();
        spirotechnicControlsView = controlsRoot.getSpirotechnicControlsView();

        show();
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

    @Override
    public SpirotechnicControlsPresenter.View getSpirotechnicControlsView() {
        return spirotechnicControlsView;
    }

}
