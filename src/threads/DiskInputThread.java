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

/**
 *
 * @author Daniel
 */
public class DiskInputThread extends Thread {

    private String fileName;
    private String outputFileName;
    private BufferedReader file;

    public DiskInputThread(String outputFileName, String fileName) {
        this.fileName = fileName;
        this.outputFileName = outputFileName;
        try {
            this.file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String splitBy = ",";
        String line="";
        try {
            line = this.file.readLine();
        } catch (IOException ex) {
            Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (line != null) {
            String[] b = line.split(splitBy);
            Person temp = new Person(b);
            ProcessingThread PT = new ProcessingThread(temp,this.outputFileName);
            PT.start();
            try {
                line = this.file.readLine();
            } catch (IOException ex) {
                Logger.getLogger(DiskInputThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}


