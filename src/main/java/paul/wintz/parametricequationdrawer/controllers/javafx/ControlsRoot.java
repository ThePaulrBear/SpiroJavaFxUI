package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import paul.wintz.parametricequationdrawer.MainPresenter;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.DrawerControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.StyleControlsPresenter;
import paul.wintz.sourcefactories.speedcontroller.SpeedControllerPresenter;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class ControlsRoot extends TabPane implements MainPresenter.View {

    // WARNING: The naming of the controllers is important!
    // They must be named "<name_of_view>Controller".
    @FXML private Tab canvasControlsView;
    @FXML private CanvasControlsView canvasControlsViewController;
    @FXML private Tab animationControlsView;
    @FXML private AnimationControlsView animationControlsViewController;
    @FXML private Tab spirotechnicControlsView;
    @FXML private SpirotechnicControlsView spirotechnicControlsViewController;
    @FXML private Tab speedControlsView;
    @FXML private SpeedControlsController speedControlsViewController;
    @FXML private Tab instantDrawerFactoryView;
    @FXML private InstantDrawerPresenterSelectionView instantDrawerFactoryViewController;
    @FXML private Tab drawerControlsView;
    @FXML private DrawerControlsView drawerControlsViewController;
    @FXML private Tab styleControlsView;
    @FXML private StyleControlsView styleControlsViewController;

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
        drawerControlsViewController.instantDrawerFactoryView = checkNotNull(instantDrawerFactoryViewController);
        return checkNotNull(drawerControlsViewController);
    }

    @Override
    public StyleControlsPresenter.View getStyleControlsView() {
        return checkNotNull(styleControlsViewController);
    }

}
