package BMIView;


import BMILogic.ToolBarListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar implements ActionListener {

    private JButton btnClear;
    private JButton btnSave;

    private JButton btnLoad;

    private JButton btnExit;

    private ToolBarListener toolBarListener;

    public ToolBar(){
        initComps();
        layoutComps();
        activateToolbar();
    }

    private void initComps(){
        btnClear = new JButton("Clear");
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");
        btnExit = new JButton("Exit");
    }

    private void layoutComps(){
        add(btnClear);
        addSeparator();
        add(btnSave);
        addSeparator();
        add(btnLoad);
        addSeparator();
        add(btnExit);
    }

    private void activateToolbar(){

        btnClear.addActionListener(this);
        btnSave.addActionListener(this);
        btnLoad.addActionListener(this);
        btnExit.addActionListener(this);

    }

    public void setToolBarListener(ToolBarListener toolBarListener){
        this.toolBarListener = toolBarListener;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnClear){
            if (toolBarListener != null){
                toolBarListener.clearEventOccurred();
            }
        } else if (ae.getSource() == btnSave) {
            if (toolBarListener != null){
                toolBarListener.saveEventOccurred();
            }
        } else if (ae.getSource() == btnLoad) {
            if(toolBarListener != null){
                toolBarListener.loadEventOccurred();
            }
        } else {
            if (toolBarListener != null){
                toolBarListener.exitEventOccurred();
            }
        }

    }
}
