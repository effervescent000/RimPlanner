/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tara
 */
@XmlRootElement
public class Roster {

    private static int shootingSkill;
    private static int meleeSkill;
    private static int socialSkill;
    private static int animalSkill;
    private static int doctorSkill;
    private static int cookingSkill;
    private static int gardeningSkill;
    private static int miningSkill;
    private static int constructionSkill;
    private static int craftingSkill;
    private static int artSkill;
    private static int researchSkill;

    private ObservableList<Pawn> roster;

    public Roster() {
        roster = FXCollections.observableArrayList();
        calcTotals();
    }

    public void addToRoster(Pawn p) {
        if (!roster.contains(p)) {
            changeStatTotals(p, true);
            roster.add(p);
        } else {
            System.out.println("Attempted to add duplicate Pawn " + p.getName() + " to roster");
        }
    }

    public void removeFromRoster(Pawn p) {
        if (roster.contains(p)) {
            roster.remove(p);
            changeStatTotals(p, false);
        } else {
            System.out.println("Attempted to remove Pawn " + p.getName() + " from the roster, but they aren't on it.");
        }
    }

    /**
     * Adds or subtracts a pawn's stats from the total counts for the roster.
     *
     * @param p The pawn whose stats will be added or removed.
     * @param b True if the pawn is being added to the roster, false if removed.
     */
    private void changeStatTotals(Pawn p, boolean b) {
        int i;
        if (b) {
            i = 1;
        } else {
            i = -1;
        }

        shootingSkill += p.getShootingSkill() * i;
        meleeSkill += p.getMeleeSkill() * i;
        socialSkill += p.getSocialSkill() * i;
        animalSkill += p.getAnimalSkill() * i;
        doctorSkill += p.getDoctorSkill() * i;
        cookingSkill += p.getCookingSkill() * i;
        gardeningSkill += p.getGardeningSkill() * i;
        miningSkill += p.getMiningSkill() * i;
        constructionSkill += p.getConstructionSkill() * i;
        craftingSkill += p.getCraftingSkill() * i;
        artSkill += p.getArtSkill() * i;
        researchSkill += p.getResearchSkill() * i;

    }

    public void calcTotals() {
        resetTotals();

        for (Pawn p : roster) {
            // a skill of -1 means the pawn is incapable of that job, so we are excluding
            //them from these counts
            if (p.getShootingSkill() > -1) {
                shootingSkill += p.getShootingSkill();
            }
            if (p.getMeleeSkill() > -1) {
                meleeSkill += p.getMeleeSkill();
            }
            if (p.getSocialSkill() > -1) {
                socialSkill += p.getShootingSkill();
            }
            if (p.getAnimalSkill() > -1) {
                animalSkill += p.getAnimalSkill();
            }
            if (p.getDoctorSkill() > -1) {
                doctorSkill += p.getDoctorSkill();
            }
            if (p.getCookingSkill() > -1) {
                cookingSkill += p.getCookingSkill();
            }
            if (p.getGardeningSkill() > -1) {
                gardeningSkill += p.getGardeningSkill();
            }
            if (p.getMiningSkill() > -1) {
                miningSkill += p.getMiningSkill();
            }
            if (p.getConstructionSkill() > -1) {
                constructionSkill += p.getConstructionSkill();
            }
            if (p.getCraftingSkill() > -1) {
                craftingSkill += p.getCraftingSkill();
            }
            if (p.getArtSkill() > -1) {
                artSkill += p.getArtSkill();
            }
            if (p.getResearchSkill() > -1) {
                researchSkill += p.getResearchSkill();
            }
        }
    }

