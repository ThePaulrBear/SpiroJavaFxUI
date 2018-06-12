package paul.wintz.javafx.widgets;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import paul.wintz.uioptiontypes.ColorOption;
import paul.wintz.utils.color.ColorUtils;

import static paul.wintz.utils.color.ColorUtils.*;

public class ColorSelector extends ColorPicker {

    public void setOption(ColorOption option){
        setValue(Color.rgb(option.red, option.green, option.blue, option.alpha / 255.0));
        valueProperty().addListener((observable, oldValue, newValue) -> option.emitViewValueChanged(colorToInt(newValue)));
    }

    private int colorToInt(Color color) {
        return ColorUtils.rgba(color.getRed(), color.getGreen(), color.getBlue(), 256 * color.getOpacity());
    }

    private static Color intToColor(int rgba) {
        return Color.rgb(red(rgba), green(rgba), blue(rgba), alpha(rgba) / 256.0);
    }

    public void setValue(int rgba) {
        setValue(intToColor(rgba));
    }

    public void setValue(int red, int green, int blue, int alpha) {
        setValue(Color.rgb(red, green, blue, alpha));
    }
}
