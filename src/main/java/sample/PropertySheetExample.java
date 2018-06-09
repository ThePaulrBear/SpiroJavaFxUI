package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.DefaultPropertyEditorFactory;
import org.controlsfx.property.editor.Editors;
import org.controlsfx.property.editor.PropertyEditor;
import paul.wintz.utils.logging.Lg;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class PropertySheetExample extends VBox {
    private static final String TAG = Lg.makeTAG(PropertySheetExample.class);
    private static Map<String, Object> customDataMap = new LinkedHashMap<>();
    static {
        customDataMap.put("Group 1#My Text1", "Same text"); // Creates a TextField in property sheet
        customDataMap.put("Group 1#My Text2", "Same text"); // Creates a TextField in property sheet
        customDataMap.put("Group 1#My Text3", "Same text"); // Creates a TextField in property sheet
        customDataMap.put("Group 1#My Text4", "Same text"); // Creates a TextField in property sheet
        customDataMap.put("Group 1#My Date", LocalDate.of(2000, Month.JANUARY, 1)); // Creates a DatePicker
        //customDataMap.put("Group 2#My Enum Choice", SomeEnumType.EnumValue); // Creates a ChoiceBox
//        customDataMap.put("Group 2#My Boolean", false); // Creates a CheckBox
//        customDataMap.put("Group 2#My Number", 500); // Creates a NumericField
    }

    private class CustomPropertyItem implements PropertySheet.Item {
        private String key;
        private String category, name;
        private ObservableValue<?> value;
        private PropertyEditor<?> numericEditor;

        private CustomPropertyItem(String key, Object object) {
            this.key = key;
            String[] skey = key.split("#");
            category = skey[0];
            name = skey[1];
            if(object instanceof String){
                value = new SimpleStringProperty((String) object);
            } else if (object instanceof Integer){
                value = new SimpleIntegerProperty((Integer) object);
                numericEditor = Editors.createNumericEditor(this);
            }
        }

        @Override
        public Class<?> getType() {
            return customDataMap.get(key).getClass();
        }

        @Override
        public String getCategory() {
            return category;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return "A description";
        }

        @Override
        public Object getValue() {
            return customDataMap.get(key);
        }

        @Override
        public void setValue(Object value) {
            customDataMap.put(key, value);
        }

        @Override
        public Optional<ObservableValue<?>> getObservableValue() {
            return Optional.ofNullable(value);
        }
    }

        PropertySheet propertySheet;
    public PropertySheetExample() {
        ObservableList<PropertySheet.Item> list = FXCollections.observableArrayList();
        for (String key : customDataMap.keySet()) {
            list.add(new CustomPropertyItem(key, customDataMap.get(key)));
        }

        propertySheet = new PropertySheet(list);
        VBox.setVgrow(propertySheet, Priority.ALWAYS);
        getChildren().add(propertySheet);

        propertySheet.setPropertyEditorFactory(param -> {
            if (param.getValue() instanceof Boolean) {
                return Editors.createCheckEditor(param);
            } else if (param.getValue() instanceof Integer) {
                return Editors.createNumericEditor(param);
            } else {
                return Editors.createTextEditor(param);
            }
        });

        SimpleObjectProperty<Callback<PropertySheet.Item, PropertyEditor<?>>> propertyEditorFactory
                = new SimpleObjectProperty<>(this, "propertyEditor", new DefaultPropertyEditorFactory());
        propertySheet.setPropertyEditorFactory(getItemPropertyEditorCallback(propertyEditorFactory));
    }

    private Callback<PropertySheet.Item, PropertyEditor<?>> getItemPropertyEditorCallback(SimpleObjectProperty<Callback<PropertySheet.Item, PropertyEditor<?>>> propertyEditorFactory) {
        return param -> {
            PropertyEditor<?> editor = propertyEditorFactory.get().call(param);

            //Add listeners to editor
            editor.getEditor().focusedProperty().addListener((observable, oldValue, newValue) ->
                    Lg.d(TAG, "Old: " + oldValue + ", new: "+ newValue));

//            editor.getEditor().addListener((observable, oldValue, newValue) -> {
//                Lg.e(TAG, "onKeyTypedProperty: Old: " + oldValue + ", new: "+ newValue);
//            });
            return editor;
        };
    }
}