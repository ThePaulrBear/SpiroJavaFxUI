package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.BooleanToggle;
import paul.wintz.javafx.widgets.ColorSelector;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.parametricequationdrawer.controllers.CircleOverlayControlsPresenter;
import paul.wintz.uioptiontypes.values.BooleanOption;
import paul.wintz.uioptiontypes.values.ColorOption;
import paul.wintz.uioptiontypes.values.IntegerOption;

public class CircleOverlayControlsView implements CircleOverlayControlsPresenter.View {

    @FXML private BooleanToggle showCircles;
    @FXML private BooleanToggle showRadii;
    @FXML private BooleanToggle showInnerRadii;
    @FXML private IntegerSlider circleStrokeWeight;
    @FXML private IntegerSlider tracerDotSize;
    @FXML private ColorSelector circleColor;

    @Override
    public void setShowCirclesOption(BooleanOption showCirclesOption) {
        showCircles.setOption(showCirclesOption);
    }

    @Override
    public void setShowRadii(BooleanOption showRadiiOption) {
        showRadii.setOption(showRadiiOption);
    }

    @Override
    public void setShowInnerRadii(BooleanOption showInnerRadiiOption) {
        showInnerRadii.setOption(showInnerRadiiOption);
    }

    @Override
    public void setCircleStrokeWeight(IntegerOption circleStrokeWeightOption) {
        circleStrokeWeight.setOption(circleStrokeWeightOption);
    }

    @Override
    public void setTracerDotSize(IntegerOption tracerDotSizeOption) {
        tracerDotSize.setOption(tracerDotSizeOption);
    }

    @Override
    public void setCircleColor(ColorOption circleColorOption) {
        circleColor.setOption(circleColorOption);
    }
}
