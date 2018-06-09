package paul.wintz.javafx.widgets;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import paul.wintz.uioptiontypes.integers.IntegerOption;

public class IntegerSpinner extends Spinner<Integer> {

    private SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;

    public void setOption(IntegerOption option) {
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(option.min, option.max);
        valueFactory.setAmountToStepBy(option.increment);
        setValueFactory(valueFactory);

        valueProperty().addListener((observable, oldValue, newValue)
                -> option.viewValueChangeCallback.callback(getValue()));
    }

    public void setValue(int value) {
        valueFactory.setValue(value);
    }

}
