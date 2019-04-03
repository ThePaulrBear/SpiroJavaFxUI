package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.sourcefactories.frametransitioners.TracerOffsetPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class TracerOffsetControlsView implements TracerOffsetPresenter.View {
    private static final String TAG = Lg.makeTAG(TracerOffsetControlsView.class);

    @FXML private IntegerSlider min;
    @FXML private IntegerSlider max;

    @Override
    public void setMaxOption(IntegerOption maxOption) {
        max.setOption(maxOption);
    }

    @Override
    public void setMinOption(IntegerOption minOption) {
        min.setOption(minOption);
    }
}
