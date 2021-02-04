package util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// copy-paste https://www.baeldung.com/java-csv
public class CSVSaver {

    private static List<String[]> dataLines = new ArrayList<>();

    @SneakyThrows
    public static void saveToCSV(String[] answers){

        dataLines.add(new String[]
                {
                        "Tries to top up", "Mistakes percent", "Volume of water (top up)",
                        "!Volume of water (top up)", "Tries to scoop",
                        "Volume of water (scoop)", "!Volume of water (scoop)"
                });
        dataLines.add(answers);
        givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(CSVSaver::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File csvOutputFile = new File("answer.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(CSVSaver::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
