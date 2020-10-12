package bo.com.bolventur.utils;

import android.content.Context;

import bo.com.bolventur.R;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        if (errorCode == Constants.ERROR_EMPTY_LOGIN_VALUES) {
            return context.getString(R.string.error_empty_login_values);
        } else if (errorCode == Constants.ERROR_EMPTY_EMAIL_VALUE) {
            return context.getString(R.string.error_empty_email_value);
        } else if (errorCode == Constants.ERROR_EMPTY_PASSWORD_VALUE) {
            return context.getString(R.string.error_empty_password_value);
        } else if (errorCode == Constants.ERROR_INVALID_EMAIL) {
            return context.getString(R.string.error_invalid_email);
        } else if (errorCode == Constants.ERROR_LOGIN) {
            return context.getString(R.string.error_login);
        } else {
            return context.getString(R.string.error_unknown);
        }
    }
}
