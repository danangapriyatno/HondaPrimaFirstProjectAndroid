package Presales;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PRIMA on 10/20/2017.
 */

public class Presale {


    public Presale(String id, String payment, String otr, String down_payment, String discount, String leasing, String installment, String tenor, String program, int presales_no, int vehicle_id, int customer_id, String status) {
        this.id = id;
        this.payment = payment;
        this.otr = otr;
        this.down_payment = down_payment;
        this.discount = discount;
        this.leasing = leasing;
        this.installment = installment;
        this.tenor = tenor;
        this.program = program;
        this.presales_no = presales_no;
        this.vehicle_id = vehicle_id;
        this.customer_id = customer_id;
        this.status = status;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("payment")
    private String payment;
    @SerializedName("otr")
    private String otr;
    @SerializedName("down_payment")
    private String down_payment;
    @SerializedName("discount")
    private String discount;
    @SerializedName("leasing")
    private String leasing;
    @SerializedName("installment")
    private String installment;
    @SerializedName("tenor")
    private String tenor;
    @SerializedName("program")
    private String program;
    @SerializedName("presales_no")
    private int presales_no;
    @SerializedName("vehicle_id")
    private int vehicle_id;
    @SerializedName("customer_id")
    private int customer_id;
    @SerializedName("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOtr() {
        return otr;
    }

    public void setOtr(String otr) {
        this.otr = otr;
    }

    public String getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(String down_payment) {
        this.down_payment = down_payment;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLeasing() {
        return leasing;
    }

    public void setLeasing(String leasing) {
        this.leasing = leasing;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getPresales_no() {
        return presales_no;
    }

    public void setPresales_no(int presales_no) {
        this.presales_no = presales_no;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
