package paul.wintz.javafx.widgets;

import org.controlsfx.control.ToggleSwitch;
import paul.wintz.nodes.FXUtils;
import paul.wintz.uioptiontypes.values.BooleanOption;

public class BooleanToggle extends ToggleSwitch {

    public void setOption(BooleanOption option){
        // Set the initial value.
        setSelected(option.getValue());

        // Setup the UI element to send changes (made by the user) to the BooleanToggle.
        selectedProperty().addListener((observable, oldValue, newValue) -> {
            option.emitViewValueChanged(newValue);
        });

        // Setup the UI element to reflect changes to the model.
        option.addModelValueChangeCallback(this::setValue);
    }

    public void setValue(boolean value) {
        FXUtils.runOnApplicationThread(() -> {
            setSelected(value);
        });
    }

    public boolean getValue() {
        return isSelected();
    }

}
