package in.neuw.self;

public class Pong {
    private String message;
    private boolean success;
    private Long timestamp;
    private boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Pong() {

    }

    public Pong(String message, boolean success, Long timestamp, boolean error) {
        this.message = message;
        this.success = success;
        this.timestamp = timestamp;
        this.error = error;
    }
}