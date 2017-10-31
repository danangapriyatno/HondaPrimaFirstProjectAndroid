package Customer_Term;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PRIMA on 10/24/2017.
 */

public class Customer_TermGetSet {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("nik")
    private String nik;
    @SerializedName("name")
    private String name;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("customer_id")
    private String customer_id;
    @SerializedName("city")
    private String city;

    public Customer_TermGetSet(String id, String nik, String name, String telephone, String customer_id, String city) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.telephone = telephone;
        this.customer_id = customer_id;
        this.city = city;
    }
}
