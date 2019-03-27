package paul.wintz.javafx.widgets;

import javafx.scene.control.TextField;
import paul.wintz.uioptiontypes.values.DoubleEquationOption;

public class EquationField extends TextField {

    public void setOption(DoubleEquationOption option){
        setText(option.value.equation);
        textProperty().addListener((observable, oldValue, newValue)
                -> option.emitEquationChanged(newValue));
    }

}
