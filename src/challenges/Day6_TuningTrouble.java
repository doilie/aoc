package challenges;

import impl.packet.Message;

public class Day6_TuningTrouble extends Challenge {
    public static void main(String[] args)  {
        Day6_TuningTrouble day6 = new Day6_TuningTrouble();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    private Message message;
    public Day6_TuningTrouble() {
        super("day6-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile() {
        message = new Message(this.getFileContents());
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Number of characters before first start-of-packet: " + message.getFirstStartOfPacketMarker());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of characters before first start-of-message: " + message.getFirstStartOfMessageMarker());
    }
}
