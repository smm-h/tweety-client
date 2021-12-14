package ir.arg.shared;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * This interface is used to generate random string of arbitrary length
 * comprised only of lowercase hex digits.
 */
public interface RandomHex {

    Random random = new Random();

    /**
     * Generate a random string of lower case hex digits of given length.
     *
     * @param length Length of the generated string
     * @return A randomly generated string
     */
    @NotNull
    static String generate(final int length) {
        final char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            final int x = random.nextInt(16);
            array[i] = (char) (x < 10 ? '0' + x : 'a' + x - 10);
        }
        return new String(array);
    }
}
