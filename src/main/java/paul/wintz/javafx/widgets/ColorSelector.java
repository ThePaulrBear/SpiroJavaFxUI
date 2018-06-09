package paul.wintz.javafx.widgets;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import paul.wintz.uioptiontypes.ColorOption;
import paul.wintz.utils.color.ColorUtils;

public class ColorSelector extends ColorPicker {

    public void setOption(ColorOption option){
        setValue(Color.rgb(option.red, option.green, option.blue, option.alpha / 255.0));
        valueProperty().addListener((observable, oldValue, newValue) -> {
            option.viewValueChangeCallback.callback(colorToInt(newValue));
        });
    }

    private int colorToInt(Color color) {
        return ColorUtils.rgba(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }

}
