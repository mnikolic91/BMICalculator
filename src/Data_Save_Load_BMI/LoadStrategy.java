package Data_Save_Load_BMI;


public interface LoadStrategy<T> {
    T loadData(String path);
}