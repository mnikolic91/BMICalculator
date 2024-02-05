package Data_Save_Load_BMI;

public interface SaveStrategy<T> {
    void saveData(T data, String path);
}