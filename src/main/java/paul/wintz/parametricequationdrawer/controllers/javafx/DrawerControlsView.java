package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.BooleanToggle;
import paul.wintz.javafx.widgets.EventButton;
import paul.wintz.mvp.Presenter;
import paul.wintz.parametricequationdrawer.controllers.DrawerControlsPresenter;
import paul.wintz.mvp.PresenterFactoryPresenter;
import paul.wintz.sourcefactories.*;
import paul.wintz.spirotechnics.generalizedspirotechnic.EllipsesSpirotechnicGraphable;
import paul.wintz.typefactory.TypeFactory;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.BooleanOption;
import paul.wintz.uioptiontypes.values.FloatOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.ListOption;

import static com.google.common.base.Preconditions.checkNotNull;

public class DrawerControlsView implements DrawerControlsPresenter.View {

    @FXML private BooleanToggle clearEveryFrame;
    @FXML private EventButton zeroTime;
    @FXML private EventButton abortFrame;
    //FIXME these should be loaded in FXML
    SpeedControllerPresenterSelectionView speedControllerFactoryView;
    InstantDrawerPresenterSelectionView instantDrawerFactoryView;
    private PresenterFactoryPresenter.PresenterSelectionView frameDrawerFactoryView = new FakeFrameDrawerPresenterSelectionView();

    @Override
    public void setClearEveryFrameOption(BooleanOption clearEveryFrame) {
        this.clearEveryFrame.setOption(clearEveryFrame);
    }

    @Override
    public void setZeroTimeOption(EventOption zeroTimeOption) {
        zeroTime.setOption(zeroTimeOption);
    }

    @Override
    public void setAbortFrameOption(EventOption abortFrameOption) {
        abortFrame.setOption(abortFrameOption);
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getSpeedControllerFactoryView() {
        return checkNotNull(speedControllerFactoryView);
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getInstantDrawerFactoryView() {
        return checkNotNull(instantDrawerFactoryView);
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getGraphableFactoryView() {
        return new FakeGraphablePresenterSelectionView();
    }

    @Override
    public PresenterFactoryPresenter.PresenterSelectionView getFrameDrawerFactoryView() {
        return checkNotNull(frameDrawerFactoryView);
    }

    private static class FakeGraphablePresenterSelectionView implements PresenterFactoryPresenter.PresenterSelectionView {
        @Override
        public TypeFactory getViewFactory() {
            return TypeFactory.builder()
                    .putType(SpirotechnicGraphablePresenter.View.class, () -> {throw new RuntimeException();})
                    .putType(EllipsesSpirotechnicGraphable.View.class, () -> {throw new RuntimeException();})
                    .build();
        }

        @Override
        public void setPresentersOption(ListOption<Class<? extends Presenter<?>>> presenterChoices) {

        }
    }

    private static class FakeFrameDrawerPresenterSelectionView implements PresenterFactoryPresenter.PresenterSelectionView {
        @Override
        public TypeFactory getViewFactory() {
            return TypeFactory.builder()
                    .putType(StandardDrawer.View.class, () -> {throw new RuntimeException();})
                    .putType(FinishGraph.View.class, () -> {throw new RuntimeException();})
                    .putType(SnakeDrawer.View.class, () -> {throw new RuntimeException();})
                    .putType(Void.class, () -> null)
                    .build();
        }

        @Override
        public void setPresentersOption(ListOption<Class<? extends Presenter<?>>> presenterChoices) {

        }
    }
}
