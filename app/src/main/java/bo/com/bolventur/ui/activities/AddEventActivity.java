package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.viewModel.AddEventViewModel;
import bo.com.bolventur.viewModel.HostMenuViewModel;

public class AddEventActivity extends AppCompatActivity {
    private Context context;
    private static final String LOG = AddEventActivity.class.getName();

    private String uidHost;

    private TextInputLayout eventTitleTextInputLayout;
    private TextInputEditText nameOfEventTextInputEditText;
    private TextInputEditText dateAndScheduleTextInputEditText;
    private TextInputEditText locationTextInputEditText;
    private TextInputEditText priceTextInputEditText;
    private TextInputEditText descriptionTextInputEditText;
    private Button btnPhoto;
    private Button cancelButton;
    private Button saveButton;

    private AddEventViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        context = this;
        viewModel = new ViewModelProvider(this).get(AddEventViewModel.class);

        getSupportActionBar().hide();
        initViews();
        initEvents();
    }



    private void initViews() {
        eventTitleTextInputLayout = findViewById(R.id.eventTitleTextInputLayout);
        nameOfEventTextInputEditText = findViewById(R.id.nameOfEventTextInputEditText);
        dateAndScheduleTextInputEditText = findViewById(R.id.dateAndScheduleTextInputEditText);
        locationTextInputEditText = findViewById(R.id.locationTextInputEditText);
        priceTextInputEditText = findViewById(R.id.priceTextInputEditText);
        descriptionTextInputEditText = findViewById(R.id.descriptionTextInputEditText);
        btnPhoto = findViewById(R.id.btnPhoto);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

    }

    private void initEvents() {
        saveButton.setOnClickListener(view -> {
            Event event = new Event();
            event.setTitle(nameOfEventTextInputEditText.getText().toString());
            event.setDate(dateAndScheduleTextInputEditText.getInputType());
            event.setLocation(locationTextInputEditText.getText().toString());
            //event.setTicket("Price",");
            event.setDescription(descriptionTextInputEditText.getText().toString());

            if (!event.getTitle().isEmpty() && !event.getDescription().isEmpty() &&
                    !event.getLocation().isEmpty()) {

                viewModel.createEvent(uidHost,event).observe(this, new Observer<Base<String>>() {
                    @Override
                    public void onChanged(Base<String> stringBase) {
                        if (stringBase.isSuccessful()){
                            Log.e(LOG, "createEvent.isSuccess:" + stringBase.getData());
                        }else{
                            Log.e(LOG, "createEvent.error", stringBase.getException());
                        }
                    }
                });
            } else {
                Toast.makeText(context, context.getString(R.string.error_empty_register_values),Toast.LENGTH_SHORT).show();
            }

        });
        cancelButton.setOnClickListener(view ->{
            finish();
        });

        btnPhoto.setOnClickListener(view ->{
            //Todo
        });
    }


}
