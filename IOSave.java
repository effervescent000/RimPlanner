/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Tara
 */
public class IOSave {

    private static final String save = "pawnsave.xml";

    public IOSave() {
    }

    /**
     * The method that builds the XML save file.
     *
     * @return True if save file created successfully, otherwise false.
     */
    public boolean buildXML(Roster roster) {
        try {

            File file = new File(save);
            JAXBContext jax = JAXBContext.newInstance(roster.getClass());
            Marshaller m = jax.createMarshaller();

            //this sets pretty printing
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(roster, file);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
