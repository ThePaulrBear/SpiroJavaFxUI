package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.sourcefactories.frametransitioners.InitialContactOffsetPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class InitialContactOffsetControlsView implements InitialContactOffsetPresenter.View {
    private static final String TAG = Lg.makeTAG(InitialContactOffsetControlsView.class);

    @FXML private IntegerSlider circle;

    @Override
    public void setCircleOption(IntegerOption circleOption) {
        circle.setOption(circleOption);
    }

}
