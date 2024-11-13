package aoc2016.day4;

import java.util.*;

public class Room
{
    private String encryptedName;
    private int sectorId;
    private String checkSum;

    public Room(String roomName)
    {
        extractEncryptedName(roomName);
        extractSectorId(roomName);
        extractCheckSum(roomName);
    }

    private void extractEncryptedName(String roomName)
    {
        int lastDashIdx = roomName.lastIndexOf('-');
        if (lastDashIdx != -1)
        {
            encryptedName = roomName.substring(0, lastDashIdx);
        }
    }

    private void extractSectorId(String roomName)
    {
        int lastDashIdx = roomName.lastIndexOf('-');
        int openBracketIdx = roomName.lastIndexOf('[');
        if (lastDashIdx != -1 && openBracketIdx != -1)
        {
            sectorId = Integer.parseInt(roomName.substring(lastDashIdx + 1, openBracketIdx));
        }
    }

    private void extractCheckSum(String roomName)
    {
        int openBracketIdx = roomName.lastIndexOf('[');
        if (openBracketIdx != -1)
        {
            checkSum = roomName.substring(openBracketIdx + 1, roomName.length() - 1);
        }
    }

    public String getEncryptedName() {
        return encryptedName;
    }

    public int getSectorId() {
        return sectorId;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public Map<Character, Integer> getEncryptedNameLetterCount()
    {
        Map<Character, Integer> encryptedNameLetterCount = new HashMap<>();

        for (int i = 0; i < encryptedName.length(); i++)
        {
            if (encryptedNameLetterCount.containsKey(encryptedName.charAt(i)))
            {
                encryptedNameLetterCount.put(encryptedName.charAt(i), encryptedNameLetterCount.get(encryptedName.charAt(i)) + 1);
            }
            else
            {
                encryptedNameLetterCount.put(encryptedName.charAt(i), 1);
            }
        }

        encryptedNameLetterCount.remove('-');

        return encryptedNameLetterCount;
    }

    public String getTop5MostCommonLetters()
    {
        TreeSet<LetterCount> treeSet = new TreeSet<>(new LetterCountComparator());
        Map<Character, Integer> encryptedNameLetterCount = getEncryptedNameLetterCount();
        treeSet.addAll(encryptedNameLetterCount.entrySet().stream().map(kv -> new LetterCount(kv.getKey(), kv.getValue())).toList());
        StringBuilder sb = new StringBuilder();
        if (treeSet.size() >= 5)
        {
            for (int i = 0; i < 5; i++)
            {
                LetterCount lc = treeSet.pollFirst();
                if (lc != null)
                {
                    sb.append(lc.letter);
                }
            }
        }
        return sb.toString();
    }

    public boolean isRealRoom()
    {
        return checkSum.equals(getTop5MostCommonLetters());
    }

    static class LetterCount
    {
        char letter;
        int count;

        LetterCount(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }
    }

    static class LetterCountComparator implements Comparator<LetterCount>
    {
         @Override
         public int compare(LetterCount o1, LetterCount o2) {
            if (o1.count > o2.count)
            {
                return -1;
            }
            else if (o1.count < o2.count)
            {
                return 1;
            }
            else
            {
                if (o1.letter < o2.letter)
                {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
    }
}
