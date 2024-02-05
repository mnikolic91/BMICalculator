package Data_Save_Load_BMI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveToText<T> implements SaveStrategy<List<T>> {
    @Override
    public void saveData(List<T> data, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (T item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println("Data saved to text file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
