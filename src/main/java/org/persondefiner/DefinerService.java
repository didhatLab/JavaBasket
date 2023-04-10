package org.persondefiner;

import java.time.LocalDate;
import java.time.Period;

public class DefinerService {

    public DefinedPerson definedPerson(Person person){
        String[] NameParts = person.fullName.split(" ");
        String sex = defineSex(NameParts[2]);
        int age = defineAge(person.dataOfBirth);
        String agePoint = defineAgePoint(age);
        String initials = defineInitials(NameParts);

        return new DefinedPerson(NameParts[1], initials, age, agePoint, sex);

    }

    private String defineSex(String patronomic){
        if (patronomic.endsWith("ич")) {
            return "М";
        } else if (patronomic.endsWith("на")) {
            return "Ж";
        }
        return "Неизвестно";
    }
    private int defineAge(LocalDate date){
        return Period.between(date, LocalDate.now()).getYears();
    }

    private String defineAgePoint(int age){
        int funcAge = age % 10;

        if (funcAge == 1){
            return "год";
        }
        else if (funcAge < 5 && funcAge > 1){
            return "года";
        }
        else {
            return "лет";
        }
    }

    private String defineInitials(String[] nameParts){
        String firstName = nameParts[1];
        String patr = nameParts[2];

        return String.format("%s.%s", firstName.charAt(0), patr.charAt(0));
    }


}
