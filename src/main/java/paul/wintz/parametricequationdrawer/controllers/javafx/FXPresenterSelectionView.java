package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.DropdownSelector;
import paul.wintz.javafx.widgets.LoaderView;
import paul.wintz.mvp.Presenter;
import paul.wintz.mvp.PresenterFactoryPresenter;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static paul.wintz.utils.logging.Lg.makeTAG;

public abstract class FXPresenterSelectionView implements PresenterFactoryPresenter.PresenterSelectionView {
    private static final String TAG = makeTAG(FXPresenterSelectionView.class);

    @FXML DropdownSelector<Class<? extends Presenter<?>>> listOfPresenters;

    protected abstract LoaderView getLoaderView();

    @Nonnull
    final <V> V getView(String location) {
        Lg.v(TAG, "getView(%s)", location);
        return checkNotNull(getLoaderView().load(location));
    }

    void clearView() {
        getLoaderView().getChildren().clear();
    }

    @Override
    public final void setPresentersOption(ListOption<Class<? extends Presenter<?>>> presenterChoices) {
        Lg.v(TAG, "setPresentersOption(%s)", presenterChoices);
        listOfPresenters.setOption(presenterChoices);
    }

}
