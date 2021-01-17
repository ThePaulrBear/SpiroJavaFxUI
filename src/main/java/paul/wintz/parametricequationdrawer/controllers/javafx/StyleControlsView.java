package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import paul.wintz.javafx.widgets.BooleanToggle;
import paul.wintz.javafx.widgets.FileSelector;
import paul.wintz.javafx.widgets.StringField;
import paul.wintz.parametricequationdrawer.controllers.StyleControlsPresenter;
import paul.wintz.uioptiontypes.values.BooleanOption;
import paul.wintz.uioptiontypes.values.JSONInPNGSaverOption;
import paul.wintz.uioptiontypes.values.StringOption;
import paul.wintz.utils.logging.Lg;

public class StyleControlsView implements StyleControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(StyleControlsView.class);

    @FXML private StringField sizeEquation;
    @FXML private BooleanToggle colorModeToggle;
    @FXML private Label channel1Label;
    @FXML private StringField channel1Equation;
    @FXML private Label channel2Label;
    @FXML private StringField channel2Equation;
    @FXML private Label channel3Label;
    @FXML private StringField channel3Equation;
    @FXML private StringField alphaChannelEquation;
    @FXML private FileSelector loadFromFile;
    @FXML private FileSelector saveToFile;

    @Override
    public void setSizeEquationOption(StringOption sizeEquationOption) {
        sizeEquation.setOption(sizeEquationOption);
    }

    @Override
    public void setColorModeOption(BooleanOption isRGBToggle) {
        colorModeToggle.setOption(isRGBToggle);
        isRGBToggle.addViewValueChangeCallback(isRGB -> {
            if (isRGB) {
                channel1Label.setText("Red");
                channel2Label.setText("Green");
                channel3Label.setText("Blue");
            } else {
                channel1Label.setText("Hue");
                channel2Label.setText("Saturation");
                channel3Label.setText("Value");
            }
        });
    }

    @Override
    public void setColorEquationOptions(StringOption channel1Equation,
                                        StringOption channel2Equation,
                                        StringOption channel3Equation,
                                        StringOption alphaEquationOption) {
        this.channel1Equation.setOption(channel1Equation);
        this.channel2Equation.setOption(channel2Equation);
        this.channel3Equation.setOption(channel3Equation);
        alphaChannelEquation.setOption(alphaEquationOption);
    }

    @Override
    public void setSettingsInPNGOptionOption(JSONInPNGSaverOption jsonInPNGSaverOption) {
        loadFromFile.setOption(jsonInPNGSaverOption.getLoadOption(), "Load");
        saveToFile.setOption(jsonInPNGSaverOption.getSaveOption(), "Save");
    }

}
