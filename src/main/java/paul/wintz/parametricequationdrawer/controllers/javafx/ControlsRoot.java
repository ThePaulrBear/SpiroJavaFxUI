package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import paul.wintz.parametricequationdrawer.SpiroUserInterface;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;

public class ControlsRoot extends TabPane implements SpiroUserInterface {

    @FXML private Tab canvasControlsView;
    @FXML private CanvasControlsView canvasControlsViewController;
    @FXML private Tab animationControlsView;
    @FXML private AnimationControlsView animationControlsViewController;
    @FXML private Tab spirotechnicControlsView;
    @FXML private SpirotechnicControlsView spirotechnicControlsViewController;

    @Override
    public CanvasControlsPresenter.View getCanvasController() {
        return canvasControlsViewController;
    }

    @Override
    public AnimationControlsPresenter.View getAnimationController() {
        return animationControlsViewController;
    }

    @Override
    public AnimationControlsPresenter.SavingControlsView getSavingView() {
        return animationControlsViewController;
    }

    @Override
    public SpirotechnicControlsPresenter.View getSpirotechnicControlsView() {
        return spirotechnicControlsViewController;
    }
}
