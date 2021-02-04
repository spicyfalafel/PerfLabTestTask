import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        /*
        There's interesting case: <some string> *
        shell adds all files into args
         */
        String[] input = args;
        // if it's the case: replace all files nearby with one *
        String joined = String.join(" ", input);
        if (joined.contains(listFilesUsingFilesList("./"))) {
            long files = getFilesNumber("./");
            // a * * will give error
            // a * won't
            if (files == input.length - 1) {
                input = new String[]{args[0], "*"};
            }
        }

        if (input.length != 2) {
            System.out.println("Please enter 2 strings to compare");
            System.out.println("Given: " + input.length);
            System.exit(-1);
        }
        String first = input[0];
        String second = input[1];
        if (first == null) {
            System.exit(-1);
        }
        boolean isEqual;
        if (second.contains("*")) {
            isEqual = checkWithStar(first, second);
        } else {
            isEqual = first.equals(second);
        }
        System.out.println(isEqual ? "OK" : "KO");
    }

    public static boolean checkWithStar(String first, String withStar) {
        String replace = withStar.replace("*", ".*");
        return first.matches(replace);
    }

    public static long getFilesNumber(String dir) {
        Path path = Paths.get(dir);

        try (Stream<Path> p = Files.list(path)) {
            return p.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String listFilesUsingFilesList(String dir) {
        Path path = Paths.get(dir);
        try (Stream<Path> stream = Files.list(path)) {
            return stream
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.joining(" "));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}