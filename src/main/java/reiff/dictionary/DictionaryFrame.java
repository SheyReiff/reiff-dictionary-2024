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
            System.exit(1);
        }

        setSize(400, 300);
        setTitle("DictionaryFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        setContentPane(main);

        JPanel north = new JPanel();
        main.add(north, BorderLayout.NORTH);

        wordField = new JTextField();
        wordField.setPreferredSize(new Dimension(getWidth(), 30));
        definitionsArea = new JTextArea();
        definitionsArea.setEditable(false);

        north.add(wordField);

        main.add(new JScrollPane(definitionsArea), BorderLayout.CENTER);

        setupListeners();
        setVisible(true);
    }

    private void setupListeners() {
        wordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                String word = wordField.getText().trim();
                List<String> definitions = (dictionary != null) ? dictionary.getDefinition(word) : null;
                displayDefinitions(definitions);
            }
        });
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
