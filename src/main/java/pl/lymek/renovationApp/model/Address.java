package pl.lymek.renovationApp.model;

public class Address {

    private long id;
    private String street;
    private String town;
    private String ZipCode;
    private int StreetNumber;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public int getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        StreetNumber = streetNumber;
    }
}
