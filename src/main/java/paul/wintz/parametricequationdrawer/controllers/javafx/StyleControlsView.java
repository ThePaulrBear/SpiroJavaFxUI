package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import paul.wintz.javafx.widgets.BooleanToggle;
import paul.wintz.javafx.widgets.EquationField;
import paul.wintz.parametricequationdrawer.controllers.StyleControlsPresenter;
import paul.wintz.uioptiontypes.values.BooleanOption;
import paul.wintz.uioptiontypes.values.DoubleEquationOption;
import paul.wintz.utils.logging.Lg;

public class StyleControlsView implements StyleControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(StyleControlsView.class);

    @FXML private EquationField sizeEquation;
    @FXML private BooleanToggle colorModeToggle;
    @FXML private Label channel1Label;
    @FXML private EquationField channel1Equation;
    @FXML private Label channel2Label;
    @FXML private EquationField channel2Equation;
    @FXML private Label channel3Label;
    @FXML private EquationField channel3Equation;
    @FXML private EquationField alphaChannelEquation;

    @Override
    public void setSizeEquationOption(DoubleEquationOption sizeEquationOption) {
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
    public void setColorEquationOptions(DoubleEquationOption channel1Equation,
                                        DoubleEquationOption channel2Equation,
                                        DoubleEquationOption channel3Equation,
                                        DoubleEquationOption alphaEquationOption) {
        this.channel1Equation.setOption(channel1Equation);
        this.channel2Equation.setOption(channel2Equation);
        this.channel3Equation.setOption(channel3Equation);
        alphaChannelEquation.setOption(alphaEquationOption);
    }

}
