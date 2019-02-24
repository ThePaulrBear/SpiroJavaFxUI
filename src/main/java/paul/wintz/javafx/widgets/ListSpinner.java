package paul.wintz.javafx.widgets;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import paul.wintz.uioptiontypes.values.ListOption;

public class ListSpinner<T> extends ComboBox<T> {


    public void setOption(ListOption<T> option) {
        valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    option.emitViewValueChanged(getValue());
                    setValue(null);
                }
        );
        setItems(FXCollections.observableArrayList(option.getList()));

    }

    public void setStringConverter(StringConverter<T> stringConverter) {
        setConverter(stringConverter);
    }

//    public void setValue(T value) {
//        valueFactory.setValue(value);
//    }

}
