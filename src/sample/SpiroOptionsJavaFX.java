package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import paul.wintz.parametricequationdrawer.SpiroUserInterface;
import paul.wintz.parametricequationdrawer.controllers.AnimationController;
import paul.wintz.parametricequationdrawer.controllers.DrawerController;
import paul.wintz.parametricequationdrawer.controllers.paul.wintz.parametricequationdrawer.controllers.canvas.CanvasControlsView;
import paul.wintz.uioptiontypes.events.EventOption;

import java.io.File;
import java.io.IOException;

import static paul.wintz.utils.logging.Lg.makeTAG;

public class SpiroOptionsJavaFX extends Stage implements SpiroUserInterface {
    private static final String TAG = makeTAG(SpiroOptionsJavaFX.class);
    private CanvasControlsView canvasControls;

    public SpiroOptionsJavaFX() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SpiroOptionsJavaFX.class.getResource("canvasControls.fxml"));
        Parent root = loader.load();

        StackPane pane = new StackPane();
        pane.getChildren().setAll(root);
        setScene(new Scene(pane));

        canvasControls = loader.<FXMLCanvasControls>getController();

        show();
    }

    @Override
    public DrawerController getDrawerController() {
        return null;
    }

    @Override
    public CanvasControlsView getCanvasController() {
        return canvasControls;
    }

    @Override
    public AnimationController.AnimationControllerView getAnimationController() {
        return new AnimationController.AnimationControllerView() {
            @Override
            public void setIsRecording(boolean isRecording) {

            }

            @Override
            public void onRecordingStarted(EventOption.Event onRecordingStartedListener) {

            }

            @Override
            public void onRecordingStopped(EventOption.Event onRecordingStoppedListener) {

            }

            @Override
            public void setFrameCount(int frameCount) {

            }

            @Override
            public void onFrameCountChanged(AnimationController.OnChangeListener<Integer> onChangeListener) {

            }

            @Override
            public void setFPS(float fps) {

            }

            @Override
            public void onFPSChanged(AnimationController.OnChangeListener<Float> onChangeListener) {

            }
        };
    }

    @Override
    public AnimationController.SavingControlsView getSavingView() {
        return new AnimationController.SavingControlsView() {
            @Override
            public void setSaveLocation(File saveLocation) {

            }

            @Override
            public void onSaveLocationChanged(AnimationController.OnChangeListener<File> onSaveLocationListener) {

            }

            @Override
            public void onSaveCued(EventOption.Event eventListener) {

            }
        };
    }
}
