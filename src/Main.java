import javax.swing.*;
import lexer.TestLexer;
import parser.TestParser;
import semantic.TestSemantic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Code Analyzer Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Increase the width and height of the window
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Select a file:");
        label.setBounds(10, 20, 100, 25);
        panel.add(label);

        JButton selectButton = new JButton("Select File");
        selectButton.setBounds(120, 20, 150, 25);
        panel.add(selectButton);

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setBounds(100, 150, 1060, 5000); // You can adjust the width and height accordingly
        panel.add(outputTextArea);

        JRadioButton lexicalButton = new JRadioButton("Lexical Analysis");
        lexicalButton.setBounds(10, 80, 150, 25); // Adjusted Y coordinate
        panel.add(lexicalButton);
        
        JRadioButton semanticButton = new JRadioButton("Semantic Analysis");
        semanticButton.setBounds(160, 80, 150, 25); // Adjusted Y coordinate
        panel.add(semanticButton);
        
        JRadioButton parserButton = new JRadioButton("Parser Analysis");
        parserButton.setBounds(310, 80, 150, 25); // Adjusted Y coordinate
        panel.add(parserButton);
        

        ButtonGroup analysisGroup = new ButtonGroup();
        analysisGroup.add(lexicalButton);
        analysisGroup.add(semanticButton);
        analysisGroup.add(parserButton);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();

                    try {
                        String analysisResult = "";
                        if (lexicalButton.isSelected()) {
                            analysisResult = TestLexer.analyzeFile(filePath);
                        } else if (semanticButton.isSelected()) {
                            analysisResult = TestSemantic.performAnalysis(filePath);
                        } else if (parserButton.isSelected()) {
                            analysisResult = TestParser.performParsing(filePath);
                        }
                        outputTextArea.setText(analysisResult);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        outputTextArea.setText("Error occurred while analyzing the file.");
                    }
                }
            }
        });
    }
}
