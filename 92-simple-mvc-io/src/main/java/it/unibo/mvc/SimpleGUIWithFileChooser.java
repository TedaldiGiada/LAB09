package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public final class SimpleGUIWithFileChooser {

    public static final String TITLE = "My first Java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    
    public SimpleGUIWithFileChooser(final Controller c) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea();
        final JTextField sfoglia = new JTextField(c.getPathCurrentFile());
        final JPanel panel = new JPanel();
        final JPanel panel1 = new JPanel();
        final JButton save = new JButton("Salva");
        final JButton browser = new JButton("Browser");
        panel.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());
        panel.add(panel1, BorderLayout.NORTH);
        panel.add(save, BorderLayout.SOUTH);
        panel.add(text, BorderLayout.CENTER);
        panel1.add(sfoglia, BorderLayout.CENTER);
        panel1.add(browser, BorderLayout.LINE_END);
        frame.setContentPane(panel);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.saveFile(text.getText());
                } catch(IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        browser.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser file = new JFileChooser("Choose where to save");
                file.setSelectedFile(c.getCurrentFile());
                final int result = file.showOpenDialog(frame);
                switch(result) {
                    case JFileChooser.APPROVE_OPTION:
                        File selectedFile = file.getSelectedFile();
                        c.setCurrentFile(selectedFile);
                        sfoglia.setText(c.getPathCurrentFile());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }  

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
     } 
}
