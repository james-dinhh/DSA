import java.util.Arrays;

public class SecretKeyGuesser {
    private char[] currentGuess;
    private boolean[] fixed;
    private final char[] characters = {'M', 'O', 'C', 'H', 'A'};
    private final int keyLength = 12;


    public SecretKeyGuesser() {

        currentGuess = new char[keyLength];
        fixed = new boolean[keyLength];
        Arrays.fill(currentGuess, 'M');
        Arrays.fill(fixed, false);
    }


    public void start() {
        SecretKey key = new SecretKey();
        int matched = 0;

        while (matched != keyLength) {
            matched = key.guess(new String(currentGuess));
            if (matched < keyLength) {
                System.out.println("Current guess: " + new String(currentGuess) + ", matched: " + matched);
                // Only update the guess if it's not fully matched
                updateGuess(matched, key);
            }
        }
        // Print final message here, after the loop
        System.out.println("I found the secret key. It is " + new String(currentGuess));
    }

    private void updateGuess(int matchedCount, SecretKey key) {
        for (int i = 0; i < keyLength; i++) {
            char originalChar = currentGuess[i];
            for (char c : characters) {
                if (c != originalChar) {
                    currentGuess[i] = c;
                    int newMatchedCount = key.guess(String.valueOf(currentGuess));
                    if (newMatchedCount > matchedCount) {
                        // The new character is correct for this position, keep it and break
                        break;
                    } else {
                        // Revert to the original character if no improvement
                        currentGuess[i] = originalChar;
                    }
                }
            }
        }
    }
}



