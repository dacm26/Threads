/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import test.Person;

/**
 *
 * @author Daniel
 */
public class ProcessingThread extends Thread {

    private  Person person;
    private  String path;

    public ProcessingThread(Person person, String path) {
        this.person = person;
        this.path = path;
    }

    @Override
    public void run() {
        int age = this.person.getAge();
        String ageband = "";
        if (age >= 18 && age <= 24) {
            ageband = "18-24";
        } else if (age >= 25 && age <= 30) {
            ageband = "25-30";
        } else if (age >= 31 && age <= 40) {
            ageband = "31-40";
        } else if (age >= 41 && age <= 55) {
            ageband = "41-55";
        } else if (age >= 56 && age <= 70) {
            ageband = "56-70";
        } else if (age >= 71) {
            ageband = "71+";
        }
        this.person.setAgeband(ageband);
        if (this.person.getAge() >= 18) {
            DiskOutputThread DOT = new DiskOutputThread(this.path, this.person);
            DOT.start();
        }

    }
}
