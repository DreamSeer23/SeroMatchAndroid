package seromatch.seromatchtest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("recipient")
    @Expose
    private String recipient;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("body")
    @Expose
    private String body;

    public String getSender() {
        return sender;
    }

    public void setSender(String send) {
        sender = send;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String rec) {
        recipient = rec;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String sbj) {
        subject = sbj;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String bdy) {
        body = bdy;
    }
}
