import javax.swing.*;

import lexer.Lexer;
import lexer.Token;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.io.Reader;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analysis Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Choose an option:");
        label.setBounds(10, 20, 150, 25);
        panel.add(label);

        String[] options = {"Lexer", "Parser", "Semantic"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(170, 20, 100, 25);
        panel.add(comboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(90, 80, 100, 25);
        panel.add(submitButton);

        JTextArea codeTextArea = new JTextArea(); // Text area for code input
        codeTextArea.setBounds(10, 110, 260, 50);
        panel.add(codeTextArea);
        
        JTextField h1Field = new JTextField(); // Text field for H1 value
        h1Field.setBounds(10, 170, 260, 25);
        h1Field.setVisible(false); // Initially hidden
        panel.add(h1Field);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                String code = codeTextArea.getText(); // Get code input from the text area
                String h1Value = h1Field.getText(); // Get H1 value from the text field
                performAnalysis(selectedOption, code, h1Value, h1Field);
            }
        });

        // Add a listener to the combo box to toggle visibility of the H1 field
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("Lexer")) {
                    h1Field.setVisible(true);
                } else {
                    h1Field.setVisible(false);
                }
            }
        });
    }

    public static void performAnalysis(String option, String code, String h1Value, JTextField h1Field) {
        switch (option) {
            case "Lexer":
                performLexerAnalysis(code, h1Value);
                break;
            case "Parser":
                performParserAnalysis(code);
                break;
            case "Semantic":
                performSemanticAnalysis(code);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void performLexerAnalysis(String code, String h1Value) {
        System.out.println("Input code: " + code);
        System.out.println("H1 Value: " + h1Value);

        try (Reader reader = new StringReader(code)) {
            Lexer lexer = new Lexer(reader);
            Token token;
            while ((token = lexer.getToken()) != null) {
                System.out.println("Token: " + token);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error analysis: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void performParserAnalysis(String code) {
        // Perform parser analysis
    }

    private static void performSemanticAnalysis(String code) {
        // Perform semantic analysis
    }
}
