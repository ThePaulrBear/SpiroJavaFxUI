package paul.wintz.javafx.widgets;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;
import paul.wintz.uioptiontypes.values.ListOption;

@Deprecated
public class ListSelector<T> extends ListView<T> {

    public ListSelector() {
        super();
    }

    public void setOption(ListOption<T> option) {
        getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> option.emitViewValueChanged(newValue)
        );


        setTooltip(new Tooltip(option.getDescription()));

        setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                return new ListCell<T>(){
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);

                        if(empty) {
                            setText("");
                        } else {
                            setText(option.displayName(item));
                        }
                    }
                };
            }
        });
    }

}

