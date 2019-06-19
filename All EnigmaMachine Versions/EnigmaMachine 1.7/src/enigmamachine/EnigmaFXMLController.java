/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class EnigmaFXMLController implements Initializable {

    private final ObservableList<Integer> starts = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28),
            choices = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    private MediaPlayer gentleMusic, epicMusic;
    
    private String inputFileName;

    @FXML
    private ListView<Integer> innerChoice, middleChoice, outerChoice;

    @FXML
    private ListView<Integer> innerStart, middleStart, outerStart;

    @FXML
    private TextArea input, output;

    @FXML
    private TextField plugboard, reflector;

    @FXML
    private Menu volumeText;

    @FXML
    private Slider volumeSlider;

    /**
     * This method opens Windows File Explorer and
     * allows you to select a text file to import.
     */
    public void fileChooserOpen() {
        FileChooser fc = new FileChooser();
        fc.setTitle("View Files");
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        fc.setInitialDirectory(
                new File(System.getProperty("user.dir")) //how to have it set to project directory??
        );
        File file = fc.showOpenDialog(new Stage());
        if (file != null) {
            inputFileName = file.getName().substring(0, file.getName().length() - 4);
            openFile(file);
        }
    }

    /**
     * This method reads the contents of the imported
     * text file and appends it to the "input" textArea
     * of the GUI.
     * @param file This parameter is the imported text file.
     */
    private void openFile(File file) {
        FileReader reader = new FileReader(file);
        List<String> lines = reader.readFile();
        input.clear();
        for (String line : lines) {
            input.appendText(line + "\n");
        }
    }

    /**
     * This method opens Windows File Explorer and
     * allows you to select a destination to export
     * your output file.
     */
    public void fileChooserSave() {
        FileChooser fc = new FileChooser();
        fc.setTitle("View Files");
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        fc.setInitialDirectory(
                new File(System.getProperty("user.dir")) //how to have it set to project directory??
        );
        fc.setInitialFileName(inputFileName + "_output.txt"); //make name same as input file name + _output EX. inputfilename_output
        File file = fc.showSaveDialog(new Stage());
        if (file != null) {
            saveFile(file);
        }
    }

    /**
     * This method creates a desired output file and then takes
     * the contents of the "output" textArea and writes it to
     * this output file.
     * @param file This parameter is the file that you wish to create and write to.
     */
    public void saveFile(File file) {
        FileReader writer = new FileReader(file);
        String[] lines = output.getText().split("\n");
        writer.writeFile(lines);
    }

    /**
     * This method verifies the settings that have
     * been input into the GUI and then executes
     * the EnigmaMachine class if there are no problems.
     */
    public void startEnigmaMachine() {
        int[] choices = {innerChoice.getSelectionModel().getSelectedItem() - 1, middleChoice.getSelectionModel().getSelectedItem() - 1, outerChoice.getSelectionModel().getSelectedItem() - 1};
        int[] startSettings = {innerStart.getSelectionModel().getSelectedItem() - 1, middleStart.getSelectionModel().getSelectedItem() - 1, outerStart.getSelectionModel().getSelectedItem() - 1};

        String plugboardSetting = plugboard.getText(), reflectorSetting = reflector.getText(), enteredMessage = input.getText();
        String outputMessages = "";
        boolean isEncrypting = !enteredMessage.matches("[^0-9]+"), okayToStart = true;

        if (plugboardSetting.length() != 29) {
            changeSongs(gentleMusic, epicMusic);
            outputMessages += "Your plugboard settings aren't valid.\n\n";
            plugboard.setStyle("-fx-background-color: red;");
            okayToStart = false;
        } else {
            changeSongs(epicMusic, gentleMusic);
            plugboard.setStyle("-fx-background-color: white;");
        }
        if (reflectorSetting.length() != 41) {
            changeSongs(gentleMusic, epicMusic);
            outputMessages += "Your reflector settings aren't valid.\n\n";
            reflector.setStyle("-fx-background-color: red;");
            okayToStart = false;
        } else {
            changeSongs(epicMusic, gentleMusic);
            reflector.setStyle("-fx-background-color: white;");
        }

        if (okayToStart) {
            EnigmaMachine eMachine = new EnigmaMachine(choices, startSettings, plugboardSetting, reflectorSetting, enteredMessage, isEncrypting);
            eMachine.run();
            outputMessages += eMachine.getOutput();
        }
        output.setText(outputMessages);
    }

    /**
     * This method sets a song to have
     * an infinite loop and then plays it.
     * @param song This parameter is the song to be played.
     */
    public void playMusic(MediaPlayer song) {
        song.setCycleCount(MediaPlayer.INDEFINITE);
        song.play();
    }

    /**
     * This method pauses a song and then
     * plays a different one.
     * @param from This parameter is the song that will be paused.
     * @param to This parameter is the song that will be played.
     */
    public void changeSongs(MediaPlayer from, MediaPlayer to) {
        from.pause();
        playMusic(to);
    }

    /**
     * This method manages the volume of the music being played.
     * @param song This parameter is the song whose volume is being manipulated.
     */
    public void musicVolume(MediaPlayer song) {
        song.setVolume(volumeSlider.getValue() / 100);

        //---------------------------------------------------------------------------
        //FULL DISCLOSURE: I don't fully understand how this code works.  I just really wanted a volume slider.
        //I used this as a reference: https://aboutyusata.blogspot.com/2015/10/audio-player-in-java-fx.html
        volumeSlider.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            song.setVolume(volumeSlider.getValue() / 100);
            volumeText.setText(Integer.toString(newvalue.intValue()));
        });
        //---------------------------------------------------------------------------
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert plugboard != null : "fx:id=\"plugboard\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert reflector != null : "fx:id=\"reflector\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert innerChoice != null : "fx:id=\"innerChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert middleChoice != null : "fx:id=\"middleChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert outerChoice != null : "fx:id=\"outerChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert innerStart != null : "fx:id=\"innerStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert middleStart != null : "fx:id=\"middleStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert outerStart != null : "fx:id=\"outerStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert volumeSlider != null : "fx:id=\"volumeSlider\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert volumeText != null : "fx:id=\"volumeText\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        epicMusic = new MediaPlayer(new Media(new File("epic-orchestra-loop.wav").toURI().toString()));
        gentleMusic = new MediaPlayer(new Media(new File("gentle-piano-music-loop.wav").toURI().toString()));
        playMusic(gentleMusic);
        volumeSlider.setValue(40);
        volumeText.setText("40");
        musicVolume(gentleMusic);

        innerChoice.setItems(choices);
        middleChoice.setItems(choices);
        outerChoice.setItems(choices);
        innerStart.setItems(starts);
        middleStart.setItems(starts);
        outerStart.setItems(starts);
        
        inputFileName = "typedMessage";
    }
}
