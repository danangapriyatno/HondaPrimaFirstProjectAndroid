package ImageUpload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PRIMA on 10/27/2017.
 */

public class ImageGetSetSecond {
    public ImageGetSetSecond(String presale_no) {
        this.presale_no = presale_no;
    }

    public String getPresale_no() {
        return presale_no;
    }

    public void setPresale_no(String presale_no) {
        this.presale_no = presale_no;
    }

    @SerializedName("presale_no")
    private String presale_no;
}
