package Vehicles;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import Customer.CustomerGetSet;

/**
 * Created by PRIMA on 10/24/2017.
 */

public class VehiclesReponse {
    public VehiclesReponse(String id, String code_stock, String name, String colour, String frame_no, String machine_no, String stock, String customer_id, String vehicles_type_id, List<VehiclesGetSet> data) {
        this.id = id;
        this.code_stock = code_stock;
        this.name = name;
        this.colour = colour;
        this.frame_no = frame_no;
        this.machine_no = machine_no;
        this.stock = stock;
        this.customer_id = customer_id;
        this.vehicles_type_id = vehicles_type_id;
        this.data = data;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("code_stock")
    private String code_stock;
    @SerializedName("name")
    private String name;
    @SerializedName("colour")
    private String colour;
    @SerializedName("frame_no")
    private String frame_no;
    @SerializedName("machine_no")
    private String machine_no;
    @SerializedName("stock")
    private String stock;
    @SerializedName("customer_id")
    private String customer_id;
    @SerializedName("vehicles_type_id")
    private String vehicles_type_id;
    @SerializedName("data")
    private List<VehiclesGetSet> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode_stock() {
        return code_stock;
    }

    public void setCode_stock(String code_stock) {
        this.code_stock = code_stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFrame_no() {
        return frame_no;
    }

    public void setFrame_no(String frame_no) {
        this.frame_no = frame_no;
    }

    public String getMachine_no() {
        return machine_no;
    }

    public void setMachine_no(String machine_no) {
        this.machine_no = machine_no;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getVehicles_type_id() {
        return vehicles_type_id;
    }

    public void setVehicles_type_id(String vehicles_type_id) {
        this.vehicles_type_id = vehicles_type_id;
    }

    public List<VehiclesGetSet> getData() {
        return data;
    }

    public void setData(List<VehiclesGetSet> data) {
        this.data = data;
    }
}
