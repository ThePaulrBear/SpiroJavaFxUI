package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.javafx.widgets.StringField;
import paul.wintz.sourcefactories.framedrawer.StandardDrawerPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.uioptiontypes.values.StringOption;
import paul.wintz.utils.logging.Lg;

public class StandardDrawerControlsView implements StandardDrawerPresenter.View {
    private static final String TAG = Lg.makeTAG(StandardDrawerControlsView.class);

    @FXML private IntegerSpinner instantsPerFrame;
    @FXML private StringField visibleStart;
    @FXML private StringField visibleEnd;
    @FXML private StringField gridStart;
    @FXML private StringField gridEnd;

    @Override
    public void setCountOption(IntegerOption count) {
        instantsPerFrame.setOption(count);
    }

    @Override
    public void setVisibleStartOption(StringOption visibleStartOption) {
        visibleStart.setOption(visibleStartOption);
    }

    @Override
    public void setVisibleEndOption(StringOption visibleEndOption) {
        visibleEnd.setOption(visibleEndOption);
    }

    @Override
    public void setGridStartOption(StringOption gridStartOption) {
        gridStart.setOption(gridStartOption);
    }

    @Override
    public void setGridEndOption(StringOption gridEndOption) {
        gridEnd.setOption(gridEndOption);
    }
}
