package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import paul.wintz.parametricequationdrawer.SpiroUserInterface;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.DrawerControllerPresenter;
import paul.wintz.parametricequationdrawer.controllers.javafx.FXMLCanvasControlsView;
import paul.wintz.parametricequationdrawer.controllers.paul.wintz.parametricequationdrawer.controllers.canvas.CanvasControllerPresenter;
import paul.wintz.uioptiontypes.events.EventOption;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import static paul.wintz.utils.logging.Lg.makeTAG;

public class SpiroOptionsJavaFX extends Stage implements SpiroUserInterface {
    private static final String TAG = makeTAG(SpiroOptionsJavaFX.class);
    private CanvasControllerPresenter.View canvasControls;

    public SpiroOptionsJavaFX() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Enumeration<URL> resources = getClass().getClassLoader().getResources("");

        URL url = new URL("file:\\Users\\PaulWintz\\GIT\\SpiroProject\\SpiroJavaFxUI\\src\\main\\java\\paul\\wintz\\parametricequationdrawer\\controllers\\javafx\\canvasControlsView.fxml");
//        for(URL url = resources.nextElement(); resources.hasMoreElements(); url = resources.nextElement()) {
//            Lg.d(TAG, "Resources:" + url);
//        }

        loader.setLocation(url);
        Parent root = loader.load();

        StackPane pane = new StackPane();
        pane.getChildren().setAll(root);
        setScene(new Scene(pane));

        canvasControls = loader.<FXMLCanvasControlsView>getController();

        show();
    }

    @Override
    public DrawerControllerPresenter getDrawerControllerPresenter() {
        return null;
    }

    @Override
    public CanvasControllerPresenter.View getCanvasController() {
        return canvasControls;
    }

    @Override
    public AnimationControlsPresenter.AnimationControllerView getAnimationController() {
        return new AnimationControlsPresenter.AnimationControllerView() {
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
            public void onFrameCountChanged(AnimationControlsPresenter.OnChangeListener<Integer> onChangeListener) {

            }

            @Override
            public void setFPS(float fps) {

            }

            @Override
            public void onFPSChanged(AnimationControlsPresenter.OnChangeListener<Float> onChangeListener) {

            }
        };
    }

    @Override
    public AnimationControlsPresenter.SavingControlsView getSavingView() {
        return new AnimationControlsPresenter.SavingControlsView() {
            @Override
            public void setSaveLocation(File saveLocation) {

            }

            @Override
            public void onSaveLocationChanged(AnimationControlsPresenter.OnChangeListener<File> onSaveLocationListener) {

            }

            @Override
            public void onSaveCued(EventOption.Event eventListener) {

            }
        };
    }
}
