package paul.wintz.javafx.widgets;

import org.controlsfx.control.ToggleSwitch;
import paul.wintz.uioptiontypes.values.BooleanOption;

public class BooleanToggle extends ToggleSwitch {

    public void setOption(BooleanOption option){
        setSelected(option.getValue());
        selectedProperty().addListener((observable, oldValue, newValue) ->
                option.emitViewValueChanged(newValue));
    }

    public void setValue(boolean value) {
        setSelected(value);
    }

    public boolean getValue() {
        return isSelected();
    }

}
