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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @version 1.6
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
    private TextArea message, console;

    @FXML
    private TextField plugboard, reflector;

    @FXML
    private Menu volumeText;

    @FXML
    private Slider volumeSlider;

    //==============================================================================================
    /**
     * 
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
            inputFileName = file.getName();
            openFile(String.valueOf(file));
        }
    }

    /**
     * 
     * @param file 
     */
    private void openFile(String file) {
        FileReader reader = new FileReader(file);
        List<String> lines = reader.readFile();
        message.clear();
        for (String line : lines) {
            message.appendText(line + "\n");
        }
    }

    //==============================================================================================
    /**
     * 
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
        file.getName();
        if (file != null) {
            writeFile(file);
        }
    }

    /**
     * 
     * @param file 
     */
    public void writeFile(File file) {
        FileReader writer = new FileReader(file.getName());
        String[] lines = console.getText().split("\n");
        writer.writeFile(lines, file);
    }

    //==============================================================================================
    /**
     * 
     */
    public void startEnigmaMachine() {
        int[] choices = {innerChoice.getSelectionModel().getSelectedItem() - 1, middleChoice.getSelectionModel().getSelectedItem() - 1, outerChoice.getSelectionModel().getSelectedItem() - 1};
        int[] startSettings = {innerStart.getSelectionModel().getSelectedItem() - 1, middleStart.getSelectionModel().getSelectedItem() - 1, outerStart.getSelectionModel().getSelectedItem() - 1};

        String plugboardSetting = plugboard.getText(), reflectorSetting = reflector.getText(), enteredMessage = message.getText();
        String consoleMessages = "";
        boolean isEncrypting = !enteredMessage.matches("[^0-9]+"), okayToStart = true, isDebugging = true;

        if (plugboardSetting.length() != 29) {
            changeSongs(gentleMusic, epicMusic);
            consoleMessages += "Your plugboard settings aren't valid.\n\n";
            plugboard.setStyle("-fx-background-color: red;");
            okayToStart = false;
        } else {
            changeSongs(epicMusic, gentleMusic);
            plugboard.setStyle("-fx-background-color: white;");
        }
        if (reflectorSetting.length() != 41) {
            changeSongs(gentleMusic, epicMusic);
            consoleMessages += "Your reflector settings aren't valid.\n\n";
            reflector.setStyle("-fx-background-color: red;");
            okayToStart = false;
        } else {
            changeSongs(epicMusic, gentleMusic);
            reflector.setStyle("-fx-background-color: white;");
        }

        if (okayToStart) {
            EnigmaMachine eMachine = new EnigmaMachine(choices, startSettings, plugboardSetting, reflectorSetting, enteredMessage, isEncrypting, isDebugging);
            eMachine.run();
            consoleMessages += eMachine.getDecoded();
        }
        console.setText(consoleMessages);
    }

    /**
     * 
     * @param song 
     */
    public void playMusic(MediaPlayer song) {
        song.setCycleCount(MediaPlayer.INDEFINITE);
        song.play();
    }

    /**
     * 
     * @param from
     * @param to 
     */
    public void changeSongs(MediaPlayer from, MediaPlayer to) {
        from.pause();
        playMusic(to);
    }

    /**
     * 
     * @param song 
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

    /**
     * 
     * @param url
     * @param rb 
     */
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
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert console != null : "fx:id=\"console\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

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
        testDay(31);
    }

    public void testDay(int day) {
        if (day == 31) {
            innerChoice.getSelectionModel().select(1 - 1);
            middleChoice.getSelectionModel().select(5 - 1);
            outerChoice.getSelectionModel().select(3 - 1);
            innerStart.getSelectionModel().select(14 - 1);
            middleStart.getSelectionModel().select(9 - 1);
            outerStart.getSelectionModel().select(24 - 1);
            reflector.setText("KM AX FZ GO DI CN BR PV LT EQ HS UW J. Y ");
            plugboard.setText("SZ GT DV KU FO MY EW JN IX LQ");
        } else if (day==15){
            innerChoice.getSelectionModel().select(2 - 1);
            middleChoice.getSelectionModel().select(4 - 1);
            outerChoice.getSelectionModel().select(1 - 1);
            innerStart.getSelectionModel().select(1 - 1);
            middleStart.getSelectionModel().select(3 - 1);
            outerStart.getSelectionModel().select(7 - 1);
            reflector.setText("AI BT MV HU FW EL DG KN YZ OQ CP SX J. R ");
            plugboard.setText("DS HY MR GW LX AJ BQ CO IP NT");
        } else if (day==5){
            innerChoice.getSelectionModel().select(5 - 1);
            middleChoice.getSelectionModel().select(2 - 1);
            outerChoice.getSelectionModel().select(4 - 1);
            innerStart.getSelectionModel().select(23 - 1);
            middleStart.getSelectionModel().select(2 - 1);
            outerStart.getSelectionModel().select(25 - 1);
            reflector.setText("IL AP EU HO QT WZ KV GM YF NR DX CJ S. B ");
            plugboard.setText("MV CL GK OQ BI FU HS PX NW EY");
        }
    }
}
