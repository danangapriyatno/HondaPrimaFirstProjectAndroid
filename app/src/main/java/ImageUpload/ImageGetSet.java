package ImageUpload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PRIMA on 10/27/2017.
 */

public class ImageGetSet {

    public ImageGetSet(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPresale_no() {
        return presale_no;
    }

    public void setPresale_no(String presale_no) {
        this.presale_no = presale_no;
    }

    @SerializedName("filename")
    private String filename;
    @SerializedName("presale_no")
    private String presale_no;

    public ImageGetSet(String filename, String presale_no) {
        this.filename = filename;
        this.presale_no = presale_no;
    }
}
