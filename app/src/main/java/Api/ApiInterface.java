package Api;

import Customer.CustomerGetSet;
import Customer.CustomerReponse;
import Customer_Term.Customer_TermGetSet;
import ImageUpload.ImageGetSet;
import ImageUpload.ImageGetSetSecond;
import PembuatanToken.Login;
import PembuatanToken.User;
import Presales.Presale;
import Presales.PresaleResponse;
import Vehicles.VehiclesGetSet;
import Vehicles.VehiclesReponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //======================================TOKEN=============================================================//
    @POST("authenticate")
    Call<User> login(@Body Login login);

    @GET("authenticate")
    Call<ResponseBody> getToken(@Header("Authorization")String AuthToken);

    //insert data user
    @POST("authenticate")
    Call<Login> register(@Query("token") String apiKey,@Field("email") String email, @Field("password") String password );
    //=========================================Presale=======================================================//
    //get data presale
    @GET("presale")
    Call<PresaleResponse> getPresaleData(@Query("token") String apiKey);
    //insert data
    @POST("presale")
    Call<Presale> InsertPresale(@Body Presale presale, @Query("token") String apiKey);

    //==========================================CUSTOMER======================================================//
    //insert data
    @POST("customer")
    Call<CustomerGetSet> InsertCustomer(@Body CustomerGetSet customerGetSet, @Query("token") String apiKey);

    //get data customer
    @GET("customer")
    Call<CustomerReponse> GetCustomerData(@Query("token") String apiKey);

    //=========================================CUSTOMER_TERM=================================================//
    //insert data
    @POST("customerterm")
    Call<Customer_TermGetSet> InsertCustomer_Term(@Body Customer_TermGetSet customer_termGetSet, @Query("token") String apiKey);


    //===========================================VEHICLE=====================================================//
    @GET("vehicle")
    Call<VehiclesReponse> GetVehicle(@Query("token") String apiKey);
    //Update Data
    @FormUrlEncoded
    @PUT("vehicle/{id}")
    Call<VehiclesGetSet> Updatedatavehicle(@Path("id") int id, @Field("code_stock") String code_stock,@Field("name") String name,@Field("colour") String colour,@Field("frame_no") String frame_no,@Field("stock") String stock,@Field("machine_no") String machine_no,@Field("customer_id") String customer_id,@Field("vehicles_type_id") String vehicles_type_id,@Query("token") String apiKey);

    //=========================================UploadImageKelengkapan=========================================//

    @Multipart
    @POST("presaleupload")
    Call<ImageGetSet> uploadImageKelengkapan(@Part("filename") RequestBody filename, @Part MultipartBody.Part filefield, @Part("presale_no") RequestBody presale_no, @Query("token") String apiKey);


}

