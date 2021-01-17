package paul.wintz.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import paul.wintz.uioptiontypes.values.DirectoryOption;
import paul.wintz.utils.logging.Lg;

import java.io.File;

public class DirectorySelector extends Button {
    private static final String TAG = Lg.makeTAG(DirectorySelector.class);

    public void setOption(DirectoryOption path, String label) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(label);

        setOnAction(event -> {
            Node source = (Node) event.getSource();
            Window window = source.getScene().getWindow();
            File selectedDirectory = directoryChooser.showDialog(window);
            if(selectedDirectory == null){
                Lg.w(TAG, "Selected directory was null!");
                return;
            } else {
                Lg.i(TAG, "Selected directory:" + selectedDirectory);
            }
            path.emitViewValueChanged(selectedDirectory);
        });
    }

}
