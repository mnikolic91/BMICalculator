package BMILogic;


import Data_Save_Load_BMI.LoadStrategy;
import Data_Save_Load_BMI.SaveStrategy;

import java.util.List;

public class ReadWriteClass {

    public static void readFromFile(String path, LoadStrategy<List<Person>> loadStrategy, List<Person> persons) {
        persons.clear();
        persons.addAll(loadStrategy.loadData(path));
    }

    public static <T> void writeToFile(String path, SaveStrategy<T> saveStrategy, T items) {
        saveStrategy.saveData(items, path);
    }

    public static String fileExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
