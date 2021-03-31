package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import paul.wintz.javafx.widgets.EventButton;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpirotechnicControlsView implements SpirotechnicControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(SpirotechnicControlsView.class);

    @FXML private IntegerSpinner circleCount;
    @FXML private EventButton randomize;
    @FXML private EventButton previousGraph;
    @FXML private EventButton nextGraph;
    @FXML private VBox circlesColumn;

    private List<SpirotechnicControlsPresenter.CircleControlsPresenter.View> circleViews = new ArrayList<>();

    @Override
    public void setCircleCountOption(IntegerOption circleCountOption) {
        circleCount.setOption(circleCountOption);
    }

    @Nonnull
    @Override
    public List<SpirotechnicControlsPresenter.CircleControlsPresenter.View> setCircleCount(int count) {
        while(circleViews.size() > count){
            removeCircle();
        }
        while(circleViews.size() < count){
            addCircle();
        }
        return circleViews;
    }

    private void addCircle() {
        FXMLLoader circleControlsLoader = new FXMLLoader();
        circleControlsLoader.setLocation(getClass().getResource("/circleControlsView.fxml"));
        try {
            Parent load = circleControlsLoader.load();
            circlesColumn.getChildren().add(load);
            CircleControlsView controller = circleControlsLoader.getController();
            int index = circleViews.size();
            controller.setIndex(index);
            circleViews.add(controller);
        } catch (IOException e) {
            Lg.e(TAG, "Failed to load circleControls", e);
            throw new RuntimeException(e);
        }
    }

    private void removeCircle() {
        Lg.v(TAG, "Removing a circle. ");
        ObservableList<Node> children = circlesColumn.getChildren();
        int last = children.size() - 1;
        children.remove(last);
        circleViews.remove(last);
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
