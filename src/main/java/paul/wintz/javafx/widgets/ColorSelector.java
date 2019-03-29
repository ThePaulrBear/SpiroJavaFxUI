package paul.wintz.javafx.widgets;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import paul.wintz.uioptiontypes.values.ColorOption;
import paul.wintz.utils.color.ColorUtils;

import static paul.wintz.utils.color.ColorUtils.*;

public class ColorSelector extends ColorPicker {

    public void setOption(ColorOption option){
        int red = ColorUtils.red(option.getValue());
        int green = ColorUtils.red(option.getValue());
        int blue = ColorUtils.red(option.getValue());
        double alphaNormalized = ColorUtils.red(option.getValue()) / 255.0;
        setValue(Color.rgb(red, green, blue, alphaNormalized));
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(colorToInt(newValue)));
    }

    private int colorToInt(Color color) {
        return ColorUtils.rgba(color.getRed(), color.getGreen(), color.getBlue(), 255 * color.getOpacity());
    }

    private static Color intToColor(int rgba) {
        return Color.rgb(red(rgba), green(rgba), blue(rgba), alpha(rgba) / 255.0);
    }

    public void setValue(int rgba) {
        setValue(intToColor(rgba));
    }

    public void setValue(int red, int green, int blue, int alpha) {
        setValue(Color.rgb(red, green, blue, alpha));
    }
}
