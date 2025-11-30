package aoc2016.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IPv7AddressTest
{
    private static Stream<Arguments> provideAddressForTLS()
    {
        return Stream.of(
                Arguments.of("abba[mnop]qrst", List.of("abba", "qrst"), List.of("mnop"), true),
                Arguments.of("abcd[bddb]xyyx", List.of("abcd", "xyyx"), List.of("bddb"), false),
                Arguments.of("aaaa[qwer]tyui", List.of("aaaa", "tyui"), List.of("qwer"), false),
                Arguments.of("ioxxoj[asdfgh]zxcvbn", List.of("ioxxoj", "zxcvbn"), List.of("asdfgh"), true),
                Arguments.of("ioxxoj[asdfgh]zxcvbn[abxoox]abcd", List.of("ioxxoj", "zxcvbn"), List.of("asdfgh"), false)
        );
    }

    private static Stream<Arguments> provideAddressForSSL()
    {
        return Stream.of(
                Arguments.of("aba[bab]xyz", true),
                Arguments.of("xyx[xyx]xyx", false),
                Arguments.of("aaa[kek]eke", true),
                Arguments.of("zazbz[bzb]cdb", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAddressForTLS")
    void testIsTLS(String input, List<String> stringsOutsideBrackets, List<String> stringsInsideBrackets, boolean expectedTls)
    {
        IPv7Address address = new IPv7Address(input);
        for (String stringOutsideBrackets : stringsOutsideBrackets) {
            assertTrue(address.getStringsOutsideBrackets().contains(stringOutsideBrackets));
        }
        for (String stringInsideBrackets : stringsInsideBrackets) {
            assertTrue(address.getStringsInsideBrackets().contains(stringInsideBrackets));
        }
        assertEquals(expectedTls, address.isTLS());
    }


    @ParameterizedTest
    @MethodSource("provideAddressForSSL")
    void testIsSSL(String input, boolean expectedTls)
    {
        IPv7Address address = new IPv7Address(input);
        assertEquals(expectedTls, address.isSSL());
    }
}
