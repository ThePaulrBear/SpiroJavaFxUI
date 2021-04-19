package paul.wintz.parametricequationdrawer.controllers.javafx;

import javafx.fxml.FXML;
import paul.wintz.javafx.widgets.*;
import paul.wintz.parametricequationdrawer.controllers.AnimationControlsPresenter;
import paul.wintz.parametricequationdrawer.controllers.SaveLocationPresenter;
import paul.wintz.uioptiontypes.events.EventOption;
import paul.wintz.uioptiontypes.values.*;

public class AnimationControlsView implements
        AnimationControlsPresenter.View,
        AnimationControlsPresenter.SavingControlsView,
        SaveLocationPresenter.View {

    @FXML private BooleanToggle recording;
    @FXML private DropdownSelector<String> format;
    @FXML private IntegerSpinner frameCount;
    @FXML private FloatSlider fps;

    @FXML private DirectorySelector saveDirectorySelector;
    @FXML private EventButton save;

    @Override public void setRecordingOption(BooleanOption recordingOption) {
        recording.setOption(recordingOption);
    }

    @Override
    public void setFormatOption(ListOption<String> formatOption) {
        format.setOption(formatOption);
    }

    @Override public void setFrameCountOption(IntegerOption frameCountOption) {
        frameCount.setOption(frameCountOption);
    }

    @Override public void setFPSOption(FloatOption fpsOption) {
        fps.setOption(fpsOption);
    }

    @Override
    public void setRecording(boolean isRecording) {
        recording.setValue(isRecording);
    }

    @Override public void setSaveDirectoryOption(DirectoryOption path) {
        saveDirectorySelector.setOption(path, "Choose Save Directory");
    }

    @Override
    public void onSaveOption(EventOption saveEventOption) {
        save.setOption(saveEventOption);
    }

}
