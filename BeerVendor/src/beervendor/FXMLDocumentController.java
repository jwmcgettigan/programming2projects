package beervendor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FXMLDocumentController {

    ObservableList<String> beerList = FXCollections.observableArrayList("Pabst", "Sam Adams", "Lite");
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Label label;
    
    @FXML
    private ImageView myBeer;
    
    @FXML
    private ChoiceBox<String> beerChoice;

    @FXML
    void handleButtonAction(ActionEvent event) {

        System.out.println("The user chose "+ beerChoice.getValue());
        
    }

    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert myBeer != null;
        assert beerChoice != null;
        beerChoice.setItems(beerList);
        beerChoice.setValue("Lite");
    }
}
