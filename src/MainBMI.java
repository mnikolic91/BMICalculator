

import BMIView.MainFrame;

import javax.swing.*;

public class MainBMI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
