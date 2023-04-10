package org.persondefiner;

import java.time.LocalDate;

public class Person {
    public String fullName;
    public LocalDate dataOfBirth;

    Person(String fullName, LocalDate dataOfBirth){
        this.fullName = fullName;
        this.dataOfBirth = dataOfBirth;
    }


}
