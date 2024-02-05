package Data_Save_Load_BMI;


import BMILogic.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoadFromText implements LoadStrategy<List<Person>> {
    @Override
    public List<Person> loadData(String path) {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                persons.add(parsePersonFromString(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }
    private static Person parsePersonFromString(String personString) {
        String[] personData = personString.substring(personString.indexOf("{") + 1, personString.indexOf("}")).split(",", 4);

        if (personData.length == 4) {
            float height = Float.parseFloat(personData[0].split("=")[1]);
            float weight = Float.parseFloat(personData[1].split("=")[1]);
            String category = personData[2].split("=")[1];
            float bmi = Float.parseFloat(personData[3].split("=")[1]);
            return new Person(height, weight, category, bmi);
        } else {
            return null;
        }
    }
}
