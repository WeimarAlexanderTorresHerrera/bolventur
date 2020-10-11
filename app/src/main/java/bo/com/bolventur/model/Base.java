package bo.com.bolventur.model;

public class Base<T> {
    private boolean isSuccessful;
    private int errorCode;
    private Exception exception;
    private T data;

    public Base(T data) {
        this.isSuccessful = true;
        this.data = data;

        this.errorCode = 0000;
        this.exception = null;
    }

    public Base(int errorCode, Exception exception) {
        this.isSuccessful = false;
        this.data = null;

        this.errorCode = errorCode;
        this.exception = exception;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Exception getException() {
        return exception;
    }

    public T getData() {
        return data;
    }
}
