package pl.lymek.renovationApp.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=2, message = "NAZWA ULICY MUSI POSIADAĆ CONAJMNIEJ 2 ZNAKI")
    private String street;

    @Size(min=2,message = "NAZWA MIASTA MUSI POSIADAĆ MINIMUM 2 ZNAKI")
    private String town;

    @Pattern(regexp = "[0-9]{2}-[0-9]{3}",message = "WPISZ POPRAWNIE KOD POCZTOWY")
    private String zipCode;

    @Size (min=1,message = "NUMER ULICY MUSI MIEĆ JAKĄŚ WARTOŚC")
    private String streetNumber;


//-----------------------------------------------------------------------------------------------------

    public Address() {

    }

    public Address(@Size(min = 2, message = "NAZWA ULICY MUSI POSIADAĆ CONAJMNIEJ 2 ZNAKI")
                           String street, @Size(min = 2, message = "NAZWA MIASTA MUSI POSIADAĆ MINIMUM 2 ZNAKI")
            String town, @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "WPISZ POPRAWNIE KOD POCZTOWY") String zipCode,
                   @Size(min = 1, message = "NUMER ULICY MUSI MIEĆ JAKĄŚ WARTOŚC") String streetNumber) {
        this.street = street;
        this.town = town;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }

    public Address(long id, @Size(min = 2, message = "NAZWA ULICY MUSI POSIADAĆ CONAJMNIEJ 2 ZNAKI") String street,
                   @Size(min = 2, message = "NAZWA MIASTA MUSI POSIADAĆ MINIMUM 2 ZNAKI") String town,
                   @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "WPISZ POPRAWNIE KOD POCZTOWY") String zipCode,
                   @Size(min = 1, message = "NUMER ULICY MUSI MIEĆ JAKĄŚ WARTOŚC") String streetNumber) {
        this.id = id;
        this.street = street;
        this.town = town;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }

    //-------------------------------------------------------------------------------------------------------


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
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

}
