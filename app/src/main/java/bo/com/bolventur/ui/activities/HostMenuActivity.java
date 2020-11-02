package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import bo.com.bolventur.R;
import bo.com.bolventur.viewModel.HostMenuViewModel;
import bo.com.bolventur.viewModel.RegisterViewModel;

public class HostMenuActivity extends AppCompatActivity {
    private Context context;
    private static final String LOG = HostMenuActivity.class.getName();


    private HostMenuViewModel hostMenuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_menu);

        context = this;
        hostMenuViewModel = new ViewModelProvider(this).get(HostMenuViewModel.class);

        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {

    }

    public void addEvent(View view) {
        Intent intent = new Intent(context, AddEventActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }
}
