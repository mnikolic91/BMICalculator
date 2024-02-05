package BMIView;


import BMILogic.ObserverInt;
import BMILogic.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ProgressPanel extends JPanel implements ObserverInt {


    private JPanel insidePanel;
    private JProgressBar progressBar;

    int entries;

    public ProgressPanel(){
        initComps();
        layoutComps();
    }

    private void initComps() {
        insidePanel = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
    }

    private void layoutComps() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        insidePanel.setBorder(BorderFactory.createTitledBorder("Progress"));
        add(insidePanel);
        insidePanel.setLayout(new GridBagLayout());
        insidePanel.add(progressBar);
    }

    public void clearProgress() {
        progressBar.setValue(0);
        progressBar.setString("0%");

    }

    @Override
    public void update(List<Person> persons) {
        entries++;
        progressBar.setValue(entries * 20);
        progressBar.setString(entries * 20 + "%");

    }

    public void setEntries(int entries) {
        this.entries = entries;
    }
}