    public HashMap<String, ArrayList<Pawn>> findVulnerable() {
        HashMap<String, ArrayList<Pawn>> vul = new HashMap<>();

        int count = roster.size();
        String skill;

        //Don't bother with this if we don't have at least three colonists
        if (count >= 3) {
            // likewise, don't bother if we don't actually have anyone who has substantial skill
            if (shootingSkill > 5 * count) {
                skill = "shooting";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (meleeSkill > 5 * count) {
                skill = "melee";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (socialSkill > 5 * count) {
                skill = "social";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (animalSkill > 5 * count) {
                skill = "animal";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (doctorSkill > 5 * count) {
                skill = "doctor";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (cookingSkill > 5 * count) {
                skill = "cooking";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (gardeningSkill > 5 * count) {
                skill = "gardening";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (miningSkill > 5 * count) {
                skill = "mining";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (constructionSkill > 5 * count) {
                skill = "construction";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (craftingSkill > 5 * count) {
                skill = "crafting";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (artSkill > 5 * count) {
                skill = "art";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
            if (researchSkill > 5 * count) {
                skill = "research";
                vul = validateMap(vul, skill);
                vul = checkSkill(vul, skill);
            }
        }

        return vul;
    }

    public static int getAnimalSkill() {
        return animalSkill;
    }

    public static int getArtSkill() {
        return artSkill;
    }

    public static int getConstructionSkill() {
        return constructionSkill;
    }

    public static int getCookingSkill() {
        return cookingSkill;
    }

    public static int getCraftingSkill() {
        return craftingSkill;
    }

    public static int getDoctorSkill() {
        return doctorSkill;
    }

    public static int getGardeningSkill() {
        return gardeningSkill;
    }

    public static int getMeleeSkill() {
        return meleeSkill;
    }

    public static int getMiningSkill() {
        return miningSkill;
    }

    public static int getResearchSkill() {
        return researchSkill;
    }

    @XmlElements({
        @XmlElement(name = "pawn")})
    public ObservableList<Pawn> getRoster() {
        return roster;
    }

//    @XmlElements({
//        @XmlElement(name = "pawn", type = Pawn.class)})
//    public List<Pawn> getRosterList() {
//        List<Pawn> li = new ArrayList(roster);
//        return li;
//    }
    public static int getShootingSkill() {
        return shootingSkill;
    }

    public static int getSocialSkill() {
        return socialSkill;
    }

    private static HashMap<String, ArrayList<Pawn>> validateMap(HashMap<String, ArrayList<Pawn>> hm, String s) {
        if (!hm.containsKey(s)) {
            hm.put(s, new ArrayList<>());
        }
        return hm;
    }

    private HashMap<String, ArrayList<Pawn>> checkSkill(HashMap<String, ArrayList<Pawn>> hm, String s) {
        double bp;
        if (roster.size() == 3) {
            bp = .4;
            hm = addToHash(hm, s, bp);
        } else if (roster.size() > 3 && roster.size() <= 6) {
            bp = .3;
            hm = addToHash(hm, s, bp);
        } else if (roster.size() > 6 && roster.size() <= 10) {
            bp = .2;
            hm = addToHash(hm, s, bp);
        } else {
            bp = .1;
            hm = addToHash(hm, s, bp);
        }
        return hm;
    }

    private HashMap<String, ArrayList<Pawn>> addToHash(HashMap<String, ArrayList<Pawn>> hm, String s, double bp) {
        for (Pawn p : roster) {
            switch (s) {
                case "shooting":
                    if (p.getShootingSkill() > shootingSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "melee":
                    if (p.getMeleeSkill() > meleeSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "social":
                    if (p.getSocialSkill() > socialSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "animal":
                    if (p.getAnimalSkill() > animalSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "doctor":
                    if (p.getDoctorSkill() > doctorSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "cooking":
                    if (p.getCookingSkill() > cookingSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "gardening":
                    if (p.getGardeningSkill() > gardeningSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "mining":
                    if (p.getMiningSkill() > miningSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "construction":
                    if (p.getConstructionSkill() > constructionSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "crafting":
                    if (p.getCraftingSkill() > craftingSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "art":
                    if (p.getArtSkill() > artSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                case "research":
                    if (p.getResearchSkill() > researchSkill * bp) {
                        hm.get(s).add(p);
                    }
                    break;
                default:
                    System.out.println("Skill string " + s + " not found for addToHash!");
            }
        }
        return hm;
    }

    private static void resetTotals() {
        shootingSkill = 0;
        meleeSkill = 0;
        socialSkill = 0;
        animalSkill = 0;
        doctorSkill = 0;
        cookingSkill = 0;
        gardeningSkill = 0;
        miningSkill = 0;
        constructionSkill = 0;
        craftingSkill = 0;
        artSkill = 0;
        researchSkill = 0;
    }

}
