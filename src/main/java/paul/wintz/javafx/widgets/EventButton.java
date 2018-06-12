package paul.wintz.javafx.widgets;

import javafx.scene.control.Button;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.utils.logging.Lg;

public class EventButton extends Button {

    public void setOption(EventOption option) {
        this.setOnAction(event -> {
            Lg.v("EventButton", "button pressed");
            option.triggerEvent();
        });
    }
}
