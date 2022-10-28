package necesjv.common;

public class ApiCall {
    public ApiCall() {
        this.error = false;
        this.data = "";
    }

    Boolean error;

    String data;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
