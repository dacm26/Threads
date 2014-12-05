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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Person;

/**
 *
 * @author Daniel
 */
public class DiskOutputThread extends Thread {

    private Stack<Person> backlog;
    private String outputPath;

    public DiskOutputThread(String outputPath,Stack<Person> backlog) {
        this.outputPath = outputPath;
        this.backlog = backlog;
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        while (true) {
            if (!(this.backlog.isEmpty())) {
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(
                            this.outputPath, true)));
                    Person temp=this.backlog.pop();
                    pw.println(temp.toString());

                } catch (IOException ex) {
                    Logger.getLogger(DiskOutputThread.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    pw.close();
                }
            }
        }

    }

}
