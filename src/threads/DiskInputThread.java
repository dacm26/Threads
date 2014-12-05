/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
    private File folder;
    private Stack<Person> backlog;
    private HashSet<String> fileSet;

    public DiskInputThread(String fileName) {
        this.fileName = fileName;
        backlog = new Stack<>();
        fileSet = new HashSet<>();
        this.folder = new File(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        File temp = this.folder.listFiles()[0];
        this.fileSet.add(temp.getName());
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(temp.getPath())));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            line = bf.readLine();
        } catch (IOException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {

            if (line != null) {
                String[] b = line.split(splitBy);
                Person person = new Person(b);
                backlog.push(person);
                try {
                    line = bf.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String name = temp.getName();
                String name2 = "";
                for (final File fileTemp : folder.listFiles()) {
                    if (this.fileSet.add(fileTemp.getName())) {
                        temp = fileTemp;
                        name2 = temp.getName();
                        System.out.println("name2: "+name2);
                        break;
                    }
                    else{
                        name2=name;
                    }
                }
                
                if (!(name.equals(name2))) {
                    try {
                        bf.close();
                        bf = new BufferedReader(new InputStreamReader(new FileInputStream(temp.getPath())));
                    } catch (IOException ex) {
                        Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    line = bf.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

}
