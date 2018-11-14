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

    private Roster roster;
    private Pawn pawn;

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

            if (pawn == null) {
                pawn = new Pawn(nameText.getText(), Integer.valueOf(ageText.getText()));
            }
            if (!roster.getRoster().contains(pawn)) {
                roster.addToRoster(pawn);
            }

            pawn.setFemale(fRadio.isSelected());

            pawn.setShootingSkill(roundSlider(shootingSlider));
            pawn.setMeleeSkill(roundSlider(meleeSlider));
            pawn.setSocialSkill(roundSlider(socialSlider));
            pawn.setAnimalSkill(roundSlider(animalSlider));
            pawn.setDoctorSkill(roundSlider(medicineSlider));
            pawn.setCookingSkill(roundSlider(cookingSlider));
            pawn.setGardeningSkill(roundSlider(gardeningSlider));
            pawn.setConstructionSkill(roundSlider(constructionSlider));
            pawn.setMiningSkill(roundSlider(miningSlider));
            pawn.setCraftingSkill(roundSlider(craftingSlider));
            pawn.setResearchSkill(roundSlider(researchSlider));

            pawn.setShootingPassion(setPassion(shootGroup));
            pawn.setMeleePassion(setPassion(meleeGroup));
            pawn.setSocialPassion(setPassion(socialGroup));
            pawn.setAnimalPassion(setPassion(animalGroup));
            pawn.setDoctorPassion(setPassion(medicineGroup));
            pawn.setCookingPassion(setPassion(cookingGroup));
            pawn.setGardeningPassion(setPassion(gardeningGroup));
            pawn.setConstructionPassion(setPassion(constructionGroup));
            pawn.setMiningPassion(setPassion(miningGroup));
            pawn.setArtPassion(setPassion(artGroup));
            pawn.setCraftingPassion(setPassion(craftingGroup));
            pawn.setResearchPassion(setPassion(researchGroup));

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

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public void setPawn(Pawn p) {
        if (nameText == null) {
            System.out.println("nameText is null");
        }
        nameText.setText(p.getName());
        ageText.setText(Integer.toString(p.getAge()));
        if (p.isFemale()) {
            fRadio.setSelected(true);
        } else {
            mRadio.setSelected(true);
        }
        shootingSlider.setValue(p.getShootingSkill());
        meleeSlider.setValue(p.getMeleeSkill());
        constructionSlider.setValue(p.getConstructionSkill());
        miningSlider.setValue(p.getMiningSkill());
        cookingSlider.setValue(p.getCookingSkill());
        gardeningSlider.setValue(p.getGardeningSkill());
        animalSlider.setValue(p.getAnimalSkill());
        craftingSlider.setValue(p.getCraftingSkill());
        artSlider.setValue(p.getArtSkill());
        medicineSlider.setValue(p.getDoctorSkill());
        socialSlider.setValue(p.getSocialSkill());
        researchSlider.setValue(p.getResearchSkill());
        if (!findToggle(p.getShootingPassion(), shootGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s shooting passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getMeleePassion(), meleeGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s melee passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getSocialPassion(), socialGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s social passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getAnimalPassion(), animalGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s animal passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getDoctorPassion(), medicineGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s medicine passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getCookingPassion(), cookingGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s cooking passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getMiningPassion(), miningGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s mining passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getConstructionPassion(), constructionGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s construction passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getGardeningPassion(), gardeningGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s gardening passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getCraftingPassion(), craftingGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s crafting passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getArtPassion(), artGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s art passion to a Radio Button.");
            return;
        }
        if (!findToggle(p.getResearchPassion(), researchGroup.getToggles())) {
            System.out.println("Failed to match Pawn " + p.getName() + "'s research passion to a Radio Button.");
            return;
        }
        this.pawn = p;

    }

    /**
     * Selects the correct Radio Button to match a Pawn's passion level.
     *
     * @param passion The passion level of a given pawn.
     * @param tg      The ObservableList of a toggle group.
     * @return true if method successfully matched the passion level with a
     *         Toggle and selected it; false otherwise.
     */
    private boolean findToggle(int passion, ObservableList<Toggle> tg) {
        Toggle toggle = null;
        switch (passion) {
            case 0:
                toggle = tg.get(0);
                break;
            case 1:
                toggle = tg.get(1);
                break;
            case 2:
                toggle = tg.get(2);
                break;
            default:
                System.out.println("Pawn's passion set to invalid value (found in findToggle())");
        }
        if (toggle != null) {
            toggle.setSelected(true);
            return true;
        } else {
            return false;
        }

    }

}
