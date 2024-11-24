package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class SimpleGUI {

    public static final String TITLE = "My first Java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    
    public SimpleGUI(final Controller c) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea();
        final JTextField field = new JTextField();
        final JPanel panel = new JPanel();
        final JButton print = new JButton("Stampa");
        final JButton mostra = new JButton("Mostra Cronologia");
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        panel.setLayout(new BorderLayout());
        panel.add(field, BorderLayout.NORTH);
        panel.add(text, BorderLayout.CENTER);
        southPanel.add(print);
        southPanel.add(mostra);
        panel.add(southPanel, BorderLayout.SOUTH);
        text.setEditable(false);
        frame.setContentPane(panel);

        print.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                c.setString(field.getText());
                c.printCurrentString();
            }
        });

        mostra.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder s = new StringBuilder();
                for(String s1 : c.getAllStringPrint()) {
                    s.append(s1).append('\n');
                }
                text.setText(s.toString());
            }
        });
    }  

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
     } 

}
