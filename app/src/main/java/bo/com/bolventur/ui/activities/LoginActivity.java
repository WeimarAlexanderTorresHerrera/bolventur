package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.users.UserProfile;
import bo.com.bolventur.model.users.HostUser;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.viewModel.LoginViewModel;

import static bo.com.bolventur.model.users.UserProfile.HOST;
import static bo.com.bolventur.model.users.UserProfile.REGULAR;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private LoginViewModel loginViewModel;

    private EditText emailEditText;
    private EditText passwordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // TODO delete before merge to develop
        emailEditText.setText("final@gmail.com");
        passwordEditText.setText("123456");
    }

    public void loginEmailPassword(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginViewModel.loginEmailPassword(email, password).observe(this, userBase -> {
            if (userBase.isSuccessful()) {
                Log.e(LOG, "User " + new Gson().toJson(userBase.getData()));

                if (userBase.getData().getUserProfile().equals(REGULAR)) {
                    Intent intent = new Intent(context, MainMenuTabActivity.class);
                    intent.putExtra(Constants.KEY_USER, new Gson().toJson(userBase.getData()));
                    startActivity(intent);

                }
                if (userBase.getData().getUserProfile().equals(HOST)) {
                    Intent intent = new Intent(context, HostMenuActivity.class);
                    intent.putExtra(Constants.KEY_USER, new Gson().toJson(userBase.getData()));
                    startActivity(intent);
                }

            } else {
                Toast.makeText(context, ErrorMapper.getError(context, userBase.getErrorCode()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
      Intent intent = new Intent(context, RegisterActivity.class);
      startActivity(intent);
    }
}