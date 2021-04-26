package paul.wintz.javafx.widgets;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import paul.wintz.nodes.FXUtils;
import paul.wintz.uioptiontypes.values.ColorOption;
import paul.wintz.utils.color.ColorUtils;

import static paul.wintz.utils.color.ColorUtils.*;

public class ColorSelector extends ColorPicker {

    public void setOption(ColorOption option){
        // Set the initial value.
        setValue(intToColor(option.getValue()));

        // Setup the UI element to send changes (made by the user) to the ColorSelector.
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(colorToInt(newValue)));

        // Setup the UI element to reflect changes to the model.
        option.addModelValueChangeCallback(rgba -> setValue(intToColor(rgba)));
    }

    private int colorToInt(Color color) {
        return ColorUtils.rgba(color.getRed(), color.getGreen(), color.getBlue(), 255 * color.getOpacity());
    }

    private static Color intToColor(int rgba) {
        return Color.rgb(red(rgba), green(rgba), blue(rgba), alpha(rgba) / 255.0);
    }

    public void setValue(int rgba) {
        FXUtils.runOnApplicationThread(() -> {
            setValue(intToColor(rgba));
        });
    }

    public void setValue(int red, int green, int blue, int alpha) {
        FXUtils.runOnApplicationThread(() -> {
            setValue(Color.rgb(red, green, blue, alpha));
        });
    }
}
