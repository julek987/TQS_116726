package geocoding;

import java.util.Objects;

public class Address {
    private final String road;
    private final String state;
    private final String city;
    private final String zip;
    private final String houseNumber;

    public Address(String road, String state, String city, String zip, String houseNumber) {
        this.road = road;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return String.format("Address{road=%s,state=%s,city=%s,zip=%s,houseNumber=%s}", road, state, city, zip, houseNumber);
    }

    @Override
    public int hashCode() {
        return 101;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final Address other = (Address) obj;

        if (!Objects.equals(this.road, other.road)) return false;
        if (!Objects.equals(this.city, other.city)) return false;
        if (!Objects.equals(this.state, other.state)) return false;
        if (!Objects.equals(this.zip, other.zip)) return false;

        return Objects.equals(this.houseNumber, other.houseNumber);
    }



}
