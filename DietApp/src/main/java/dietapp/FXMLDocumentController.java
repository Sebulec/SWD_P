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

import backend.*;
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

    List<Recipe> breakfastList;
    List<Recipe> dinnerList;
    List<Recipe> supperList;
    ObservableList<String> supp = FXCollections.observableArrayList();
    ObservableList<String> breakf = FXCollections.observableArrayList();
    ObservableList<String> dinn = FXCollections.observableArrayList();
    Integer age;
    Double w;
    Double h;
    Gender g;
    ActivityType aT;
    private WebEngine engine;



    @FXML
    private void onActionButton(ActionEvent e) throws FileNotFoundException {

        Button x = (Button) e.getSource();

        age=Integer.parseInt(years.getText());
        w=Double.parseDouble(weight.getText());
        h=Double.parseDouble(height.getText());

        if(genderM.isSelected()){
            g=Gender.male;
        }
        else{
            g=Gender.female;
        }

        if(physicalH.isSelected()){
            aT=ActivityType.high;
        }
        else if(physicalM.isSelected()){
            aT=ActivityType.normal;
        }
        else {
            aT=ActivityType.low;
        }

        if (x == supper) {
            recipeList.setItems(supp);

        }
        if (x == breakfast) {
            recipeList.setItems(breakf);
        }
        if (x == dinner) {
            recipeList.setItems(dinn);
        }

        User Marysia= new User(age,w,h,g,aT);
        System.out.println(Marysia.getAge()+ " "+Marysia.getHeight()+" "+Marysia.getWeight()+" "+Marysia.getActivityType());
    }

    String page;

    @FXML
    private void changePage(ActionEvent e) throws FileNotFoundException {
        String s = recipeList.getSelectionModel().getSelectedItem();
        //recipeList.getSelectionModel().
        System.out.println(s);
        for (int i = 0; i < supperList.size() - 1; i++) {
            if (supperList.get(i).getTitle() == s) {
                page = supperList.get(i).getUrl();
            }
        }
        engine.load(page);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            DecisionMaker decisionMaker = new DecisionMaker();
            User user = new User(27,62., 157., Gender.female, ActivityType.low);

            decisionMaker.makeDecision(user, RecipeType.supper, new RecipeFactory().getRecipesWithType(RecipeType.dinner), new CompletionHandler() {
                @Override
                public void completed(List<Recipe> recipes) {

                }

                @Override
                public void stopped() {

                }
            });

            genderW.setSelected(true);
            physicalS.setSelected(true);
            RecipeFactory.getRecipesWithType(RecipeType.supper);
            engine = webContent.getEngine();

            RecipeFactory recipeF = new RecipeFactory();
            supperList = recipeF.getRecipesWithType(RecipeType.supper);

            breakfastList = recipeF.getRecipesWithType(RecipeType.breakfast);

            dinnerList = recipeF.getRecipesWithType(RecipeType.dinner);


            for (int i = 0; i < supperList.size() - 1; i++) {
                supp.add(supperList.get(i).getTitle());
            }
            for (int i = 0; i < breakfastList.size() - 1; i++) {
                breakf.add(breakfastList.get(i).getTitle());
            }
            for (int i = 0; i < dinnerList.size() - 1; i++) {
                dinn.add(dinnerList.get(i).getTitle());
            }

            supper.disableProperty().bind(weight.textProperty().isEmpty()
                    .or(height.textProperty().isEmpty()).or(years.textProperty().isEmpty()));
            breakfast.disableProperty().bind(weight.textProperty().isEmpty()
                    .or(height.textProperty().isEmpty()).or(years.textProperty().isEmpty()));
            dinner.disableProperty().bind(weight.textProperty().isEmpty()
                    .or(height.textProperty().isEmpty()).or(years.textProperty().isEmpty()));

            weight.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        weight.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            height.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        height.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            years.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        years.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });

            recipeList.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {

                        public void changed(
                                ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                            // change the label text value to the newly selected
                            // item.
                            String s = newValue;
                            //recipeList.getSelectionModel().
                            System.out.println(s);
                            for (int i = 0; i < supperList.size() - 1; i++) {
                                if (supperList.get(i).getTitle() == s) {
                                    page = supperList.get(i).getUrl();
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
