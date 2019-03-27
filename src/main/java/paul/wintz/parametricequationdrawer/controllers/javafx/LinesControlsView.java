package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.EquationField;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.sourcefactories.instantdrawer.LinesPresenter;
import paul.wintz.uioptiontypes.values.DoubleEquationOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

public class LinesControlsView implements LinesPresenter.View {
    private static final String TAG = Lg.makeTAG(LinesControlsView.class);

    @FXML private IntegerSlider segments;
    @FXML private EquationField direction;
    @FXML private EquationField lineStart;
    @FXML private EquationField lineEnd;

    @Override
    public void setSegmentsOption(IntegerOption segmentsOption) {
        segments.setOption(segmentsOption);
    }

    @Override
    public void setShapeOption(ListOption<LinesPresenter.StrokeShape> shapeOption) {

    }

    @Override
    public void setDirectionOption(DoubleEquationOption directionOption) {
        direction.setOption(directionOption);
    }

    @Override
    public void setLineStartOption(DoubleEquationOption lineStartOption) {
        lineStart.setOption(lineStartOption);
    }

    @Override
    public void setLineEndOption(DoubleEquationOption lineEndOption) {
        lineEnd.setOption(lineEndOption);
    }
}
