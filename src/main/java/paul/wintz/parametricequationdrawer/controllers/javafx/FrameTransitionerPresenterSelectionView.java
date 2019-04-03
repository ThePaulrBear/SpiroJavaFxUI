package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.LoaderView;
import paul.wintz.sourcefactories.frametransitioners.InitialContactOffsetPresenter;
import paul.wintz.sourcefactories.frametransitioners.TracerOffsetPresenter;
import paul.wintz.typefactory.TypeFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class FrameTransitionerPresenterSelectionView extends FXPresenterSelectionView {

    @FXML LoaderView loaderView;

    private final TypeFactory viewFactory = TypeFactory.builder()
            .putType(InitialContactOffsetPresenter.View.class, () -> getView("/initialContactOffsetControlsView.fxml"))
            .putType(TracerOffsetPresenter.View.class, () -> getView("/tracerOffsetControlsView.fxml"))
            .onVoidSelectedAction(this::clearView)
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
