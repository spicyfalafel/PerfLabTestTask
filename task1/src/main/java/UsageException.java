public class UsageException extends Exception {
    public UsageException(String message) {
        System.out.println(message);
        System.out.println("Usage: nb is integer that is more than 0," +
                "base should be in format \"0123456789abcdefghijklmn\" etc, " +
                "only digits and english letters allowed.");
    }
}
