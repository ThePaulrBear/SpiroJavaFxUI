package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import paul.wintz.functionevaluator.FunctionEvaluator;
import paul.wintz.functionevaluator.InvalidEquationException;
import paul.wintz.parametricequationdrawer.controllers.StyleControlsPresenter;
import paul.wintz.utils.color.ColorUtils;
import paul.wintz.utils.logging.Lg;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

public class StyleControlsView implements StyleControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(StyleControlsView.class);

    @FXML private TextField sizeEquation;
    @FXML private TextField redEquation;
    @FXML private TextField greenEquation;
    @FXML private TextField blueEquation;
    @FXML private TextField alphaEquation;

    // To create a new functionEvaluator, call setEquation() then build(), which will return a new functionEvaluator
    // with the given function equation and a standard set of variables that are specified in setValuesSupplier().
    private final FunctionEvaluator.Builder functionEvaluatorBuilder = FunctionEvaluator.builder().setEquation("1.0");

    @Override
    public void setValuesSupplier(StyleControlsPresenter.ValuesSupplier valuesSupplier) {
        functionEvaluatorBuilder.addVariable("speed", valuesSupplier::speed)
                .addVariable("s", valuesSupplier::speed)
                .addVariable("accel", valuesSupplier::acceleration)
                .addVariable("a", valuesSupplier::acceleration)
                .addVariable("accelerationDirection", valuesSupplier::accelerationDirection)
                .addVariable("accel_dir", valuesSupplier::accelerationDirection)
                .addVariable("direction", valuesSupplier::direction)
                .addVariable("dir", valuesSupplier::direction)
                .addVariable("tracerAngle", valuesSupplier::tracerAngle)
                .addVariable("tracerRadius", valuesSupplier::tracerRadius)
                .addVariable("time", valuesSupplier::time)
                .addVariable("t", valuesSupplier::time)
                .addVariable("frame", valuesSupplier::frame);
    }

    @Override
    public void setSizeEquation(String sizeEquation) {
        this.sizeEquation.setText(sizeEquation);
    }

    @Override
    public void setColorEquations(String redEquation, String greenEquation, String blueEquation, String alphaEquation) {
        this.redEquation.setText(redEquation);
        this.greenEquation.setText(greenEquation);
        this.blueEquation.setText(blueEquation);
        this.alphaEquation.setText(alphaEquation);
    }

    @Override
    public void setSizeSupplierCallback(@Nonnull final Consumer<DoubleSupplier> sizeSupplierCallback) {
        bindCallback(sizeEquation, sizeSupplierCallback);
    }

    @Override
    public void setColorSupplierCallback(Consumer<IntSupplier> colorSupplierCallback) {
        final ChangeListener<String> color_equations_changed_listener = (observable, oldValue, newValue) -> {
            try {
                FunctionEvaluator redFunctionEvaluator = functionEvaluatorBuilder.setEquation(redEquation.getText())
                        .build();
                FunctionEvaluator greenFunctionEvaluator = functionEvaluatorBuilder.setEquation(greenEquation.getText())
                        .build();
                FunctionEvaluator blueFunctionEvaluator = functionEvaluatorBuilder.setEquation(blueEquation.getText())
                        .build();
                FunctionEvaluator alphaFunctionEvaluator = functionEvaluatorBuilder.setEquation(alphaEquation.getText())
                        .build();

                colorSupplierCallback.accept(() -> {
                    return ColorUtils.hsba(
                            redFunctionEvaluator.evaluate(),
                            greenFunctionEvaluator.evaluate(),
                            blueFunctionEvaluator.evaluate(),
                            alphaFunctionEvaluator.evaluate());
                });
                Lg.v(TAG, "Color equations changed");
            } catch (InvalidEquationException e) {
                Lg.w(TAG, e.getMessage());
            }
        };
        redEquation.textProperty().addListener(color_equations_changed_listener);
        greenEquation.textProperty().addListener(color_equations_changed_listener);
        blueEquation.textProperty().addListener(color_equations_changed_listener);
        alphaEquation.textProperty().addListener(color_equations_changed_listener);
    }

    private void bindCallback(TextField equationField, Consumer<DoubleSupplier> callback) {
        equationField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                FunctionEvaluator functionEvaluator = functionEvaluatorBuilder.setEquation(equationField.getText())
                        .build();
                callback.accept(functionEvaluator::evaluate);
                Lg.v(TAG, equationField.getId() + " changed to " + equationField.getText());
            } catch (InvalidEquationException e) {
                Lg.w(TAG, e.getMessage());
            }
        });
    }
}
