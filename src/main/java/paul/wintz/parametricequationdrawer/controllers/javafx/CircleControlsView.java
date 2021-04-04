package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import paul.wintz.javafx.widgets.IntegerSpinner;
import paul.wintz.javafx.widgets.StringField;
import paul.wintz.spirotechnics.cirlcesspirotechnic.parameters.CircleControlsPresenter;
import paul.wintz.uioptiontypes.values.EquationOption;
import paul.wintz.uioptiontypes.values.IntegerOption;
import paul.wintz.utils.logging.Lg;

public class CircleControlsView implements CircleControlsPresenter.View {
    private static final String TAG = Lg.makeTAG(CircleControlsView.class);

    @FXML IntegerSpinner radius;
    @FXML IntegerSpinner velocity;
    @FXML Label velocityLabel;
    @FXML StringField rotationOffset;
    @FXML Label rotationOffsetLabel;

    @Override
    public void setRadiusOption(IntegerOption radiusOption) {
        radius.setOption(radiusOption);
    }

    @Override
    public void setVelocityOption(IntegerOption velocityOption) {
        velocity.setOption(velocityOption);
    }

    @Override
    public void setRotationOffsetOption(EquationOption rotationOffsetOption) {
        rotationOffset.setOption(rotationOffsetOption);
    }

    void setIndex(int index) {
        if(index == 0){
            velocity.setVisible(false);
            velocity.setManaged(false);
            velocityLabel.setVisible(false);
            velocityLabel.setManaged(false);
            rotationOffset.setVisible(false);
            rotationOffset.setManaged(false);
            rotationOffsetLabel.setVisible(false);
            rotationOffsetLabel.setManaged(false);
        }
    }
}
