/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

/**
 * @version 1.5
 * @author Justin McGettigan
 */
public class EnigmaFXMLController implements Initializable {

    private ObservableList<Integer> starts = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    private ObservableList<Integer> choices = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    @FXML
    private ListView<Integer> innerStart, middleStart, outerStart;

    @FXML
    private ListView<Integer> innerChoice, middleChoice, outerChoice;

    @FXML
    private TextArea plugboard, reflector, message, console;

    @FXML
    private Button start;

    @FXML
    private RadioButton encrypting;

    public void startEnigmaMachine() {
        /*
        DAY 16 SETTINGS
        Choices: 5, 2, 3
        Starts: 8, 16, 13
        Plugboard:[HM JO DI NR BY XZ GS PU FQ CT]
        Reflector:[AI BT MV HU FW EL DG KN YZ OQ CP SX J. R ]
         */
        int[] choices = {innerChoice.getSelectionModel().getSelectedItem() - 1, middleChoice.getSelectionModel().getSelectedItem() - 1, outerChoice.getSelectionModel().getSelectedItem() - 1};
        int[] startSettings = {innerStart.getSelectionModel().getSelectedItem() - 1, middleStart.getSelectionModel().getSelectedItem() - 1, outerStart.getSelectionModel().getSelectedItem() - 1};
        String plugboardSetting = plugboard.getText();
        String reflectorSetting = reflector.getText();
        String enteredMessage = message.getText();
        boolean isEncrypting = encrypting.isSelected();
        boolean okayToStart = true;

        if(!enteredMessage.matches("[\\w\\s\\.]+")){
            System.out.println("Your message contains unallowed characters.");
            okayToStart = false;
        }
        if(plugboardSetting.length() != 41){
            System.out.println("Your plugboard settings aren't valid.");
            okayToStart = false;
        }
        if(reflectorSetting.length() != 29){
            System.out.println("Your reflector settings aren't valid.");
            okayToStart = false;
        }

        if (okayToStart) {
            EnigmaMachine eMachine = new EnigmaMachine(choices, startSettings, plugboardSetting, reflectorSetting, enteredMessage, isEncrypting);
            eMachine.run();
        }
    }

    public void appendText(String str) {
        Platform.runLater(() -> console.appendText(str));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert plugboard != null : "fx:id=\"plugboard\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert reflector != null : "fx:id=\"reflector\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert innerStart != null : "fx:id=\"innerStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert middleStart != null : "fx:id=\"middleStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert outerStart != null : "fx:id=\"outerStart\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert innerChoice != null : "fx:id=\"innerChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert middleChoice != null : "fx:id=\"middleChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert outerChoice != null : "fx:id=\"outerChoice\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        innerStart.setItems(starts);
        middleStart.setItems(starts);
        outerStart.setItems(starts);
        innerChoice.setItems(choices);
        middleChoice.setItems(choices);
        outerChoice.setItems(choices);
        encrypting.setSelected(true);
        
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
    }

}
