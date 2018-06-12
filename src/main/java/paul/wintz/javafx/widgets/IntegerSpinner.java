package paul.wintz.javafx.widgets;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import paul.wintz.uioptiontypes.integers.IntegerOption;

public class IntegerSpinner extends Spinner<Integer> {

    private SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;

    public void setOption(IntegerOption option) {
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(getValue()));
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(option.min, option.max);
        valueFactory.setAmountToStepBy(option.increment);
        valueFactory.setValue(option.initial);
        setValueFactory(valueFactory);
    }

    public void setValue(int value) {
        valueFactory.setValue(value);
    }

}
