package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.sourcefactories.TargetInstantsPerCycle;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class TargetCountSpeedControlsView implements TargetInstantsPerCycle.View {
    private static final String TAG = Lg.makeTAG(TargetCountSpeedControlsView.class);

    @FXML private IntegerSpinner count;

    @Override
    public void setCountOption(IntegerOption countOption) {
        Lg.v(TAG, "setCountOption(%s)", countOption);
        count.setOption(countOption);
    }
}
