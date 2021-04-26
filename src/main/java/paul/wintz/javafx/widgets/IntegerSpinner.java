package paul.wintz.javafx.widgets;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import paul.wintz.nodes.FXUtils;
import paul.wintz.uioptiontypes.values.IntegerOption;

public class IntegerSpinner extends Spinner<Integer> {

    private SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;

    public void setOption(IntegerOption option) {
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(getValue()));
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(option.min, option.max);
        valueFactory.setAmountToStepBy(option.increment);
        valueFactory.setValue(option.getValue());
        setValueFactory(valueFactory);

        setEditable(true);

        // Because Editable is true, we have to manually control in/decrementing when up/down is pressed.
        getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                increment();
            } else if (event.getCode() == KeyCode.DOWN) {
                decrement();
            }
        });

        // Setup the UI element to reflect changes to the model.
        option.addModelValueChangeCallback(this::setValue);
    }

    public void setValue(int value) {
        FXUtils.runOnApplicationThread(() -> {
            valueFactory.setValue(value);
        });
    }

}
