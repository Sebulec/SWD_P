/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dietapp;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.RecipeFactory;
import backend.RecipeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author sebastiankotarski
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            RecipeFactory.getRecipesWithType(RecipeType.supper);
        } catch (FileNotFoundException e) {
            System.out.println("Change working directory");
        }
    }

}
