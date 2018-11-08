/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tara
 */
public class PawnWindowController implements Initializable {

    @FXML
    private TextField ageText;
    @FXML
    private ToggleGroup animalGroup;
    @FXML
    private Label animalLabel;
    @FXML
    private Slider animalSlider;
    @FXML
    private ToggleGroup artGroup;
    @FXML
    private Label artLabel;
    @FXML
    private Slider artSlider;
    @FXML
    private ToggleGroup constructionGroup;
    @FXML
    private Label constructionLabel;
    @FXML
    private Slider constructionSlider;
    @FXML
    private ToggleGroup cookingGroup;
    @FXML
    private Label cookingLabel;
    @FXML
    private Slider cookingSlider;
    @FXML
    private ToggleGroup craftingGroup;
    @FXML
    private Label craftingLabel;
    @FXML
    private Slider craftingSlider;
    @FXML
    private RadioButton fRadio;
    @FXML
    private ToggleGroup gardeningGroup;
    @FXML
    private Label gardeningLabel;
    @FXML
    private Slider gardeningSlider;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton mRadio;
    @FXML
    private ToggleGroup medicineGroup;
    @FXML
    private Label medicineLabel;
    @FXML
    private Slider medicineSlider;
    @FXML
    private ToggleGroup meleeGroup;
    @FXML
    private Label meleeLabel;
    @FXML
    private Slider meleeSlider;
    @FXML
    private ToggleGroup miningGroup;
    @FXML
    private Label miningLabel;
    @FXML
    private Slider miningSlider;
    @FXML
    private TextField nameText;
    @FXML
    private ToggleGroup researchGroup;
    @FXML
    private Label researchLabel;
    @FXML
    private Slider researchSlider;
    @FXML
    private Button saveButton, cancelButton;
    @FXML
    private ToggleGroup shootGroup;
    @FXML
    private Label shootingLabel;
    @FXML
    private Slider shootingSlider;
    @FXML
    private ToggleGroup socialGroup;
    @FXML
    private Label socialLabel;
    @FXML
    private Slider socialSlider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        shootingSlider.setOnDragOver((DragEvent e) -> {
//            shootingLabel.setText(Double.toString(shootingSlider.getValue()));
//        });

        shootingSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            shootingLabel.setText(String.format("%.0f", new_val));
        });
        meleeSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            meleeLabel.setText(String.format("%.0f", new_val));
        });
        socialSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            socialLabel.setText(String.format("%.0f", new_val));
        });
        animalSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            animalLabel.setText(String.format("%.0f", new_val));
        });
        medicineSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            medicineLabel.setText(String.format("%.0f", new_val));
        });
        cookingSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            cookingLabel.setText(String.format("%.0f", new_val));
        });
        gardeningSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            gardeningLabel.setText(String.format("%.0f", new_val));
        });
        miningSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            miningLabel.setText(String.format("%.0f", new_val));
        });
        constructionSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            constructionLabel.setText(String.format("%.0f", new_val));
        });
        craftingSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            craftingLabel.setText(String.format("%.0f", new_val));
        });
        artSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            artLabel.setText(String.format("%.0f", new_val));
        });
        researchSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            researchLabel.setText(String.format("%.0f", new_val));
        });

        saveButton.setOnAction((ActionEvent e) -> {
            Pawn p = new Pawn(nameText.getText(), Integer.valueOf(ageText.getText()));

            p.setFemale(fRadio.isSelected());

            p.setShootingSkill(roundSlider(shootingSlider));
            p.setMeleeSkill(roundSlider(meleeSlider));
            p.setSocialSkill(roundSlider(socialSlider));
            p.setAnimalSkill(roundSlider(animalSlider));
            p.setDoctorSkill(roundSlider(medicineSlider));
            p.setCookingSkill(roundSlider(cookingSlider));
            p.setGardeningSkill(roundSlider(gardeningSlider));
            p.setConstructionSkill(roundSlider(constructionSlider));
            p.setMiningSkill(roundSlider(miningSlider));
            p.setCraftingSkill(roundSlider(craftingSlider));
            p.setResearchSkill(roundSlider(researchSlider));

            p.setShootingPassion(setPassion(shootGroup));
            p.setMeleePassion(setPassion(meleeGroup));
            p.setSocialPassion(setPassion(socialGroup));
            p.setAnimalPassion(setPassion(animalGroup));
            p.setDoctorPassion(setPassion(medicineGroup));
            p.setCookingPassion(setPassion(cookingGroup));
            p.setGardeningPassion(setPassion(gardeningGroup));
            p.setConstructionPassion(setPassion(constructionGroup));
            p.setMiningPassion(setPassion(miningGroup));
            p.setCraftingPassion(setPassion(craftingGroup));
            p.setResearchPassion(setPassion(researchGroup));
            
            Roster.addToRoster(p);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        });

        cancelButton.setOnAction((ActionEvent e) -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

    }

    private int setPassion(ToggleGroup tg) {
        ObservableList<Toggle> tl = tg.getToggles();
        if (tl.get(1).isSelected()) {
            return 1;
        } else if (tl.get(2).isSelected()) {
            return 2;
        } else {
            return 0;
        }
    }

    private int roundSlider(Slider s) {
        return (int) (Math.round(s.getValue()));
    }

}
