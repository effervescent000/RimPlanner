/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tara
 */

@XmlRootElement
public class Pawn {

//    private String firstName;
    private String name;
//    private String lastName;
    private int age;
    private boolean female;

    // skills that are incapable are set to -1
    private int shootingSkill;
    private int meleeSkill;
    private int socialSkill;
    private int animalSkill;
    private int doctorSkill;
    private int cookingSkill;
    private int gardeningSkill;
    private int miningSkill;
    private int constructionSkill;
    private int craftingSkill;
    private int artSkill;
    private int researchSkill;

    //Passions: 0 = no passion, 1 = 1 flame, 2 = 2 flames
    private int shootingPassion;
    private int meleePassion;
    private int socialPassion;
    private int animalPassion;
    private int doctorPassion;
    private int cookingPassion;
    private int gardeningPassion;
    private int miningPassion;
    private int constructionPassion;
    private int craftingPassion;
    private int artPassion;
    private int researchPassion;

    //incapable booleans for jobs that don't have skills but pawns can still refuse to do
    private boolean haulingCapable;
    private boolean cleaningCapable;

    private ArrayList<String> traits;

    private String childBS;
    private String adultBS;

    public Pawn() {}
    
    public Pawn(String name, int age) {
        this.name = name;
        this.age = age;

        //the extra capable booleans default to true
        haulingCapable = true;
        cleaningCapable = true;

        traits = new ArrayList<>();
    }

    @XmlElement
    public String getAdultBS() {
        return adultBS;
    }
    
    @XmlAttribute
    public int getAge() {
        return age;
    }

    @XmlElement
    public int getAnimalPassion() {
        return animalPassion;
    }
    
    @XmlElement
    public int getAnimalSkill() {
        return animalSkill;
    }

    @XmlElement
    public int getArtPassion() {
        return artPassion;
    }

    @XmlElement
    public int getArtSkill() {
        return artSkill;
    }

    @XmlElement
    public String getChildBS() {
        return childBS;
    }

    @XmlElement
    public int getConstructionPassion() {
        return constructionPassion;
    }

    @XmlElement
    public int getConstructionSkill() {
        return constructionSkill;
    }

    @XmlElement
    public int getCookingPassion() {
        return cookingPassion;
    }

    @XmlElement
    public int getCookingSkill() {
        return cookingSkill;
    }

    @XmlElement
    public int getCraftingPassion() {
        return craftingPassion;
    }

    @XmlElement
    public int getCraftingSkill() {
        return craftingSkill;
    }

    @XmlElement
    public int getDoctorPassion() {
        return doctorPassion;
    }

    @XmlElement
    public int getDoctorSkill() {
        return doctorSkill;
    }

//    public String getFirstName() {
//        return firstName;
//    }

    @XmlElement
    public int getGardeningPassion() {
        return gardeningPassion;
    }

    @XmlElement
    public int getGardeningSkill() {
        return gardeningSkill;
    }

//    public String getLastName() {
//        return lastName;
//    }

    @XmlElement
    public int getMeleePassion() {
        return meleePassion;
    }

    @XmlElement
    public int getMeleeSkill() {
        return meleeSkill;
    }

    @XmlElement
    public int getMiningPassion() {
        return miningPassion;
    }

