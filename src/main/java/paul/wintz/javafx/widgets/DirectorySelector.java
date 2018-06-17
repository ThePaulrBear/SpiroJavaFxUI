package paul.wintz.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import paul.wintz.uioptiontypes.values.FileOption;

import java.io.File;

public class DirectorySelector extends Button {

    public void setOption(FileOption path) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Save Directory");

        setOnAction(event -> {
            Node source = (Node) event.getSource();
            Window window = source.getScene().getWindow();
            File selectedDirectory = directoryChooser.showDialog(window);
            path.emitViewValueChanged(selectedDirectory);
        });
    }

}
