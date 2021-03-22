package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.*;
import paul.wintz.mvp.Presenter;
import paul.wintz.mvp.PresenterFactoryPresenter;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.DrawerControlsPresenter;
import paul.wintz.sourcefactories.SpirotechnicGraphablePresenter;
import paul.wintz.spirotechnics.generalizedspirotechnic.EllipsesSpirotechnicGraphable;
import paul.wintz.typefactory.TypeFactory;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.*;
import paul.wintz.utils.logging.Lg;

import static com.google.common.base.Preconditions.checkNotNull;

public class CanvasControlsView implements CanvasControlsPresenter.View, DrawerControlsPresenter.View  {
    private static final String TAG = Lg.makeTAG(CanvasControlsView.class);

    @FXML IntegerSpinner width;
    @FXML IntegerSpinner height;
    @FXML FloatSlider zoom;
    @FXML FloatSlider centerX;
    @FXML FloatSlider centerY;
    @FXML StringField rotation;
    @FXML ColorSelector background;
    @FXML EventButton clear;
    @FXML EventButton reset;
    @FXML BooleanToggle preserveGraph;
    @FXML BooleanToggle clearEveryFrame;

    //FIXME these should be loaded in FXML
    InstantDrawerPresenterSelectionView instantDrawerFactoryView;
    FrameDrawerPresenterSelectionView frameDrawerFactoryView;

    @Override
    public void setWidthOption(IntegerOption widthOption) {
        width.setOption(widthOption);
    }

    @Override
    public void setHeightOption(IntegerOption heightOption) {
        height.setOption(heightOption);
    }

    @Override
    public void setZoomOption(FloatOption zoomOption) {
        zoom.setOption(zoomOption);
    }

    @Override public void setCenterXOption(FloatOption centerX) {
        this.centerX.setOption(centerX);
    }

    @Override public void setCenterYOption(FloatOption centerY) {
        this.centerY.setOption(centerY);
    }

    @Override public void setRotationOption(StringOption rotationOption) {
        this.rotation.setOption(rotationOption);
    }

    @Override public void setBackgroundColorOption(ColorOption backgroundOption) {
        background.setOption(backgroundOption);
    }

    @Override public void setPreserveGraphOption(BooleanOption option) {
        preserveGraph.setOption(option);
    }

    @Override public void setClearAllOption(EventOption option) {
        clear.setOption(option);
    }

    @Override public void setResetOption(EventOption option) {
        reset.setOption(option);
    }

    @Override
    public void setWidth(int width) {
        Lg.v(TAG, "setWidth(%d)", width);
        this.width.setValue(width);
    }

    @Override
    public void setHeight(int height) {
        Lg.v(TAG, "setHeight(%d)", height);
        this.width.setValue(height);
    }

    @Override
    public void setZoom(float zoom) {
        this.zoom.setValue(zoom);
    }

    @Override
    public void setCenterX(float centerX) {
        Lg.v(TAG, "setCenterX(%.2f)", centerX);
        this.centerX.setValue(centerX);
    }

    @Override
    public void setCenterY(float centerY) {
        Lg.v(TAG, "setCenterY(%.2f)", centerY);
        this.centerY.setValue(centerY);
    }

    @Override
    public void setRotation(String index) {
        Lg.v(TAG, "setRotation(%d)", index);
        rotation.setValue(index);
    }

    @Override
    public void setBackground(int rgba) {
        background.setValue(rgba);
    }

    @Override
    public void setBackground(int red, int green, int blue, int alpha) {
        background.setValue(red, green, blue, alpha);
    }

    @Override
    public void setPreserveGraph(boolean preserveGraph) {
        this.preserveGraph.setValue(preserveGraph);
    }

    @Override
    public boolean getPreserveGraph() {
        return preserveGraph.getValue();
    }

    @Override
    public void setClearEveryFrameOption(BooleanOption clearEveryFrame) {
        this.clearEveryFrame.setOption(clearEveryFrame);
    }

    @Override
    public void setZeroTimeOption(EventOption zeroTimeOption) {
//        zeroTime.setOption(zeroTimeOption);
    }

    @Override
    public void setAbortFrameOption(EventOption abortFrameOption) {
//        abortFrame.setOption(abortFrameOption);
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
}
