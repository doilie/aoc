package aoc2016.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day4_SecurityThroughObscurity extends Challenge {
    public static void main(String[] args) {
        Day4_SecurityThroughObscurity day4 = new Day4_SecurityThroughObscurity();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_SecurityThroughObscurity() {
        super("2016/day4-input.txt");
        this.parseFile();
    }

    private final List<Room> rooms = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            rooms.add(new Room(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Sum of sector id's of real rooms: " + rooms.stream().filter(Room::isRealRoom).map(Room::getSectorId).mapToInt(Integer::intValue).sum());
    }

    @Override
    public void doTwoStarSolution() {
        for (Room room : rooms)
        {
            String decryptedName = room.decryptName();
            if (decryptedName.equals("northpole object storage"))
            {
                System.out.println("Sector id of room where North Pole objects are stored: " + room.getSectorId());
            }
        }
    }
}
