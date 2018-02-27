package com.deepak.tools.misc.other;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Swing {

    private static JButton button = new JButton("Click me");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test 123 title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.add(button);
        frame.add(panel);
        
        button.addActionListener(action -> printParentName(action));
        
        frame.pack();
        frame.setVisible(true);
    }

    private static void printParentName(ActionEvent action) {
        JButton buttonSource = (JButton) action.getSource();
        Container parent = buttonSource.getParent();
        while (parent != null && !(parent instanceof JFrame) ) {
            parent = parent.getParent();
        }
        
        JOptionPane.showMessageDialog(parent, ((JFrame) parent).getTitle());
    }
}
