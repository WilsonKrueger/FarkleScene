/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farklescene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wilson Krueger
 */

public class StartingSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    private Button buttonStart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void buttonStartAction(ActionEvent actionEvent) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        
        //This line gets the stage information
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        
        window.setScene(mainScene);
        window.show();
    }
    
}
