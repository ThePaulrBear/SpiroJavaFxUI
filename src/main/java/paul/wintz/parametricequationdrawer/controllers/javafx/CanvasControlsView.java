package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.*;
import paul.wintz.parametricequationdrawer.controllers.CanvasControlsPresenter;
import paul.wintz.uioptiontypes.BooleanOption;
import paul.wintz.uioptiontypes.ColorOption;
import paul.wintz.uioptiontypes.FloatOption;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.integers.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class CanvasControlsView implements CanvasControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(CanvasControlsView.class);

    @FXML IntegerSpinner size;
    @FXML FloatSlider centerX;
    @FXML FloatSlider centerY;
    @FXML IntegerSlider rotationIndex;
    @FXML ColorSelector background;
    @FXML EventButton clear;
    @FXML EventButton reset;
    @FXML BooleanToggle preserveGraph;

    @Override public void setSizeOption(IntegerOption sizeOption) {
        size.setOption(sizeOption);
    }

    @Override public void setCenterXOption(FloatOption centerX) {
        this.centerX.setOption(centerX);
    }

    @Override public void setCenterYOption(FloatOption centerY) {
        this.centerY.setOption(centerY);
    }

    @Override public void setRotationOption(IntegerOption rotationOption) {
        this.rotationIndex.setOption(rotationOption);
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
    public void setSize(int size) {
        Lg.v(TAG, "setSize(%d)", size);
        this.size.setValue(size);
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
    public void setRotation(int index) {
        Lg.v(TAG, "setRotation(%d)", index);
        rotationIndex.setValue(index);
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

}
