/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Tara
 */
public class ReadSave {

    private static final String save = "pawnsave.xml";

    public ReadSave() {
    }

    public Roster readXML() {
        try {
            File file = new File(save);
            JAXBContext jax = JAXBContext.newInstance(Roster.class);

            Unmarshaller u = jax.createUnmarshaller();
            Roster roster = (Roster) u.unmarshal(file);
            return roster;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

}
