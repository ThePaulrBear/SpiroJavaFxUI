package paul.wintz.javafx.widgets;

import javafx.scene.control.Slider;
import paul.wintz.uioptiontypes.values.FloatOption;

public class FloatSlider extends Slider {

    public void setOption(FloatOption option) {
        // Set the initial value.
        setValue(option.getValue());

        // Set configurations.
        setBlockIncrement(option.increment);
        setMin(option.min);
        setMax(option.max);

        // Setup the UI element to send changes (made by the user) to the StringOption.
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(newValue.floatValue()));

        // Setup the UI element to reflect changes to the model.
        option.addModelValueChangeCallback(this::setValue);
    }

}
