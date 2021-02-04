package util;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log
public class LogReader {

    @SneakyThrows
    public List<String> readLog(String pathToLog){
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(pathToLog))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        }
    }


}
