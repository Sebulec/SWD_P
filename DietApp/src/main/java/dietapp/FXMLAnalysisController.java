package dietapp;

import backend.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.security.krb5.internal.crypto.Des;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by frasz on 12.06.2017.
 */
public class FXMLAnalysisController implements Initializable {



    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField years;

    @FXML
    private RadioButton genderW;

    @FXML
    private RadioButton genderM;

    @FXML
    private ListView<String> recipeList;
    @FXML
    private ListView<String> entryList;

    @FXML
    private Button analysis;
    @FXML
    private Button analysisProblem;




    List<Recipe> supperList;
    ObservableList<String> result = FXCollections.observableArrayList();
    ObservableList<String> supp = FXCollections.observableArrayList();

    Integer age;
    Double w;
    Double h;
    Gender g;
    ActivityType aT;


    @FXML
    private void onAnalysisButton(ActionEvent e) throws IOException {
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDecision.fxml"));
        Scene scene = new Scene(root);
        //  root.setController(new FXMLDocumentControler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    @FXML
    private void onActionButton(ActionEvent e) throws FileNotFoundException {

        Button x = (Button) e.getSource();
       // recipeList.setItems(supp);
        age=Integer.parseInt(years.getText());
        w=Double.parseDouble(weight.getText());
        h=Double.parseDouble(height.getText());

        if(genderM.isSelected()){
            g=Gender.male;
        }
        else{
            g=Gender.female;
        }


        aT=ActivityType.normal;
        User Marysia= new User(age,w,h,g,aT);
        System.out.println(Marysia.getAge()+ " "+Marysia.getHeight()+" "+Marysia.getWeight()+" "+Marysia.getActivityType());

        String recipeName = recipeList.getSelectionModel().getSelectedItem();
        Recipe recipe = this.supperList.stream().filter((recipe1) -> recipe1.getTitle().equals(recipeName)).collect(Collectors.toList()).get(0);

        DecisionMaker decisionMaker = new DecisionMaker();
        decisionMaker.makeAnalysis(Marysia, recipe, new CompletionHandler() {
            @Override
            public void completed(List<? extends DietEntity> entities) {
                List<AnalysisResponse> selectedAnalysisResponses = (List<AnalysisResponse>) entities;
                result.removeAll(result);
                for (int i = 0; i < selectedAnalysisResponses.size(); i++) {
                    String type;
                    String activity;
                    if(selectedAnalysisResponses.get(i).getRecipeType().toString()=="dinner"){
                        type="obiad";
                    }
                    else if(selectedAnalysisResponses.get(i).getRecipeType().toString()=="super"){
                        type="kolację";
                    }
                    else{
                        type="śniadanie";
                    }
                    if(selectedAnalysisResponses.get(i).getActivityType().toString()=="low"){
                        activity="niską";
                    }
                    else if(selectedAnalysisResponses.get(i).getRecipeType().toString()=="high"){
                        activity="wysoką";
                    }
                    else{
                        activity="średnią";
                    }
                    result.add("Posiadając "+activity +" aktywność fizyczną możesz zjeść tę potrawę na "+type);
                    //result.add(selectedAnalysisResponses.get(i).getRecipeType().toString() + " "+selectedAnalysisResponses.get(i).getActivityType().toString());
                }
                if(selectedAnalysisResponses.size()==0){
                    result.add("Brak warunków, aby zjeść daną potrawę");
                }
                entryList.setItems(result);
            }

            @Override
            public void stopped() {

            }
        });

    }

    String page;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            genderW.setSelected(true);
            //physicalS.setSelected(true);
            //RecipeFactory.getRecipesWithType(RecipeType.supper);

            RecipeFactory recipeF = new RecipeFactory();

            supperList = Stream.concat(recipeF.getRecipesWithType(RecipeType.breakfast).stream(), recipeF.getRecipesWithType(RecipeType.dinner).stream()).collect(Collectors.toList());
            supperList.addAll(recipeF.getRecipesWithType(RecipeType.supper));

            for (int i = 0; i < supperList.size() - 1; i++) {
                supp.add(supperList.get(i).getTitle());
            }
           recipeList.setItems(supp);

            recipeList.getSelectionModel().select(0);


           analysis.disableProperty().bind(weight.textProperty().isEmpty()
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


        } catch (FileNotFoundException e) {
            System.out.println("Change working directory");
        }
    }

}
