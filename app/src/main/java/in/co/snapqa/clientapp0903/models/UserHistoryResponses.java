package in.co.snapqa.clientapp0903.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhananjay on 12-06-2017.
 */

public class UserHistoryResponses {

    @SerializedName("response")
    @Expose
    List<UserHistoryFragmentResponse> response;

    public UserHistoryResponses(List<UserHistoryFragmentResponse> response) {
        this.response = response;
    }

    public List<UserHistoryFragmentResponse> getResponse() {
        return response;
    }

    public void setResponse(List<UserHistoryFragmentResponse> response) {
        this.response = response;
    }
}
