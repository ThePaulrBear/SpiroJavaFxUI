package paul.wintz.javafx.widgets;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import paul.wintz.uioptiontypes.values.ListOption;
import paul.wintz.utils.logging.Lg;

public class DropdownSelector<T> extends ComboBox<T> {
    private final static String TAG = Lg.makeTAG(DropdownSelector.class);

    private ListOption<T> listOption;

    public DropdownSelector() {
        super();
    }

    public void setOption(ListOption<T> listOption) {
        this.listOption = listOption;

        // Setup the listener to handle when an item is selected.
        getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    listOption.emitViewValueChanged(newValue);
                }
        );

        // Setup the tooltip if a description is provided.
        String description = listOption.getDescription();
        if(description != null) {
            setTooltip(new Tooltip(description));
        }

        // Configure the display of items in the dropdown.
        setCellFactory(lv -> createCell());
        // Configure the display of the button that is clicked to open the drop down.
        setButtonCell(createCell());

        for(T choice : listOption.getList()) {
            this.getItems().add(choice);
        }

        getSelectionModel().select(listOption.getValue());
        listOption.emitViewValueChanged(getValue());
    }

    private ListCell<T> createCell() {
        return new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(listOption.displayName(item));
                }
            }
        };
    }
}

