package com.university;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SolutionTest {

    @Test
    public void testSolutionCSVMatchesExpected() {
        String studentFilePath = "src/main/resources/student.csv";
        String expectedFilePath = "src/main/resources/expected.csv";

        try {
            Files.deleteIfExists(Paths.get(studentFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to delete pre-existing student.csv file.");
        }

        try {
            App.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to execute App.main() with exception: " + e.getMessage());
        }

        if (!Files.exists(Paths.get(studentFilePath))) {
            fail("The student.csv file does not exist after running the test.");
        }

        try (BufferedReader studentReader = new BufferedReader(new FileReader(studentFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {
            String studentLine;
            String expectedLine;
            while ((studentLine = studentReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                assertEquals(expectedLine, studentLine, "Mismatch found in the CSV file content.");
            }

            assertEquals(studentReader.readLine(), expectedReader.readLine(), "Files have different number of lines.");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while reading the CSV files: " + e.getMessage());
        }

        try {
            Files.deleteIfExists(Paths.get(studentFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to delete student.csv after the test.");
        }
    }
}
