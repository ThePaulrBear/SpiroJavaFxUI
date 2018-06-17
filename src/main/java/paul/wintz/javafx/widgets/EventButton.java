package paul.wintz.javafx.widgets;

import javafx.scene.control.Button;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.utils.logging.Lg;

public class EventButton extends Button {
    private static final String TAG = Lg.makeTAG(EventButton.class);

    public void setOption(EventOption option) {
        this.setOnAction(event -> {
            Lg.v(TAG, "%s button pressed", getText());
            option.triggerEvent();
        });
    }
}
