package model;

public class AsyncRequestStatus {

    private String requestId;
    private RequestState state;
    private String message;
    private Long createdBookId; // optional, doar pt POST /books

    public AsyncRequestStatus() {
    }

    public AsyncRequestStatus(String requestId, RequestState state, String message) {
        this.requestId = requestId;
        this.state = state;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public RequestState getState() {
        return state;
    }

    public void setState(RequestState state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreatedBookId() {
        return createdBookId;
    }

    public void setCreatedBookId(Long createdBookId) {
        this.createdBookId = createdBookId;
    }
}
