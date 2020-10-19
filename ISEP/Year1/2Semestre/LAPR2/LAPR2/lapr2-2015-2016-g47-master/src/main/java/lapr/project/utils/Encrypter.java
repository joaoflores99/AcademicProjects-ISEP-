/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a Encrypter
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class Encrypter {

    public Encrypter() {

    }
    private static final String alphabet = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ÁáÃãÂâÀàÇçÉéÊêÍíÓóÔôÕõÚú+.,_!?@ ");

    /**
     * Encrypts a string using ceasers cypher.
     *
     * @param word the word to encrypt
     * @param shift the shift of characters
     * @return the encrypted string
     */
    public static String encryptStringCaesar(String word, int shift) {
        String encryptedWord = "";
        for (char letter : word.toCharArray()) {
            int index = alphabet.indexOf(letter);
            if (index + shift >= alphabet.length()) {
                index = (index + shift) - alphabet.length();
                encryptedWord += alphabet.charAt(index);

            } else {
                encryptedWord += alphabet.charAt(index + shift);
            }
            ;
        }
        return encryptedWord;
    }

    /**
     * Decrypts a ceasers cypher.
     *
     * @param word the word to decrypt.
     * @param shift the caesers cypher
     * @return the decrypted string
     */
    public static String decryptStringCaesar(String word, int shift) {
        String decryptedWord = "";
        for (char letter : word.toCharArray()) {
            int index = alphabet.indexOf(letter);
            if (index - shift < 0) {
                index = (index - shift) + alphabet.length();
                decryptedWord += alphabet.charAt(index);

            } else {
                decryptedWord += alphabet.charAt(index - shift);
            }

        }
        return decryptedWord;
    }

    /**
     * Encrypts a string using a keyword as a cypher.
     *
     * @param word the word to encrypt
     * @param shift the shift of characters
     * @param keyword the keyword
     * @return the encrypted string
     */
    public static String encryptStringKeyword(String word, int shift, String keyword) {
        String encryptedWord = "";
        String keywordAlphabet = keyword;
        char[] chars = keywordAlphabet.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }
        keywordAlphabet = "";
        for (Character character : charSet) {
            keywordAlphabet += character;
        }
        for (int i = 0; i < alphabet.length(); i++) {

            if (!keywordAlphabet.contains(String.valueOf(alphabet.charAt(i)))) {
                keywordAlphabet += alphabet.charAt(i);

            }
        }
        for (char letter : word.toCharArray()) {
            int index = keywordAlphabet.indexOf(letter);
            if (index + shift >= keywordAlphabet.length()) {
                index = (index + shift) - keywordAlphabet.length();
                encryptedWord += keywordAlphabet.charAt(index);
            } else {
                encryptedWord += keywordAlphabet.charAt(index + shift);
            }

        }
        return encryptedWord;
    }

    /**
     * Decrypts a string using a keyword as a cypher.
     *
     * @param word the word to encrypt
     * @param shift the shift of characters
     * @param keyword the keyword
     * @return the decrypted string
     */
    public static String decryptStringKeyword(String word, int shift, String keyword) {
        String decryptedWord = "";
        String keywordAlphabet = keyword;
        char[] chars = keywordAlphabet.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }
        keywordAlphabet = "";
        for (Character character : charSet) {
            keywordAlphabet += character;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            if (!keywordAlphabet.contains(String.valueOf(alphabet.charAt(i)))) {
                keywordAlphabet += alphabet.charAt(i);

            }
            if (keywordAlphabet.length() == alphabet.length()) {

            }
        }
        for (char letter : word.toCharArray()) {
            int index = keywordAlphabet.indexOf(letter);
            if (index - shift < 0) {

                index = (index - shift) + keywordAlphabet.length();
                decryptedWord += keywordAlphabet.charAt(index);
            } else {
                decryptedWord += keywordAlphabet.charAt(index - shift);
            }

        }
        return decryptedWord;
    }

    public static void main(String[] args) {
        Encrypter enc = new Encrypter();
        for (int i = 1; i < 20; i++) {
            System.out.println(Encrypter.encryptStringKeyword("aaaaáááééé", i, "asdasd"));
            System.out.println(Encrypter.decryptStringKeyword(Encrypter.encryptStringKeyword("aaaaáááééé", i, "asdasd"), i, "asdasd"));
        }

    }
}
