package paul.wintz.javafx.widgets;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.controlsfx.control.ToggleSwitch;
import paul.wintz.uioptiontypes.BooleanOption;
import paul.wintz.utils.logging.Lg;

public class BooleanToggle extends ToggleSwitch {

    public void setOption(BooleanOption option){
        setSelected(option.initial);
        selectedProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(newValue));
    }

    public void setValue(boolean value) {
        setSelected(value);
    }

    public boolean getValue() {
        return isSelected();
    }

}
