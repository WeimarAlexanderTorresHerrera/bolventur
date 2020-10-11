package bo.com.bolventur.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean isEmpty(String text) {
        return text.isEmpty();
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
        return pattern.matcher(email).matches();
    }
}
