/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
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
    private Button addPawnButton, editPawnButton, importButton, exportButton, refreshButton;

    private Roster roster;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roster = new Roster();

        pawnTable.setItems(roster.getRoster());
        nameCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getName()));
        ageCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, Integer> p) -> new ReadOnlyObjectWrapper(p.getValue().getAge()));
        genderCol.setCellValueFactory((TableColumn.CellDataFeatures<Pawn, String> p) -> {
            if (p.getValue().isFemale()) {
                return new ReadOnlyObjectWrapper("F");
            } else {
                return new ReadOnlyObjectWrapper("M");
            }
        });

        roster.getRoster().addListener((ListChangeListener.Change<? extends Pawn> c) -> {
            refreshProgress();
        });

        addPawnButton.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PawnWindow.fxml"));
                PawnWindowController controller = new PawnWindowController();
                if (controller != null) {
                    controller.setRoster(roster);
                } else {
                    System.out.println("controller is null");
                }
                loader.setController(controller);
                Stage stage = new Stage();
                stage.setScene(new Scene((Pane) loader.load()));

                stage.show();

            } catch (Exception ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        exportButton.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export status");
            alert.setHeaderText(null);
            WriteSave xmlBuilder = new WriteSave();
            if (xmlBuilder.buildXML(roster)) {
                alert.setContentText("Roster exported successfully!");
                alert.showAndWait();
            } else {
                alert.setContentText("Roster export failed.");
                alert.showAndWait();
            }
        });

        refreshButton.setOnAction((ActionEvent e) -> {
            refreshProgress();
        });

        importButton.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Import status");
            alert.setHeaderText(null);
            Roster readXML = new ReadSave().readXML();
            if (readXML != null) {
                this.roster = readXML;
                alert.setContentText("Roster imported successfully!");
                alert.showAndWait();
                pawnTable.setItems(roster.getRoster());
                roster.getRoster().addListener((ListChangeListener.Change<? extends Pawn> c) -> {
                    refreshProgress();
                });
//                roster.calcTotals();
                refreshProgress();
            } else {
                alert.setContentText("Roster import failed.");
                alert.showAndWait();
            }
        });

//        refreshProgress();
    }

    public void refreshProgress() {
        roster.calcTotals();
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

        HashMap<String, ArrayList<Pawn>> hm = roster.findVulnerable();

        //again, don't bother with these calcs if we don't have at least 3 colonists
        if (roster.getRoster().size() >= 3) {
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
        if (p.getProgress() * 20 < 4) {
            //this colors the bar red if our average skill is below 5
            p.setStyle("-fx-accent: red;");
        } else if (hm.containsKey(s) && hm.get(s) != null) {
            
            //this colors the bar orange if the skill is considered vulnerable
            p.setStyle("-fx-accent: orange;");
        } else {
            p.setStyle("-fx-accent: blue;");
        }
    }

    private double getProgValue(String s) {
        double t = 0; // t for total
        double c = roster.getRoster().size(); // c for total Count

        switch (s) {
            case "shooting":
                t = Roster.getShootingSkill();
                break;
            case "social":
                t = Roster.getSocialSkill();
                break;
            case "animal":
                t = Roster.getAnimalSkill();
                break;
            case "doctor":
                t = Roster.getDoctorSkill();
                break;
            case "cooking":
                t = Roster.getCookingSkill();
                break;
            case "gardening":
                t = Roster.getGardeningSkill();
                break;
            case "mining":
                t = Roster.getMiningSkill();
                break;
            case "construction":
                t = Roster.getConstructionSkill();
                break;
            case "crafting":
                t = Roster.getCraftingSkill();
                break;
            case "research":
                t = Roster.getResearchSkill();
                break;
            default:
                throw new AssertionError();
        }

        if (t > 0 && c > 0) {
            double p = (t / c / 20);
            return p; //20 because that's the max skill, and we need to calculate a double between 0 and 1 for the progress bar
        } else {
            return 0;
        }

    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

}
