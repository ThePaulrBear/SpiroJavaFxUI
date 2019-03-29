package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.FloatSlider;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.mvp.Presenter;
import paul.wintz.mvp.PresenterFactoryPresenter;
import paul.wintz.sourcefactories.framedrawer.SnakeDrawerPresenter;
import paul.wintz.typefactory.TypeFactory;
import paul.wintz.uioptiontypes.values.FloatOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

public class SnakeDrawerControlsView implements SnakeDrawerPresenter.View {
    private static final String TAG = Lg.makeTAG(SnakeDrawerControlsView.class);

    @FXML private IntegerSlider snakeCount;
    @FXML private FloatSlider offset;

    @Override
    public void setSnakeCountOption(IntegerOption snakeCountOption) {
        snakeCount.setOption(snakeCountOption);
    }

    @Override
    public void setOffsetOption(FloatOption offsetOption) {
        offset.setOption(offsetOption);
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getSnakeModesPresenterFactoryView() {
        return new PresenterFactoryPresenter.PresenterSelectionView() {
            @Override
            public TypeFactory getViewFactory() {
                return TypeFactory.builder()
                        .putType(Void.class, () -> null)
                        .putType(SnakeDrawerPresenter.FixedLengthSnake.View.class, () -> null)
                        .build();
            }

            @Override
            public void setPresentersOption(ListOption<Class<? extends Presenter<?>>> presenterChoices) {

            }
        };
    }

    @Override
    public TypeFactory getViewFactory() {
        return TypeFactory.builder()
                .putType(Void.class, () -> null)
                .build();
    }

    @Override
    public void setPresentersOption(ListOption<Class<? extends Presenter<?>>> presenterChoices) {

    }
}
