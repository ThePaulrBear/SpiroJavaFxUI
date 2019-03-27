package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.EquationField;
import paul.wintz.parametricequationdrawer.controllers.StyleControlsPresenter;
import paul.wintz.uioptiontypes.values.DoubleEquationOption;
import paul.wintz.utils.logging.Lg;

public class StyleControlsView implements StyleControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(StyleControlsView.class);

    @FXML private EquationField sizeEquation;
    @FXML private EquationField redEquation;
    @FXML private EquationField greenEquation;
    @FXML private EquationField blueEquation;
    @FXML private EquationField alphaEquation;

    @Override
    public void setSizeEquationOption(DoubleEquationOption sizeEquationOption) {
        sizeEquation.setOption(sizeEquationOption);
    }

    @Override
    public void setColorEquationOptions(DoubleEquationOption redEquationOption,
                                        DoubleEquationOption greenEquationOption,
                                        DoubleEquationOption blueEquationOption,
                                        DoubleEquationOption alphaEquationOption) {
        redEquation.setOption(redEquationOption);
        greenEquation.setOption(greenEquationOption);
        blueEquation.setOption(blueEquationOption);
        alphaEquation.setOption(alphaEquationOption);
    }

}
