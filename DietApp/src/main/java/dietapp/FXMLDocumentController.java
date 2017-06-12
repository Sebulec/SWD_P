/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dietapp;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import backend.Recipe;
import backend.RecipeFactory;
import backend.RecipeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.ListView;
import javafx.scene.control.Control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/**
 *
 * @author sebastiankotarski
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField years;

    @FXML
    private RadioButton physicalS;

    @FXML
    private RadioButton physicalM;

    @FXML
    private RadioButton physicalH;

    @FXML
    private RadioButton genderW;

    @FXML
    private RadioButton genderM;

    @FXML
    private ListView<String> recipeList;

    @FXML
    private WebView webContent;

    @FXML
    private Button breakfast;

    @FXML
    private Button dinner;

    @FXML
    private Button supper;
    @FXML
    private Button website;

    List<Recipe> supperList;

   private WebEngine engine;



    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

    }

    @FXML
    private void onActionButton(ActionEvent e) throws FileNotFoundException {

        Button x = (Button) e.getSource();
        if(x== supper){
            System.out.println("Jemy kolacje");
            RecipeFactory recipeF= new RecipeFactory();
            supperList=recipeF.getRecipesWithType(RecipeType.supper);
            engine.load("http://www.onet.pl");

        }
        if(x== breakfast){
            System.out.println("Jemy sniadanie");
            RecipeFactory recipeF= new RecipeFactory();
            supperList=recipeF.getRecipesWithType(RecipeType.breakfast);
        }
        if(x== dinner){
            System.out.println("Jemy obiad");
            RecipeFactory recipeF= new RecipeFactory();
            supperList=recipeF.getRecipesWithType(RecipeType.dinner);
        }
        ObservableList<String> items=FXCollections.observableArrayList();
        for(int i=0; i<supperList.size()-1;i++) {
            items.add(supperList.get(i).getTitle());
            System.out.println(i+supperList.get(i).getTitle());
        }
        recipeList.setItems(items);

    }
    String page;

    @FXML
    private void changePage(ActionEvent e) throws FileNotFoundException {
        String s=recipeList.getSelectionModel().getSelectedItem();
       //recipeList.getSelectionModel().
        System.out.println(s);
        for(int i=0; i<supperList.size()-1;i++) {
            if (supperList.get(i).getTitle() == s) {
                page=supperList.get(i).getUrl();
            }
        }
        engine.load(page);
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            RecipeFactory.getRecipesWithType(RecipeType.supper);
            engine= webContent.getEngine();



            recipeList.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {

                        public void changed(
                                ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                            // change the label text value to the newly selected
                            // item.
                            String s=newValue;
                            //recipeList.getSelectionModel().
                            System.out.println(s);
                            for(int i=0; i<supperList.size()-1;i++) {
                                if (supperList.get(i).getTitle() == s) {
                                    page=supperList.get(i).getUrl();
                                }
                            }
                            engine.load(page);
                        }
                    });
        } catch (FileNotFoundException e) {
            System.out.println("Change working directory");
        }
    }

}
