package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import bo.com.bolventur.R;
import bo.com.bolventur.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        getSupportActionBar().hide();
        initViews();
        initEvents();
    }

    private void initViews() {

    }

    private void initEvents() {

    }
}