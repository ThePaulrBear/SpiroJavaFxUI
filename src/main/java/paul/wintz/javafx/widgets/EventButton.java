package paul.wintz.javafx.widgets;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.utils.logging.Lg;

public class EventButton extends Button {

    public void setOption(EventOption option) {
        this.setOnAction(event -> {
            Lg.v("EvenButton", "button pressed");
            option.triggerEvent();
        });
    }
}
