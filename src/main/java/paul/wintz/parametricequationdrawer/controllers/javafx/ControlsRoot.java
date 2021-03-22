package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import paul.wintz.mvp.PresenterFactoryPresenter;
import paul.wintz.parametricequationdrawer.MainPresenter;
import paul.wintz.parametricequationdrawer.controllers.*;
import paul.wintz.sourcefactories.speedcontroller.SpeedControllerPresenter;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class ControlsRoot extends TabPane implements MainPresenter.View {

    // WARNING: The naming of the controllers is important!
    // They must be named "<name_of_view>Controller".
    @FXML private Pane canvasControlsView;
    @FXML private CanvasControlsView canvasControlsViewController;
    @FXML private Pane animationControlsView;
    @FXML private AnimationControlsView animationControlsViewController;
    @FXML private Pane spirotechnicControlsView;
    @FXML private SpirotechnicControlsView spirotechnicControlsViewController;
    @FXML private Pane speedControlsView;
    @FXML private SpeedControlsController speedControlsViewController;
    @FXML private Pane instantDrawerFactoryView;
    @FXML private InstantDrawerPresenterSelectionView instantDrawerFactoryViewController;
    @FXML private Pane frameDrawerFactoryView;
    @FXML private FrameDrawerPresenterSelectionView frameDrawerFactoryViewController;
    @FXML private Pane drawerControlsView;
    @FXML private Pane styleControlsView;
    @FXML private StyleControlsView styleControlsViewController;
    @FXML private Pane frameTransitionerFactoryView;
    @FXML private PresenterFactoryPresenter.PresenterSelectionView frameTransitionerFactoryViewController;
    @FXML private Pane circleOverlayControlsView;
    @FXML private CircleOverlayControlsPresenter.View circleOverlayControlsViewController;

    @Override
    public CanvasControlsPresenter.View getCanvasController() {
        return checkNotNull(canvasControlsViewController);
    }

    @Override
    public AnimationControlsPresenter.View getAnimationController() {
        return checkNotNull(animationControlsViewController);
    }

    @Override
    public AnimationControlsPresenter.SavingControlsView getSavingView() {
        return checkNotNull(animationControlsViewController);
    }

    @Override
    public SpirotechnicControlsPresenter.View getSpirotechnicControlsView() {
        return checkNotNull(spirotechnicControlsViewController);
    }

    @Override
    public SpeedControllerPresenter.View getSpeedPresenterView() {
        return checkNotNull(speedControlsViewController);
    }

    @Override
    public DrawerControlsPresenter.View getDrawerControlsView() {
        canvasControlsViewController.instantDrawerFactoryView = checkNotNull(instantDrawerFactoryViewController);
        canvasControlsViewController.frameDrawerFactoryView = checkNotNull(frameDrawerFactoryViewController);
        return checkNotNull(canvasControlsViewController);
    }

    @Override
    public StyleControlsPresenter.View getStyleControlsView() {
        return checkNotNull(styleControlsViewController);
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getFrameTransitionerView() {
        return frameTransitionerFactoryViewController;
    }

    @Override
    public CircleOverlayControlsPresenter.View getCircleOverlayControlsView() {
        return circleOverlayControlsViewController;
    }

    @Override
    public SaveLocationPresenter.View getSaveLocationView() {
        return animationControlsViewController;
    }

}
