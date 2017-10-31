package Customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import Presales.Presale;

/**
 * Created by PRIMA on 10/23/2017.
 */

public class CustomerReponse {


    public CustomerReponse(String id, String nik, String name, String telephone, String city, String type_id, List<CustomerGetSet> data) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.telephone = telephone;
        this.city = city;
        this.type_id = type_id;
        this.data = data;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("nik")
    private String nik;
    @SerializedName("name")
    private String name;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("city")
    private String city;
    @SerializedName("type_id")
    private String type_id;
    @SerializedName("data")
    private List<CustomerGetSet> data;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public List<CustomerGetSet> getData() {
        return data;
    }

    public void setData(List<CustomerGetSet> data) {
        this.data = data;
    }
}
