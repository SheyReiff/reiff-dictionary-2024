package reiff.dictionary;

import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;

public class DictionaryFrame extends JFrame {
    private final JTextField wordField;
    private final JTextArea definitionsArea;
    private EnglishDictionary dictionary;

    public DictionaryFrame() {
        try {
            dictionary = new EnglishDictionary();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the dictionary: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        final JFrame frame = new JFrame("DictionaryFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());

        wordField = new JTextField();
        wordField.setPreferredSize(new Dimension(frame.getWidth(), 30));
        wordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String word = wordField.getText().trim().toLowerCase();
                List<String> definitions = dictionary.getDefinition(word);
                displayDefinitions(definitions);
            }
        });

        definitionsArea = new JTextArea();
        definitionsArea.setEditable(false);

        panel.add(wordField, BorderLayout.NORTH);
        panel.add(new JScrollPane(definitionsArea), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void displayDefinitions(List<String> definitions) {
        definitionsArea.setText("");
        if (definitions != null && !definitions.isEmpty()) {
            for (String definition : definitions) {
                definitionsArea.append(definition + "\n");
            }
        } else {
            definitionsArea.setText("No definitions found for the word.");
        }
    }
}




