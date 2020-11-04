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

    public static final int ERROR_REGISTER = 2007;
    public static final int ERROR_REGISTER_EMAIL_ALREADY_EXISTS = 2008;
    public static final int ERROR_REGISTER_WEAK_PASSWORD = 2009;

    public static final String KEY_USER = "user";
    public static final String KEY_EVENT_SELECTED = "event selected";

    //Server
    public static final int ERROR_SERVER = 3000;
    public static final int ERROR_NO_CONNECTION = 1001;

    //Api
    public static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/bolvertur.appspot.com/o/";
    public static final String RESOURCE_EVENTS ="EventsCultural.json";
    public static final String QUERY_PARAM_ALT ="media";
    public static final String RESOURCE_EVENTS2 ="EventsFav.json";

    //Firebase
    public static final String FIREBASE_PATH_EVENT = "/events";
    public static final String KEY_EVENT_UID_LOGIN = "uidHost";

    public static final int ERROR_UPLOAD_IMAGE = 2006;

    public static final String DIRECTORY_IMAGE = "bolventur_image";

}
