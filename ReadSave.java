/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rimplanner;

import java.io.BufferedReader;
import java.io.File;

/**
 * This class may be deleted. I had intended to use it to read the save file to
 * get Pawn data but nothing about Pawns is actually stored there, as far as I
 * can tell.
 *
 * @author Tara
 */
public class ReadSave {

    private File fn;
    private BufferedReader br;

    public ReadSave(File fn) {
        this.fn = fn;
    }

    private void openFile() {
        if (fn != null) {

        }
    }

}
