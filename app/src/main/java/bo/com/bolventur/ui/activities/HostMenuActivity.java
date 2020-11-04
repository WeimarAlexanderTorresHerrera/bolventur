package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.users.HostUser;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.HostMenuViewModel;
import bo.com.bolventur.viewModel.RegisterViewModel;

public class HostMenuActivity extends AppCompatActivity {
    private Context context;
    private static final String LOG = HostMenuActivity.class.getName();


    private HostMenuViewModel hostMenuViewModel;

    private User uidHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_menu);

        context = this;
        hostMenuViewModel = new ViewModelProvider(this).get(HostMenuViewModel.class);

        getSupportActionBar().hide();
        initViews();
        getIntentValues();
    }

    private void initViews() {

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_USER)) {
            try {
                String json = intent.getStringExtra(Constants.KEY_USER);
                User user = new Gson().fromJson(json, User.class);
                uidHost = user;
            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }

    public void addEvent(View view) {
        Intent intent = new Intent(context, AddEventActivity.class);
        intent.putExtra(Constants.KEY_EVENT_UID_LOGIN, uidHost.getUid());
        startActivity(intent);
    }

    public void profile(View view) {
        finish();
        /* Esto ya no lo utilizaremos
        Intent intent = new Intent(context, HostEventActivity.class);
        intent.putExtra(Constants.KEY_EVENT_UID_LOGIN, uidHost.getUid());
        startActivity(intent);*/
    }
}
