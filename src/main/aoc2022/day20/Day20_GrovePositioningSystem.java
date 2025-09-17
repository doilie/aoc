package aoc2022.day20;

import lib.Challenge;

import java.util.List;

public class Day20_GrovePositioningSystem extends Challenge {
    public static void main(String[] args) {
        Day20_GrovePositioningSystem day20 = new Day20_GrovePositioningSystem();
        day20.doOneStarSolution();
        day20.doTwoStarSolution();
    }

    public Day20_GrovePositioningSystem() {
        super("2022/day20-input.txt");
        parseFile();
    }

    private String[] lines;

    @Override
    protected void parseFile() {
        lines = getFileContents().split("\\n");
    }

    @Override
    public void doOneStarSolution() {
        EncryptedFile encryptedFile = EncryptedFile.buildEncryptedFile(lines, 1);
        List<EncryptedFile.NumberWrapper> mixedFile = encryptedFile.mixFile();
        System.out.println("Grove coordinates sum: " + getGroveCoordinatesSum(encryptedFile, mixedFile));
    }

    @Override
    public void doTwoStarSolution() {
        EncryptedFile encryptedFile = EncryptedFile.buildEncryptedFile(lines, 811589153);
        List<EncryptedFile.NumberWrapper> mixedFile = encryptedFile.mixFile();
        for (int i = 0; i < 9; i++) {
            mixedFile = encryptedFile.mixFile(mixedFile);
        }
        System.out.println("Grove coordinates sum with decryption key: " + getGroveCoordinatesSum(encryptedFile, mixedFile));
    }

    private long getGroveCoordinatesSum(EncryptedFile encryptedFile, List<EncryptedFile.NumberWrapper> mixedFile) {
        return encryptedFile.findNumberAtIndexFromZero(mixedFile, 1000) +
                encryptedFile.findNumberAtIndexFromZero(mixedFile, 2000) +
                encryptedFile.findNumberAtIndexFromZero(mixedFile, 3000);
    }
}
