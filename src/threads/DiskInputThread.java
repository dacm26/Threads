/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Person;
import java.util.Stack;

/**
 *
 * @author Daniel
 */
public class DiskInputThread extends Thread {

    private String fileName;
    private BufferedReader file;
    private Stack<Person> backlog;

    public DiskInputThread(String fileName) {
        this.fileName = fileName;
        backlog = new Stack<>();
        try {
            this.file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BufferedReader getFile() {
        return file;
    }

    public void setFile(BufferedReader file) {
        this.file = file;
    }

    public Stack<Person> getBacklog() {
        return backlog;
    }

    public void setBacklog(Stack<Person> backlog) {
        this.backlog = backlog;
    }
    
    
    
    @Override
    public void run() {
        String splitBy = ",";
        String line = "";
        try {
            line = this.file.readLine();
        } catch (IOException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            if (line != null) {
                String[] b = line.split(splitBy);
                Person temp = new Person(b);
                backlog.push(temp);
                try {
                    line = this.file.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    line = this.file.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
