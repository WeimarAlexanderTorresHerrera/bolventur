package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import bo.com.bolventur.R;
import bo.com.bolventur.viewModel.AddEventViewModel;
import bo.com.bolventur.viewModel.HostMenuViewModel;

public class AddEventActivity extends AppCompatActivity {
    private Context context;
    private static final String LOG = AddEventActivity.class.getName();


    private AddEventViewModel addEventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        context = this;
        addEventViewModel = new ViewModelProvider(this).get(AddEventViewModel.class);

        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {

    }
}
