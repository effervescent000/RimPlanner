/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tara
 */
public class MainWindowController implements Initializable {

    @FXML
    private TableColumn<Pawn, Integer> ageCol;
    @FXML
    private TableColumn<Pawn, String> genderCol;
    @FXML
    private TableColumn<Pawn, String> nameCol;
    @FXML
    private TableView<Pawn> pawnTable;
    @FXML
    private ProgressBar shootingBar, socialBar, researchBar, miningBar,
            doctorBar, craftingBar, cookingBar, gardeningBar, animalBar,
            constructionBar;
    @FXML
    private Button addPawnButton, editPawnButton, importButton, exportButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pawnTable.setItems(Roster.getRoster());
        nameCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getName()));
        ageCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, Integer> p) -> new ReadOnlyObjectWrapper(p.getValue().getAge()));
        genderCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, String> p) -> {
            if (p.getValue().isFemale()) {
                return new ReadOnlyObjectWrapper("F");
            } else {
                return new ReadOnlyObjectWrapper("M");
            }
        });

        addPawnButton.setOnAction((ActionEvent e) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("PawnWindow.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        exportButton.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export status");
            alert.setHeaderText(null);
            if (IOSave.buildXML()) {
                alert.setContentText("Roster exported successfully!");
                alert.showAndWait();
            } else {
                alert.setContentText("Roster export failed.");
                alert.showAndWait();
            }
        });

        //TODO load default file containing roster info
        refreshProgress();

        Roster.getRoster().addListener((ListChangeListener.Change<? extends Pawn> c) -> {
            refreshProgress();
        });

    }

    public void refreshProgress() {
        shootingBar.setProgress(getProgValue("shooting"));
        //there is no melee bar
        socialBar.setProgress(getProgValue("social"));
        animalBar.setProgress(getProgValue("animal"));
        doctorBar.setProgress(getProgValue("doctor"));
        cookingBar.setProgress(getProgValue("cooking"));
        gardeningBar.setProgress(getProgValue("gardening"));
        miningBar.setProgress(getProgValue("mining"));
        constructionBar.setProgress(getProgValue("construction"));
        craftingBar.setProgress(getProgValue("crafting"));
        researchBar.setProgress(getProgValue("research"));

        HashMap<String, ArrayList<Pawn>> hm = Roster.findVulnerable();

        //again, don't bother with these calcs if we don't have at least 3 colonists
        if (Roster.getRoster().size() >= 3) {
            colorBar(shootingBar, "shooting", hm);
            colorBar(socialBar, "social", hm);
            colorBar(animalBar, "animal", hm);
            colorBar(doctorBar, "doctor", hm);
            colorBar(cookingBar, "cooking", hm);
            colorBar(gardeningBar, "gardening", hm);
            colorBar(miningBar, "mining", hm);
            colorBar(constructionBar, "construction", hm);
            colorBar(craftingBar, "crafting", hm);
            colorBar(researchBar, "research", hm);
        }

    }

    private void colorBar(ProgressBar p, String s, HashMap hm) {
        if (p.getProgress() * 20 < 5) {
            p.setStyle("-fx-accent: red;");
        } else if (hm.containsKey(s)) {
            p.setStyle("-fx-accent: orange;"); // TODO I don't know if this is a valid color
        } else {
            p.setStyle("-fx-accent: blue;");
        }
    }

    private double getProgValue(String s) {
        int t = Roster.getRoster().size(); // t for total
        int c = 0; // c for total Count

        switch (s) {
            case "shooting":
                c = Roster.getShootingSkill();
                break;
            case "social":
                c = Roster.getSocialSkill();
                break;
            case "animal":
                c = Roster.getAnimalSkill();
                break;
            case "doctor":
                c = Roster.getDoctorSkill();
                break;
            case "cooking":
                c = Roster.getCookingSkill();
                break;
            case "gardening":
                c = Roster.getGardeningSkill();
                break;
            case "mining":
                c = Roster.getMiningSkill();
                break;
            case "construction":
                c = Roster.getConstructionSkill();
                break;
            case "crafting":
                c = Roster.getCraftingSkill();
                break;
            case "research":
                c = Roster.getResearchSkill();
                break;
            default:
                throw new AssertionError();
        }

        if (t > 0 && c > 0) {
            return t / c / 20; //20 because that's the max skill, and we need to calculate a double between 0 and 1 for the progress bar
        } else {
            return 0;
        }

    }

}