    @XmlElement
    public int getMiningSkill() {
        return miningSkill;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @XmlElement
    public int getResearchPassion() {
        return researchPassion;
    }

    @XmlElement
    public int getResearchSkill() {
        return researchSkill;
    }

    @XmlElement
    public int getShootingPassion() {
        return shootingPassion;
    }

    @XmlElement
    public int getShootingSkill() {
        return shootingSkill;
    }

    @XmlElement
    public int getSocialPassion() {
        return socialPassion;
    }

    @XmlElement
    public int getSocialSkill() {
        return socialSkill;
    }

    @XmlElement
    public ArrayList<String> getTraits() {
        return traits;
    }

    @XmlElement
    public boolean isCleaningCapable() {
        return cleaningCapable;
    }

    @XmlElement
    public boolean isFemale() {
        return female;
    }

    @XmlElement
    public boolean isHaulingCapable() {
        return haulingCapable;
    }
    
    public void setAdultBS(String adultBS) {
        this.adultBS = adultBS;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    private boolean validatePassion(int i) {
        if (i < 0 || i > 2) {
            System.out.println("Invalid arg " + i + " passed to a passion setter");
            return false;
        } else {
            return true;
        }
    }

    public void setAnimalPassion(int animalPassion) {
        if (validatePassion(animalPassion)) {
            this.animalPassion = animalPassion;
        }
    }

    public void setAnimalSkill(int animalSkill) {
        this.animalSkill = animalSkill;
    }

    public void setArtPassion(int artPassion) {
        if (validatePassion(artPassion)) {
            this.artPassion = artPassion;
        }
    }

    public void setArtSkill(int artSkill) {
        this.artSkill = artSkill;
    }

    public void setChildBS(String childBS) {
        this.childBS = childBS;
    }

    public void setCleaningCapable(boolean cleaningCapable) {
        this.cleaningCapable = cleaningCapable;
    }

    public void setConstructionPassion(int constructionPassion) {
        if (validatePassion(constructionPassion)) {
            this.constructionPassion = constructionPassion;
        }
    }

    public void setConstructionSkill(int constructionSkill) {
        this.constructionSkill = constructionSkill;
    }

    public void setCookingPassion(int cookingPassion) {
        if (validatePassion(cookingPassion)) {
            this.cookingPassion = cookingPassion;
        }
    }

    public void setCookingSkill(int cookingSkill) {
        this.cookingSkill = cookingSkill;
    }

    public void setCraftingPassion(int craftingPassion) {
        if (validatePassion(craftingPassion)) {
            this.craftingPassion = craftingPassion;
        }
    }

    public void setCraftingSkill(int craftingSkill) {
        this.craftingSkill = craftingSkill;
    }

    public void setDoctorPassion(int doctorPassion) {
        if (validatePassion(doctorPassion)) {
            this.doctorPassion = doctorPassion;
        }
    }

    public void setDoctorSkill(int doctorSkill) {
        this.doctorSkill = doctorSkill;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public void setGardeningPassion(int gardeningPassion) {
        if (validatePassion(gardeningPassion)) {
            this.gardeningPassion = gardeningPassion;
        }
    }

    public void setGardeningSkill(int gardeningSkill) {
        this.gardeningSkill = gardeningSkill;
    }

    public void setHaulingCapable(boolean haulingCapable) {
        this.haulingCapable = haulingCapable;
    }

    public void setMeleePassion(int meleePassion) {
        if (validatePassion(meleePassion)) {
            this.meleePassion = meleePassion;
        }

    }

    public void setMeleeSkill(int meleeSkill) {
        this.meleeSkill = meleeSkill;
    }

    public void setMiningPassion(int miningPassion) {
        if (validatePassion(miningPassion)) {
            this.miningPassion = miningPassion;
        }
    }

    public void setMiningSkill(int miningSkill) {
        this.miningSkill = miningSkill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResearchPassion(int researchPassion) {
        if (validatePassion(researchPassion)) {
            this.researchPassion = researchPassion;
        }
    }

    public void setResearchSkill(int researchSkill) {
        this.researchSkill = researchSkill;
    }

    public void setShootingPassion(int shootingPassion) {
        if (validatePassion(shootingPassion)) {
            this.shootingPassion = shootingPassion;
        }
    }

    public void setShootingSkill(int shootingSkill) {
        this.shootingSkill = shootingSkill;
    }

    public void setSocialPassion(int socialPassion) {
        if (validatePassion(socialPassion)) {
            this.socialPassion = socialPassion;
        }

    }

    public void setSocialSkill(int socialSkill) {
        this.socialSkill = socialSkill;
    }

    public void setTraits(ArrayList<String> traits) {
        this.traits = traits;
    }

}
