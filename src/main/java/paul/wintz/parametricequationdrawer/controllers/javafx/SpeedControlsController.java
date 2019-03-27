package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.sourcefactories.speedcontroller.SpeedControllerPresenter;
import paul.wintz.uioptiontypes.values.IntegerOption;

public class SpeedControlsController implements SpeedControllerPresenter.View {

    @FXML private Label targetSpeedLabel;
    @FXML private IntegerSpinner targetSpeedSpinner;
    @FXML private Label instantCountLabel;
    @FXML private IntegerSpinner instantCountSpinner;

    @Override
    public void setTargetSpeedOption(IntegerOption targetSpeedOption) {
        targetSpeedSpinner.setOption(targetSpeedOption);
    }

    @Override
    public void setInstantCountOption(IntegerOption instantCountOption) {
        instantCountSpinner.setOption(instantCountOption);
    }

    @Override
    public void setActiveMode(SpeedControllerPresenter.SpeedMode speedMode) {
        switch (speedMode) {
            case TARGET_SPEED:
                targetSpeedLabel.setStyle("-fx-font-weight: bold;");
                instantCountLabel.setStyle("-fx-font-weight: thin;");
                break;
            case TARGET_COUNT:
                targetSpeedLabel.setStyle("-fx-font-weight: thin;");
                instantCountLabel.setStyle("-fx-font-weight: bold;");
                break;
        }
    }
}
