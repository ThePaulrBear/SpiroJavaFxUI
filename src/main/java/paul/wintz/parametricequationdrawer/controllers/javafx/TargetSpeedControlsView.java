package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSlider;
import paul.wintz.sourcefactories.TargetSpeedController;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class TargetSpeedControlsView implements TargetSpeedController.View {
    private static final String TAG = Lg.makeTAG(TargetSpeedControlsView.class);

    @FXML private IntegerSlider targetSpeed;

    @Override
    public void setTargetSpeedOption(IntegerOption targetSpeedOption) {
        Lg.v(TAG, "setTargetSpeedOption(%s)", targetSpeedOption);
        targetSpeed.setOption(targetSpeedOption);
    }
}
