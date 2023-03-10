package geocoding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {
    Address address = new Address(
            "890 Fifth Avenue",
            "New York",
            "NY",
            "10021",
            null);
    String addressString = String.format(
            "Address{road=%s,state=%s,city=%s,zip=%s,houseNumber=%s}",
            "890 Fifth Avenue",
            "New York",
            "NY",
            "10021",
            null);

    @Test
    void testToString() {
        Assertions.assertEquals(address.toString(), addressString);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(address.hashCode(), 101);
    }
    @Test
    void testEquals() {
        Address testAddress = new Address(
                "890 Fifth Avenue",
                "New York",
                "NY",
                "10021",
                null);

        Assertions.assertEquals(address, testAddress);
    }
}
