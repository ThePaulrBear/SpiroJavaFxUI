package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import paul.wintz.javafx.widgets.*;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

import java.io.IOException;
import java.util.Stack;

public class SpirotechnicControlsView implements SpirotechnicControlsPresenter.View {
        private static final String TAG = Lg.makeTAG(SpirotechnicControlsView.class);

    @FXML private IntegerSpinner circleCount;
    @FXML private EventButton randomize;
    @FXML private EventButton previousGraph;
    @FXML private EventButton nextGraph;
    @FXML private VBox circlesColumn;


    @Override
    public void setCircleCountOption(IntegerOption circleCountOption) {
        circleCount.setOption(circleCountOption);
    }

    @Override
    public SpirotechnicControlsPresenter.CircleControlsPresenter.View addCircle() {
        FXMLLoader circleControlsLoader = new FXMLLoader();
        circleControlsLoader.setLocation(getClass().getResource("/circleControlsView.fxml"));
        try {
            Parent load = circleControlsLoader.load();
            circlesColumn.getChildren().add(load);
            return circleControlsLoader.<CircleControlsView>getController();
        } catch (IOException e) {
            Lg.e(TAG, "Failed to load circleControls", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeCircle() {
        ObservableList<Node> children = circlesColumn.getChildren();
        int last = children.size() - 1;
        children.remove(last);
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

}
