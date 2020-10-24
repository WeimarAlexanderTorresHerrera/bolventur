package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.viewModel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private Context context;
    private static final String LOG = RegisterActivity.class.getName();

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confPasswordEditText;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confPasswordEditText = findViewById(R.id.confPasswordEditText);
    }

    public void register(View view){
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPsswd = confPasswordEditText.getText().toString();

        registerViewModel.register(email, password, username, confirmPsswd).observe(this, new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccessful()) {
                    Log.e(LOG, "User " + new Gson().toJson(userBase.getData()));

                    Intent intent = new Intent(context, MainMenuTabActivity.class);
                    intent.putExtra(Constants.KEY_USER, new Gson().toJson(userBase.getData()));
                    startActivity(intent);
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, userBase.getErrorCode()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}