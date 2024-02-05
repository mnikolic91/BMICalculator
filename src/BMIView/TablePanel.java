package BMIView;


import BMILogic.ObserverInt;
import BMILogic.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class TablePanel extends JPanel implements ObserverInt {

    private JTable table;


    public TablePanel() {
        initComps();
        layoutComps();
    }

    private void initComps() {
        String[] columnNames = {"Height", "Weight", "Category", "BMI"};

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table = new JTable(model);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }


    public void update(List<Person> persons) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Person person : persons) {
            if (!containsPerson(model, person)) {
                model.addRow(new Object[]{person.getHeight(), person.getWeight(), person.getCategory(), person.getBmi()});
            }
        }
    }

    private boolean containsPerson(DefaultTableModel model, Person person) {
        for (int row = 0; row < model.getRowCount(); row++) {
            if (personEqualsTableRow(person, model, row)) {
                return true;
            }
        }
        return false;
    }

    private boolean personEqualsTableRow(Person person, DefaultTableModel model, int row) {
        return person.getHeight() == (float) model.getValueAt(row, 0) &&
                person.getWeight() == (float) model.getValueAt(row, 1) &&
                person.getCategory().equals(model.getValueAt(row, 2)) &&
                person.getBmi() == (float) model.getValueAt(row, 3);
    }

}


