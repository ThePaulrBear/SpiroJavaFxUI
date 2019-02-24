package paul.wintz.javafx.widgets;

import javafx.scene.control.Slider;
import paul.wintz.uioptiontypes.values.IntegerOption;

public class IntegerSlider extends Slider {

    public void setOption(IntegerOption option) {
        setValue(option.value);
        setShowTickMarks(true);
        setMinorTickCount(0);
        setSnapToTicks(true);
        setMajorTickUnit(option.increment);
        setBlockIncrement(option.increment);
        setMin(option.min);
        setMax(option.max);
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(newValue.intValue()));
    }

}
