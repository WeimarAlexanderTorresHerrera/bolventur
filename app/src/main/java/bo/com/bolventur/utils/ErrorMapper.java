package bo.com.bolventur.utils;

import android.content.Context;

import bo.com.bolventur.R;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        if (errorCode == Constants.ERROR_EMPTY_VALUES) {
            return context.getString(R.string.error_empty_values);
        } else {
            return context.getString(R.string.error_unknown);
        }
    }
}
