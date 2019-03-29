package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.sourcefactories.framedrawer.StandardDrawerPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class StandardDrawerControlsView implements StandardDrawerPresenter.View {
    private static final String TAG = Lg.makeTAG(StandardDrawerControlsView.class);

    @FXML private IntegerSpinner instantsPerFrame;

    @Override
    public void setInstantsPerFrameOption(IntegerOption instantsPerFrameOption) {
        instantsPerFrame.setOption(instantsPerFrameOption);
    }
}
