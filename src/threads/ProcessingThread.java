/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.Stack;
import test.Person;

/**
 *
 * @author Daniel
 */
public class ProcessingThread extends Thread {

    private Stack<Person> DIT_backlog;
    private Stack<Person> DOT_backlog;

    public ProcessingThread(Stack<Person> backlog) {
        this.DIT_backlog = backlog;
        this.DOT_backlog = new Stack<>();
    }

    public Stack<Person> getDIT_backlog() {
        return DIT_backlog;
    }

    public void setDIT_backlog(Stack<Person> DIT_backlog) {
        this.DIT_backlog = DIT_backlog;
    }

    public Stack<Person> getDOT_backlog() {
        return DOT_backlog;
    }

    public void setDOT_backlog(Stack<Person> DOT_backlog) {
        this.DOT_backlog = DOT_backlog;
    }

    @Override
    public void run() {
        while (true) {
            if (!(this.DIT_backlog.isEmpty())) {
                Person temp = DIT_backlog.pop();
                int age = temp.getAge();
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
                temp.setAgeband(ageband);
                if (temp.getAge() >= 18) {
                    DOT_backlog.push(temp);
                }
            }

        }

    }
}
