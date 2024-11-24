package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final String PATH = System.getProperty("user.home")
            + File.separator + BadIOGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final Random random = new Random();
    private final JFrame frame = new JFrame(TITLE);

    
    public BadIOGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        final JButton write = new JButton("Write on file");
        panel.add(write);
        final JButton read = new JButton("Read the file");
        panel.add(read);
        canvas.add(panel, BorderLayout.CENTER);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try (PrintStream ps = new PrintStream(PATH, StandardCharsets.UTF_8)) {
                    ps.print(random.nextInt());
                    
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); 
                }
            }
        });
        read.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final List<String> lines = Files.readAllLines(new File(PATH).toPath());
                    for(String line : lines) {
                        System.out.println(line);
                    }
                } catch(IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
       new BadIOGUI().display();
    }
}
