package org.persondefiner;

public class DefinedPerson {
    public String SecondName;
    public String Initials;
    public int Age;
    public String AgePoint;
    public String Sex;

    public DefinedPerson(String secondName, String initials, int age, String agePoint, String sex){
        this.SecondName = secondName;
        this.Initials = initials;
        this.Age = age;
        this.AgePoint = agePoint;
        this.Sex = sex;
    }

    @Override
    public String toString() {
        return this.SecondName + " " + this.Initials + " " + this.Sex + " " + this.Age + " " + this.AgePoint;
    }
}
