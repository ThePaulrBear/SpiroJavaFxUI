package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.javafx.widgets.StringField;
import paul.wintz.sourcefactories.instantdrawer.LinesPresenter;
import paul.wintz.uioptiontypes.values.EquationOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

public class LinesControlsView implements LinesPresenter.View {
    private static final String TAG = Lg.makeTAG(LinesControlsView.class);

    @FXML private IntegerSlider segments;
    @FXML private StringField direction;
    @FXML private StringField lineStart;
    @FXML private StringField lineEnd;

    @Override
    public void setSegmentsOption(IntegerOption segmentsOption) {
        segments.setOption(segmentsOption);
    }

    @Override
    public void setShapeOption(ListOption<LinesPresenter.StrokeShape> shapeOption) {

    }

    @Override
    public void setDirectionOption(EquationOption directionOption) {
        direction.setOption(directionOption);
    }

    @Override
    public void setLineStartOption(EquationOption lineStartOption) {
        lineStart.setOption(lineStartOption);
    }

    @Override
    public void setLineEndOption(EquationOption lineEndOption) {
        lineEnd.setOption(lineEndOption);
    }
}
