import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NegativeNumberException, BadBaseException {
        if(args == null || args.length == 0) {
            System.out.println("Please enter args");
            System.exit(-1);
        }
        Iterator<String> it = Arrays.stream(args).iterator();
        while(it.hasNext()){
            int nb = Integer.parseInt(it.next());
            String base = "";
            if (it.hasNext())
                base = it.next();

            System.out.println(itoBase(nb, base));
        }
    }

    //@SneakyThrows
    // nb - число, base - "01", "012", "123456789abcdef", "котики"...
    public static String itoBase(int nb, String base) throws NegativeNumberException, BadBaseException {
        if (nb<0) throw new NegativeNumberException("Negative numbers are not supported.");
        if (base == null || "".equals(base)) throw new BadBaseException("Base was null or empty.");
        if (strContainsDuplicateChars(base)) throw new BadBaseException("Base should not contain duplicate characters.");
        if (!checkRegex(base)) throw new BadBaseException("Base contained invalid characters.");
        int temp = nb;
        int baseInt = base.length();
        StringBuilder result = new StringBuilder();

        do {
            result.insert(0, base.charAt(temp % baseInt));
            temp/=baseInt;
        } while(temp > 0);
        return result.toString();
    }

    // поскольку UTF-16 в Java не гарантирует, что символ будет
    // кодироваться одним char'ом, мне легче ограничить вот так так
    private static boolean checkRegex(String base) {
        return base.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean strContainsDuplicateChars(String str){
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars){
            if (map.containsKey(c)){
                return true;
            }else{
                map.put(c, 1);
            }
        }
        return false;
    }
}
