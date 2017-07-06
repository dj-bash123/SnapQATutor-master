package in.co.snapqa.clientapp0903.models;

/**
 * Created by dhananjay on 06-07-2017.
 */

public class RejectDealRequest {

    public String token;
    public String dealId;

    public RejectDealRequest() {
        this.token = token;
        this.dealId = dealId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
}
