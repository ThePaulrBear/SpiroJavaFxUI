package paul.wintz.javafx.widgets;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import paul.wintz.nodes.FXUtils;
import paul.wintz.uioptiontypes.values.StringOption;

public class StringField extends TextField {

    public void setOption(StringOption option){
        // Set the initial value.
        setText(option.getValue());


        String description = option.getDescription();
        if(description != null) {
            setTooltip(new Tooltip(description));
        }

        // Setup the UI element to send changes (made by the user) to the StringOption.
        textProperty().addListener((observable, oldValue, newValue)
                -> option.emitViewValueChanged(newValue));

        // Setup the UI element to reflect changes to the model.
        option.addModelValueChangeCallback(this::setText);
    }

    public void setValue(String value) {
        FXUtils.runOnApplicationThread(() -> {
            setText(value);
        });
    }

}
