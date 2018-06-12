package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.*;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.uioptiontypes.BooleanOption;
import paul.wintz.uioptiontypes.FileOption;
import paul.wintz.uioptiontypes.FloatOption;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.integers.IntegerOption;

public class AnimationControlsView implements AnimationControlsPresenter.View, AnimationControlsPresenter.SavingControlsView {

    @FXML private BooleanToggle recording;
    @FXML private IntegerSpinner frameCount;
    @FXML private FloatSlider fps;

    @FXML private DirectorySelector saveDirectorySelector;
    @FXML private EventButton save;

    @Override public void setRecordingOption(BooleanOption recordingOption) {
        recording.setOption(recordingOption);
    }

    @Override public void setFrameCountOption(IntegerOption frameCountOption) {
        frameCount.setOption(frameCountOption);
    }

    @Override public void setFPSOption(FloatOption fpsOption) {
        fps.setOption(fpsOption);
    }

    @Override public void setSaveDirectoryOption(FileOption path) {
        saveDirectorySelector.setOption(path);
    }

    @Override
    public void onSaveOption(EventOption saveEventOption) {
        save.setOption(saveEventOption);
    }

}
