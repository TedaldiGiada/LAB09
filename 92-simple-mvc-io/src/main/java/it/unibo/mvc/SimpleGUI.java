package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    public static final String TITLE = "My first Java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    
    public SimpleGUI(final Controller c) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea();
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JButton save = new JButton("Salva");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.saveFile(text.getText());
                } catch(IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(save, BorderLayout.SOUTH);
        panel.add(text, BorderLayout.CENTER);
        frame.setContentPane(panel);
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
     }

}
