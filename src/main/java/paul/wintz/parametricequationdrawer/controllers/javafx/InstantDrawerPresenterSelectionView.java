package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.LoaderView;
import paul.wintz.sourcefactories.instantdrawer.LinesPresenter;
import paul.wintz.typefactory.TypeFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class InstantDrawerPresenterSelectionView extends FXPresenterSelectionView {

    @FXML LoaderView loaderView;

    private final TypeFactory viewFactory = TypeFactory.builder()
            .putType(LinesPresenter.View.class, () -> getView("/linesControlsView.fxml"))
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
