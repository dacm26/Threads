/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Person;

/**
 *
 * @author Daniel
 */
public class DiskOutputThread extends Thread {

    private String file;
    private Person person;

    public DiskOutputThread(String file, Person person) {
        this.file = file;
        this.person = person;
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(
                    this.file, true)));
            pw.println(this.person.toString());

        } catch (IOException ex) {
            Logger.getLogger(DiskOutputThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pw.close();
        }

    }

}
