package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.SpirotechnicControlsPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class CircleControlsListView extends ListView<SpirotechnicControlsPresenter.CircleControlsPresenter.View> {
    private static final String TAG = Lg.makeTAG(CircleControlsView.class);

    public CircleControlsListView() {
        setCellFactory(param -> new ViewListCell());
    }

    private static class ViewListCell extends ListCell<SpirotechnicControlsPresenter.CircleControlsPresenter.View> {

        @Override protected void updateItem(SpirotechnicControlsPresenter.CircleControlsPresenter.View item, boolean empty) {
            super.updateItem(item, empty);
            Lg.v(TAG, "Update item to " + item);
            item.setVelocityOption(IntegerOption.builder().increment(5).build());
        }

    }
}
