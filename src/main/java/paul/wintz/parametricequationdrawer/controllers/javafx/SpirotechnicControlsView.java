package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import paul.wintz.javafx.widgets.EventButton;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.javafx.widgets.StringField;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.AbstractSpirotechnicControlsView;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.StringOption;
import paul.wintz.utils.logging.Lg;

import javax.annotation.Nonnull;
import java.io.IOException;

public class SpirotechnicControlsView extends AbstractSpirotechnicControlsView<CircleControlsView> implements SpirotechnicControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(SpirotechnicControlsView.class);

    @FXML private IntegerSpinner circleCount;
    @FXML private EventButton randomize;
    @FXML private EventButton previousGraph;
    @FXML private EventButton nextGraph;
    @FXML private VBox circlesColumn;
    @FXML private StringField tracerRadialOffset;
    @FXML public StringField tracerOffsetY;

    @Override
    public void setCircleCountOption(IntegerOption circleCountOption) {
        circleCount.setOption(circleCountOption);
    }

    @Override
    protected void runSetCircleCountRunnable(Runnable setCircleCountRunnable) {
        if (Platform.isFxApplicationThread()) {
            setCircleCountRunnable.run();
        } else {
            Platform.runLater(setCircleCountRunnable);
        }
    }

    @Override
    @Nonnull
    protected CircleControlsView loadCircleControlsView() {
        FXMLLoader circleControlsLoader = new FXMLLoader();
        circleControlsLoader.setLocation(getClass().getResource("/circleControlsView.fxml"));
        try {
            Parent load = circleControlsLoader.load();
            circlesColumn.getChildren().add(load);
            CircleControlsView controller = circleControlsLoader.getController();
            controller.setIndex(getCircleViewsCount());
            return controller;
        } catch (IOException e) {
            Lg.e(TAG, "Failed to load circleControls", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void removeLastCircleControlsView() {
        Lg.v(TAG, "Removing a circle. ");
        ObservableList<Node> children = circlesColumn.getChildren();
        children.remove(getCircleViewsCount() - 1);
    }

    @Override
    public void setRandomizeOption(EventOption randomizeOption) {
        randomize.setOption(randomizeOption);
    }

    @Override
    public void setLoadNextGraphOption(EventOption nextGraphOption) {
        nextGraph.setOption(nextGraphOption);
    }

    @Override
    public void setLoadPreviousGraphOption(EventOption previousGraphOption) {
        previousGraph.setOption(previousGraphOption);
    }

    @Override
    public void setTracerRadialOffsetEquation(StringOption tracerRadialOffsetOption) {
        tracerRadialOffset.setOption(tracerRadialOffsetOption);
    }

    @Override
    public void setTracerTangentOffsetEquation(StringOption tracerTangentOffsetOption) {
//        Not yet implemented
//        tracerTangentOffset.setOption(tracerTangentOffsetOption);
    }

}
