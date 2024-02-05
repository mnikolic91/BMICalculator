package BMIView;

import BMILogic.*;
import Data_Save_Load_BMI.LoadFromBin;
import Data_Save_Load_BMI.LoadFromText;
import Data_Save_Load_BMI.SaveToBin;
import Data_Save_Load_BMI.SaveToText;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ObservableInt {

    private ViewPanel viewPanel;
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private ProgressPanel progressPanel;
    private NewTablePanel newTablePanel;

    private ToolBar toolBar;

    private final JFileChooser fileChooser = new JFileChooser();

    private static final String DIR = "DATA";

    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<ObserverInt> observers;

    private Person person;


    public MainFrame() {
        super("BMI APP");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        initComps();
        layoutComps();
        activateFrame();
        setVisible(true);
        observers = new ArrayList<>();
        addObserver(viewPanel);
        addObserver(tablePanel);
        addObserver(progressPanel);
        addObserver(newTablePanel);

    }

    private void initComps() {
        viewPanel = new ViewPanel();
        formPanel = new FormPanel();
        toolBar = new ToolBar();
        tablePanel = new TablePanel();
        progressPanel = new ProgressPanel();
        newTablePanel = new NewTablePanel();
        persons = new ArrayList<>();
        fileChooser.setCurrentDirectory(new File(DIR));
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
                "TXT files", "txt");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                "BIN files", "bin");
        fileChooser.setFileFilter(filter1);
        fileChooser.addChoosableFileFilter(filter2);
        alignSaveWithExtensions();
    }

    private void layoutComps() {
        setLayout(new BorderLayout());

        add(toolBar, BorderLayout.NORTH);

        viewPanel.setPreferredSize(new Dimension(250, 250));
        add(viewPanel, BorderLayout.WEST);

        add(progressPanel, BorderLayout.EAST);

        add(formPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(tablePanel, BorderLayout.CENTER);

        newTablePanel.setPreferredSize(new Dimension(centerPanel.getWidth(), 100));
        centerPanel.add(newTablePanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

    }

    private void alignSaveWithExtensions() {

        fileChooser.addActionListener(ae -> {
            if (ae.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                String path = fileChooser.getSelectedFile().getPath();
                if (fileChooser.getFileFilter().getDescription().equals("TXT files")) {
                    if (!path.endsWith(".txt")) {
                        path += ".txt";
                    }
                } else if (fileChooser.getFileFilter().getDescription().equals("BIN files")) {
                    if (!path.endsWith(".bin")) {
                        path += ".bin";
                    }
                }
                fileChooser.setSelectedFile(new File(path));
            }
        });
    }

    private boolean dirExists(String path) {
        return new File(path).exists();
    }


    private void activateFrame() {

        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formEventOccurred(FormEvent fe) {
                person = fe.getPerson();
                persons.add(person);
                notifyObservers();

                if (persons.size() == 5) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Max entry size is 5.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    formPanel.disableForm();
                }
            }
        });


        toolBar.setToolBarListener(new ToolBarListener() {
            @Override
            public void clearEventOccurred() {
                clearEventsOnPanels();
            }

            @Override
            public void saveEventOccurred() {
                int value = fileChooser.showSaveDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    if (ReadWriteClass.fileExtension(path).equals("bin")) {
                        ReadWriteClass.writeToFile(path, new SaveToBin<>(), persons);
                    } else {
                        ReadWriteClass.writeToFile(path, new SaveToText<>(), persons);
                    }
                    notifyObservers();
                }
            }


            @Override
            public void loadEventOccurred() {
                clearEventsOnPanels();
                int value = fileChooser.showOpenDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    if (ReadWriteClass.fileExtension(path).equals("bin"))
                        ReadWriteClass.readFromFile(path, new LoadFromBin<>(), persons);
                    else {
                        ReadWriteClass.readFromFile(path, new LoadFromText(), persons);
                    }
                    progressPanel.setEntries(persons.size() -1);
                    notifyObservers();
                }
            }

            @Override
            public void exitEventOccurred() {
                int value = JOptionPane.showConfirmDialog(null, "Hvala na koristenju aplikacije!", "Izlaz", JOptionPane.CANCEL_OPTION);
                if (value == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void clearEventsOnPanels() {
        viewPanel.clearText();
        tablePanel.clearTable();
        newTablePanel.clearTable();
        progressPanel.clearProgress();
        progressPanel.setEntries(0);
        persons.clear();
    }


    @Override
    public void addObserver(ObserverInt observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInt observer : observers) {
            observer.update(persons);
        }
    }
}
