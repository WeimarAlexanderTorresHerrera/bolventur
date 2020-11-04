package bo.com.bolventur.utils;

public class Constants {
    // Login
    public static final int ERROR_LOGIN = 2000;
    public static final int ERROR_EMPTY_LOGIN_VALUES = 2001;
    public static final int ERROR_EMPTY_EMAIL_VALUE = 2002;
    public static final int ERROR_EMPTY_PASSWORD_VALUE = 2003;
    public static final int ERROR_INVALID_EMAIL = 2004;

    public static final int ERROR_DONT_MATCH = 2005;
    public static final int ERROR_EMPTY_REGISTER_VALUES = 2006;

    public static final String KEY_USER = "user";
    public static final String KEY_EVENT_SELECTED = "event selected";

    //Server
    public static final int ERROR_SERVER = 3000;
    public static final int ERROR_NO_CONNECTION = 1001;

    //Api
    public static final String BASE_URL = "https://bolventur.herokuapp.com/api/";
    public static final String RESOURCE_EVENTS = "event";
    public static final String RESOURCE_FAVORITES = "favorite";

}
