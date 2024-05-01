package semantic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestSemantic {
    public static String performAnalysis(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return filePath + " was not found!";
        }

        try (FileReader reader = new FileReader(file)) {
            SemanticAnalyzer semantic = new SemanticAnalyzer(reader);
            semantic.analyzeProgram();

            long startTime = System.currentTimeMillis();
            long endTime;

            endTime = System.currentTimeMillis();
            String result = "File has finished analyzing!\n" +
                            "Execution time: " + (endTime - startTime) + "ms\n" +
                            semantic.getErrors() + " errors reported\n---";

            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error occurred while analyzing the file.";
        }
    }
}
