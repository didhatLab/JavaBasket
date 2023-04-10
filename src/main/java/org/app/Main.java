package org.app;

import org.cli.TextEater;
import org.cli.TextFeeder;
import org.persondefiner.DefinedPerson;
import org.persondefiner.DefinerService;
import org.persondefiner.InputParser;
import org.persondefiner.Person;

public class Main {
    public static void main(String[] args) {

        TextEater reader = new TextEater();
        TextFeeder writer = new TextFeeder();
        DefinerService DefinerPersonService = new DefinerService();
        InputParser parse = new InputParser();

        while (true) {
            writer.feed("Input person data: ");
            reader.eat(1);
            try {
                Person person = parse.parseInput(reader.get_last_string());
                DefinedPerson definedPerson = DefinerPersonService.definedPerson(person);
                writer.feed(definedPerson.toString());
            } catch (Exception e){
                writer.feed("error while parse statement: use right format: Name SecondName Patr Date(dd.MM.yyyy)");
            }

        }
    }

}
