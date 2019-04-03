package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.LoaderView;
import paul.wintz.sourcefactories.framedrawer.SnakeDrawerPresenter;
import paul.wintz.sourcefactories.framedrawer.StandardDrawerPresenter;
import paul.wintz.typefactory.TypeFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class FrameDrawerPresenterSelectionView extends FXPresenterSelectionView {

    @FXML LoaderView loaderView;

    private final TypeFactory viewFactory = TypeFactory.builder()
            .putType(StandardDrawerPresenter.View.class, () -> getView("/standardDrawerControlsView.fxml"))
            .putType(SnakeDrawerPresenter.View.class, () -> getView("/snakeDrawerControlsView.fxml"))
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
