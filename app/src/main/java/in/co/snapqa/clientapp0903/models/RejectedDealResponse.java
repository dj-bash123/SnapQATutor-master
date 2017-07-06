package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhananjay on 06-07-2017.
 */

public class RejectedDealResponse {

    @SerializedName("message")
    @Expose
    public String message;

    public RejectedDealResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
