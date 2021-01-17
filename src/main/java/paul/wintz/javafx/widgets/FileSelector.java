package paul.wintz.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import paul.wintz.uioptiontypes.values.FileOption;

import java.io.File;

public class FileSelector extends Button {

    public void setOption(FileOption fileOption, String label) {
        FileChooser fileChooser = new FileChooser();

        // Choose which types of files are displayed.
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(fileOption.extensionsDescription, fileOption.extensions);
        fileChooser.getExtensionFilters().add(filter);

        // Set the title that appears at the top of the file selection window.
        fileChooser.setTitle(label);

        // Choose what directory is displayed when the file selection window opens.
        fileChooser.setInitialDirectory(fileOption.initialDirectory.toFile());

        // Set the button text.
        this.setText(label);

        setOnAction(event -> {
            Node source = (Node) event.getSource();
            Window window = source.getScene().getWindow();

            File selectedDirectory;
            if(fileOption.purpose == FileOption.Purpose.OPEN){
                selectedDirectory = fileChooser.showOpenDialog(window);
            } else if(fileOption.purpose == FileOption.Purpose.SAVE){
                selectedDirectory = fileChooser.showSaveDialog(window);
            } else {
                throw new IllegalArgumentException();
            }

            fileOption.emitViewValueChanged(selectedDirectory);
        });
    }

}
