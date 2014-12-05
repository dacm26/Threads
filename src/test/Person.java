/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author Daniel
 */
public class Person {
    private String name;
    private int age;
    private String ageband;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public Person(String[] row){
        this.name=row[0];
        this.age=Integer.parseInt(row[1]);
        this.ageband="";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeband() {
        return ageband;
    }

    public void setAgeband(String ageband) {
        this.ageband = ageband;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + ageband ;
    }
    
    
}
