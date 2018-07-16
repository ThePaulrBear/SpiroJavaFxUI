package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.math.Vector2D;
import paul.wintz.sourcefactories.*;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

import javax.sound.sampled.Line;

public class LinesControlsView implements LinesPresenter.View {
    private static final String TAG = Lg.makeTAG(LinesControlsView.class);

    @FXML private IntegerSlider segments;

    @Override
    public void setSegmentsOption(IntegerOption segmentsOption) {
        segments.setOption(segmentsOption);
    }

    @Override
    public void setShapeOption(ListOption<LinesPresenter.StrokeShape> shapeOption) {

    }

    private int i = 0;

    @Override
    public void setPositionConsumer(VectorConsumer positionConsumer) {
        positionConsumer.setSource(() -> {
            i++;
            Vector2D vector2D = Vector2D.fromPolar(i * 0.01, 100);
            //Lg.v(TAG, "position: %s", vector2D);
            return vector2D;
        });
    }

    @Override
    public void setDirectionDoubleConsumer(DoubleConsumer directionDoubleConsumer) {
        directionDoubleConsumer.setSource(() -> 0);
    }

    @Override
    public void setStrokeWeightDoubleConsumer(DoubleConsumer strokeWeightDoubleConsumer) {
        strokeWeightDoubleConsumer.setSource(() -> 3);
    }

    @Override
    public void setStrokeColorDoubleConsumer(ColorConsumer strokeColorDoubleConsumer) {
        strokeColorDoubleConsumer.setSource(() -> 0x55ff0000);
    }

    @Override
    public void setLineStartDoubleConsumer(DoubleConsumer lineStartDoubleConsumer) {
        lineStartDoubleConsumer.setSource(() -> -2);
    }

    @Override
    public void setLineEndDoubleConsumer(DoubleConsumer lineEndDoubleConsumer) {
        lineEndDoubleConsumer.setSource(() -> 1);
    }
}
