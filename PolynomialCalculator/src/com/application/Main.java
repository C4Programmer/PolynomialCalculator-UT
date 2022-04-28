

package com.application;

import com.gui.CalculatorInterface;

import javax.swing.*;
import javax.swing.UIManager.*;

public class Main {

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            JFrame jFrame = new JFrame("Polynomial Calculator");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setContentPane(new CalculatorInterface().jpanel);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        } catch (Exception e) {
        }
    }
}
