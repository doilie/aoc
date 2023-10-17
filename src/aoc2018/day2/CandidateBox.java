package aoc2018.day2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CandidateBox {
    private final Hashtable<String, List<Short>> letterIndices = new Hashtable<>();
    private short totalLetters;

    public CandidateBox(String input) {
        String[] inputLetters = input.split("");
        for (short i = 0; i < inputLetters.length; i++) {
            String inputLetter = inputLetters[i];
            List<Short> letterIndexList = letterIndices.get(inputLetter);
            if (letterIndexList == null) {
                letterIndexList = new ArrayList<>();
                letterIndexList.add(i);
                letterIndices.put(inputLetter, letterIndexList);
            }
            else {
                letterIndexList.add(i);
            }
            totalLetters++;
        }
    }

    public boolean containsLetterCount(short letterCount) {
        List<List<Short>> letterIndexLists = new ArrayList<>(letterIndices.values());
        for (List<Short> letterIndexList : letterIndexLists) {
            if (letterIndexList.size() == letterCount) {
                return true;
            }
        }
        return false;
    }

    public String getCommonLetters(CandidateBox boxToCompare) {
        Set<String> lettersToCompare = letterIndices.keySet();
        List<String> commonLetters = IntStream.range(0, totalLetters).mapToObj(i -> "").collect(Collectors.toList());
        for (String letterToCompare : lettersToCompare) {
            if (boxToCompare.letterIndices.get(letterToCompare) != null) {
                List<Short> letterIndexList = letterIndices.get(letterToCompare);
                List<Short> toCompareLetterIndexList = boxToCompare.letterIndices.get(letterToCompare);
                for (Short idx : letterIndexList) {
                    if (toCompareLetterIndexList.contains(idx)) {
                        commonLetters.set(idx, letterToCompare);
                    }
                }
            }
        }
        return String.join("", commonLetters).trim();
    }

    public short getTotalLetters() {
        return totalLetters;
    }
}
