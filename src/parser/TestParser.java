package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ast.Program;
import visitor.PrintVisitor;

public class TestParser {
    public static String performParsing(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return filePath + " was not found!";
        }

        try (FileReader reader = new FileReader(file)) {
            Parser parser = new Parser(reader);
            Program program = parser.parseProgram();

            long startTime = System.currentTimeMillis();
            long endTime;

            endTime = System.currentTimeMillis();
            String result = "File has finished parsing!\n" +
                            "Execution time: " + (endTime - startTime) + "ms\n" +
                            parser.getErrors() + " errors reported\n---";

            // print out AST
            PrintVisitor printer = new PrintVisitor();
            printer.visit(program);

            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error occurred while parsing the file.";
        }
    }
}
