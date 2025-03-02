package atan.code.verbose;

public class Utils {
    static void assertIfFalse(boolean result) {
        if (!result) {
            throw new AssertionError();
        }
    }
}
