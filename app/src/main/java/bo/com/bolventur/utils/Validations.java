package bo.com.bolventur.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean isEmpty(String text) {
        return text.isEmpty();
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        return pattern.matcher(email).matches();
    }
}
