package org.persondefiner;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputParser {

    public Person parseInput(String dataLine){
        String[] data = dataLine.split(" ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[3], formatter);

        String Name = data[0] +" "+ data[1] + " " + data[2];


        return new Person(Name, birthday);
    }

}
