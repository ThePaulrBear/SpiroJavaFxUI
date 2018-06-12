package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;

public class SpiroRoot extends TabPane {

    @FXML private Tab canvasControlsView;
    @FXML private CanvasControlsView canvasControlsViewController;
    @FXML private Tab animationControlsView;
    @FXML private AnimationControlsView animationControlsViewController;

    public CanvasControlsView getCanvasControlsView() {
        return canvasControlsViewController;
    }

    public AnimationControlsView getAnimationControlsViewController() {
        return animationControlsViewController;
    }

    public AnimationControlsPresenter.SavingControlsView getSavingControlsViewController() {
        return animationControlsViewController;
    }
}
