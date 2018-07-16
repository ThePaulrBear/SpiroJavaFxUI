package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.math.Vector2D;
import paul.wintz.sourcefactories.ColorConsumer;
import paul.wintz.sourcefactories.DotDrawerPresenter;
import paul.wintz.sourcefactories.DoubleConsumer;
import paul.wintz.sourcefactories.VectorConsumer;
import paul.wintz.utils.color.ColorUtils;
import paul.wintz.utils.logging.Lg;

public class DotsDrawerControlsView implements DotDrawerPresenter.View {
    private static final String TAG = Lg.makeTAG(LinesControlsView.class);

    @FXML private IntegerSlider segments;

    private int i = 0;

    @Override
    public void setPositionConsumer(VectorConsumer positionConsumer) {
        positionConsumer.setSource(() -> {
            i++;
            return Vector2D.fromPolar(i * 0.01, 100);
        });
    }

    @Override
    public void setSizeConsumer(DoubleConsumer sizeConsumer) {
        sizeConsumer.setSource(() -> 1.0);
    }

    @Override
    public void setStrokeColorConsumer(ColorConsumer strokeColorConsumer) {
        strokeColorConsumer.setSource(() -> ColorUtils.GREEN);
    }

    @Override
    public void setFillColorConsumer(ColorConsumer fillColorConsumer) {
        fillColorConsumer.setSource(() -> 0xff44ff22);
    }

    @Override
    public void setStrokeWeightConsumer(DoubleConsumer strokeWeightConsumer) {
        strokeWeightConsumer.setSource(() -> 3.0);
    }

}
