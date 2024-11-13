package aoc2016.day4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void getEncryptedName()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals("aaaaa-bbb-z-y-x", room.getEncryptedName());
    }

    @Test
    void getSectorId()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals(123, room.getSectorId());
    }

    @Test
    void getCheckSum()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals("abxyz", room.getCheckSum());
    }

    @Test
    void getEncryptedNameLetterCount()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        Map<Character, Integer> encryptedNameLetterCount = room.getEncryptedNameLetterCount();
        assertEquals(5, encryptedNameLetterCount.get('a'));
        assertEquals(3, encryptedNameLetterCount.get('b'));
        assertEquals(1, encryptedNameLetterCount.get('x'));
        assertEquals(1, encryptedNameLetterCount.get('y'));
        assertEquals(1, encryptedNameLetterCount.get('z'));
        assertNull(encryptedNameLetterCount.get('f'));
    }

    @Test
    void getTop5MostCommonLetters_abxyz()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals("abxyz", room.getTop5MostCommonLetters());
    }

    @Test
    void getTop5MostCommonLetters_abcde()
    {
        Room room = new Room("a-b-c-d-e-f-g-h-987[abcde]");
        assertEquals("abcde", room.getTop5MostCommonLetters());
    }

    @Test
    void getTop5MostCommonLetters_oarel()
    {
        Room room = new Room("not-a-real-room-404[oarel]");
        assertEquals("oarel", room.getTop5MostCommonLetters());
    }

    @Test
    void getTop5MostCommonLetters_decoy()
    {
        Room room = new Room("totally-real-room-200[decoy]");
        assertEquals("loart", room.getTop5MostCommonLetters());
    }

    @Test
    void isRealRoom_abxyz()
    {
        Room room = new Room("aaaaa-bbb-z-y-x-123[abxyz]");
        assertTrue(room.isRealRoom());
    }

    @Test
    void isRealRoom_decoy()
    {
        Room room = new Room("totally-real-room-200[decoy]");
        assertFalse(room.isRealRoom());
    }

    @Test
    void checkWithPuzzleSample()
    {
        List<Room> puzzleSampleList = List.of(
                new Room("aaaaa-bbb-z-y-x-123[abxyz]"),
                new Room("a-b-c-d-e-f-g-h-987[abcde]"),
                new Room("not-a-real-room-404[oarel]"),
                new Room("totally-real-room-200[decoy]")
        );
        assertEquals(1514, puzzleSampleList.stream().filter(Room::isRealRoom).map(Room::getSectorId).mapToInt(Integer::intValue).sum());
    }
}