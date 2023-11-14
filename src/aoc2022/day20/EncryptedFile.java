package aoc2022.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EncryptedFile {
    public static class NumberWrapper {
        long value;

        NumberWrapper(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Long.toString(value);
        }
    }

    private final List<NumberWrapper> encryptedFile = new ArrayList<>();

    public static EncryptedFile buildEncryptedFile(String[] lines, int multiplier) {
        EncryptedFile encryptedFile = new EncryptedFile();
        for (String line : lines) {
            encryptedFile.addToFile(line, multiplier);
        }
        return encryptedFile;
    }

    public List<NumberWrapper> mixFile() {
        return mixFile(new ArrayList<>(encryptedFile));
    }

    public List<NumberWrapper> mixFile(List<NumberWrapper> mixedFile) {
        for (NumberWrapper stepsToMoveWrapper : encryptedFile) {
            int currIdx = mixedFile.indexOf(stepsToMoveWrapper);
            long stepsToMove = stepsToMoveWrapper.value;
            int newIdx = getNewIndex(currIdx, stepsToMove);
            if (newIdx != currIdx) {
                mixedFile = reorderList(mixedFile, currIdx, newIdx);
            }
        }
        return mixedFile;
    }

    public long findNumberAtIndexFromZero(List<NumberWrapper> mixedFile, int index) {
        int indexOfZero = IntStream.range(0, mixedFile.size()).filter(i -> mixedFile.get(i).value == 0).findFirst().orElse(-1);
        if (indexOfZero != -1) {
            int indexToGet = (indexOfZero + index) % mixedFile.size();
            return mixedFile.get(indexToGet).value;
        }
        return -1;
    }

    private void addToFile(String numberAsString, long multiplier) {
        NumberWrapper numberAsObject = new NumberWrapper((long) Integer.parseInt(numberAsString) * multiplier);
        encryptedFile.add(numberAsObject);
    }

    private int getNewIndex(int currIdx, long stepsToMove) {
        if (stepsToMove == 0) {
            return currIdx;
        }
        int listSize = encryptedFile.size();
        stepsToMove = stepsToMove % (listSize - 1);
        long newIdx = (currIdx + stepsToMove) % (listSize - 1);
        if (newIdx <= 0) {
            newIdx = listSize - 1 + newIdx;
        }
        return (int) newIdx;
    }

    private List<NumberWrapper> reorderList(List<NumberWrapper> mixedFile, int currIdx, int newIdx) {
        List<NumberWrapper> listPart1 = mixedFile.subList(0, currIdx);
        List<NumberWrapper> listPart2 = mixedFile.subList(currIdx + 1, mixedFile.size());
        NumberWrapper currNum = mixedFile.get(currIdx);
        List<NumberWrapper> tempList = new ArrayList<>();
        tempList.addAll(listPart1);
        tempList.addAll(listPart2);
        listPart1 = tempList.subList(0, newIdx);
        listPart2 = tempList.subList(newIdx, tempList.size());
        List<NumberWrapper> newList = new ArrayList<>(listPart1);
        newList.add(currNum);
        newList.addAll(listPart2);
        return newList;
    }

}
