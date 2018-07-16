package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.LoaderView;
import paul.wintz.sourcefactories.TargetInstantsPerCycle;
import paul.wintz.sourcefactories.TargetSpeedController;
import paul.wintz.typefactory.TypeFactory;
import paul.wintz.utils.logging.Lg;

import static com.google.common.base.Preconditions.checkNotNull;

public class SpeedControllerPresenterSelectionView extends FXPresenterSelectionView {
    private static final String TAG = Lg.makeTAG(SpeedControllerPresenterSelectionView.class);

    @FXML LoaderView loaderView = new LoaderView();

    private TypeFactory viewFactory = TypeFactory.builder()
            .putType(TargetSpeedController.View.class, () -> getView("/targetSpeedControlsView.fxml"))
            .putType(TargetInstantsPerCycle.View.class, () -> getView("/targetCountSpeedControlsView.fxml"))
            .build();

    @Override
    protected LoaderView getLoaderView() {
        return checkNotNull(loaderView);
    }

    @Override
    public TypeFactory getViewFactory() {
        return viewFactory;
    }
}
