package paul.wintz.javafx.widgets;

import javafx.scene.control.Slider;
import paul.wintz.uioptiontypes.values.FloatOption;

public class FloatSlider extends Slider {

    public void setOption(FloatOption option) {
        setValue(option.getValue());
        setBlockIncrement(option.increment);
        setMin(option.min);
        setMax(option.max);
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(newValue.floatValue()));

    }

}
