package aoc2017.day4;

import java.util.*;

public class Passphrase
{
    private final String passphrase;
    private final Set<String> passphraseWords = new HashSet<>();
    private final Set<String> passphraseWordsNoAnagrams = new HashSet<>();

    public Passphrase(String passphrase)
    {
        this.passphrase = passphrase;
        String[] passPhraseWordsArray = passphrase.split(" ");
        passphraseWords.addAll(Arrays.stream(passPhraseWordsArray).toList());
        passphraseWordsNoAnagrams.addAll(Arrays.stream(passPhraseWordsArray).map(Passphrase::alphabetizeWord).toList());
    }

    private int getPassphraseWordCount()
    {
        return passphrase.split(" ").length;
    }

    public boolean isValid()
    {
        return getPassphraseWordCount() == passphraseWords.size();
    }

    public boolean isValid_NoAnagram()
    {
        return getPassphraseWordCount() == passphraseWordsNoAnagrams.size();
    }

    public static String alphabetizeWord(String word)
    {
        List<String> letters = new ArrayList<>(Arrays.stream(word.split("")).toList());
        Collections.sort(letters);
        return String.join("", letters);
    }
}
