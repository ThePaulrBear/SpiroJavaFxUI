package paul.wintz.javafx.widgets;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import paul.wintz.uioptiontypes.values.ListOption;

public class ListSelector<T> extends ListView<T> {

    public ListSelector() {
        super();

    }

    public void setOption(ListOption<T> option) {
        getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                option.emitViewValueChanged(newValue);
            }
        });
    }

}

