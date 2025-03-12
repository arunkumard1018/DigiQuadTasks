import task_02.ConverterUI;

import javax.swing.*;

public class Task_02_App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConverterUI converterUI = new ConverterUI();
            converterUI.createAndShowGUI();
        });
    }
}
