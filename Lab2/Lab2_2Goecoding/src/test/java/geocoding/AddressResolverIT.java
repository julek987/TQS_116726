package geocoding;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddressResolverIT {
    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        this.resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = resolver.findAddressForLocation(40.633116, -8.658784);
        assertEquals(result, Optional.of(new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null)));
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        assertThrows(IllegalArgumentException.class, () -> {
            resolver.findAddressForLocation(-361, -361);
        });
    }
}