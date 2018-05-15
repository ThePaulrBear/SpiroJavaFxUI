package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import paul.wintz.parametricequationdrawer.controllers.paul.wintz.parametricequationdrawer.controllers.canvas.CanvasControllerPresenter;
import paul.wintz.parametricequationdrawer.controllers.paul.wintz.parametricequationdrawer.controllers.canvas.CanvasControlsView;
import paul.wintz.utils.color.ColorUtils;
import paul.wintz.utils.logging.Lg;

public class FXMLCanvasControls implements CanvasControlsView {
    private static final String TAG = Lg.makeTAG(FXMLCanvasControls.class);

    @FXML TextField centerX;
    @FXML TextField centerY;
    @FXML TextField size;
    @FXML ColorPicker background;

    private CanvasControllerPresenter presenter;

    @Override
    public void setPresenter(CanvasControllerPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void setSize(int width, int height) {
        size.setText(String.valueOf(width));
    }

    @Override
    public void setCenterX(float centerX) {
        this.centerX.setText(String.valueOf(centerX));
    }

    @Override
    public void setCenterY(float centerY) {
        this.centerY.setText(String.valueOf(centerY));
    }

    @Override
    public void setRotation(int index) {

    }

    public void handleSizeChange() {
        presenter.onSizeChanged(Integer.parseInt(size.getText()), Integer.getInteger(size.getText()));
    }

    public void onClear() {
        presenter.onClear();
    }

    public void handleCenterXChange() {
        presenter.onCenterXChanged(Float.parseFloat(centerX.getText()));
    }

    public void handleCenterYChange() {
        try {
            presenter.onCenterYChanged(Float.parseFloat(centerY.getText()));
        } catch (NumberFormatException e) {
            Lg.e(TAG, "Could not parse centerY");
        }
    }

    @Override
    public void setBackground(int red, int green, int blue, int alpha) {
        background.setValue(Color.rgb(red, green, blue, alpha));
    }

    public void handleBackgroundChange() {
        Color backgroundColor = background.getValue();
        int backgroundInt = ColorUtils.rgba(backgroundColor.getRed(),
                backgroundColor.getGreen(),
                backgroundColor.getBlue(),
                backgroundColor.getOpacity());

        Lg.d(TAG, "handleBackgroundChange to " + Integer.toHexString(backgroundInt));
        presenter.onBackgroundColorChanged(backgroundInt);
    }
}
