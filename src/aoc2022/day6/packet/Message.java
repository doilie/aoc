package aoc2022.day6.packet;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private static final int StartOfPacketSize = 4;
    private static final int StartOfMessageSize = 14;
    private final String receivedMessage;

    public Message(String receivedMessage) {
        this.receivedMessage = receivedMessage;
    }

    public int getFirstStartOfPacketMarker() {
        return getFirstMarker(StartOfPacketSize);
    }

    public int getFirstStartOfMessageMarker() {
        return getFirstMarker(StartOfMessageSize);
    }

    private int getFirstMarker(int parseSize) {
        for (int i = 0; i < receivedMessage.length() - parseSize; i++) {
            String messagePart = receivedMessage.substring(i, i + parseSize);
            List<Character> messageChars = new ArrayList<>();
            for (int j = 0; j < messagePart.length(); j++) {
                if (!messageChars.contains(messagePart.charAt(j))) {
                    messageChars.add(messagePart.charAt(j));
                }
                else {
                    break;
                }
            }
            if (messageChars.size() == parseSize) {
                return i + parseSize;
            }
        }
        return 0;
    }
}
