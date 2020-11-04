package bo.com.bolventur.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.CompressImage;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.AddEventViewModel;
import bo.com.bolventur.viewModel.HostMenuViewModel;

public class AddEventActivity extends AppCompatActivity implements SlideDatePickerDialogCallback{
    private Context context;
    private static final String LOG = AddEventActivity.class.getName();

    private String uidHost;

    private TextInputLayout eventTitleTextInputLayout;
    private TextInputEditText nameOfEventTextInputEditText;
    private TextView showDateTextView;
    private Button dateAndScheduleButton;
    private TextInputEditText locationTextInputEditText;
    private TextInputEditText priceTextInputEditText;
    private TextInputEditText placeTextInputEditText;
    private TextInputEditText descriptionTextInputEditText;
    private Button btnPhoto;
    private CheckBox culturalEventsCheckBox;
    private CheckBox musicalEventsCheckBox;
    private CheckBox tourismCheckBox;
    private Button cancelButton;
    private Button saveButton;

    private static final int RC_PERMISSIONS = 201;
    private static final int RC_GALLERY = 202;

    private Uri fileCoverPhoto;

    private AddEventViewModel viewModel;
    Event event = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        context = this;
        viewModel = new ViewModelProvider(this).get(AddEventViewModel.class);

        getSupportActionBar().hide();
        initViews();
        initEvents();
        getIntentValues();
    }



    private void initViews() {
        showDateTextView = findViewById(R.id.showDateTextView);
        placeTextInputEditText = findViewById(R.id.placeTextInputEditText);
        eventTitleTextInputLayout = findViewById(R.id.eventTitleTextInputLayout);
        nameOfEventTextInputEditText = findViewById(R.id.nameOfEventTextInputEditText);
        dateAndScheduleButton = findViewById(R.id.dateAndScheduleButton);
        locationTextInputEditText = findViewById(R.id.locationTextInputEditText);
        priceTextInputEditText = findViewById(R.id.priceTextInputEditText);
        descriptionTextInputEditText = findViewById(R.id.descriptionTextInputEditText);
        btnPhoto = findViewById(R.id.btnPhoto);
        culturalEventsCheckBox=findViewById(R.id.culturalEventsCheckBox);
        musicalEventsCheckBox =findViewById(R.id.musicalEventsCheckBox);
        tourismCheckBox= findViewById(R.id.tourismCheckBox);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

    }


    private void initEvents() {
        saveButton.setOnClickListener(view -> {

            Map<String, String> map = new HashMap<String, String>();
            map.put("Price", priceTextInputEditText.getText().toString());
            map.put("places", "value2");
            event.setTitle(nameOfEventTextInputEditText.getText().toString());
            event.setLocation(locationTextInputEditText.getText().toString());
            event.setTicket(map);

            event.setDescription(descriptionTextInputEditText.getText().toString());
            if(culturalEventsCheckBox.isChecked()||!musicalEventsCheckBox.isChecked()||!tourismCheckBox.isChecked()){
                event.setCategory(0);
            }else if (!culturalEventsCheckBox.isChecked()|| musicalEventsCheckBox.isChecked()||!tourismCheckBox.isChecked()){
                event.setCategory(1);
            }else{
                event.setCategory(2);
            }

            if (!event.getTitle().isEmpty() && !event.getDescription().isEmpty() &&
                    !event.getLocation().isEmpty())  {

                viewModel.createEvent(uidHost,event,fileCoverPhoto).observe(this, new Observer<Base<String>>() {
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

        dateAndScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, 2030);
                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(endDate);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(),"Dialog");
            }
        });

        btnPhoto.setOnClickListener(view ->{
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (hasPermissions()) {
                    openGallery();
                } else {
                    askPermissions();
                }
            }else {
                openGallery();
            }
        });
    }

    private void getIntentValues(){
        Intent intent =getIntent();
        if (intent.hasExtra(Constants.KEY_EVENT_UID_LOGIN)){
            uidHost = intent.getStringExtra(Constants.KEY_EVENT_UID_LOGIN);
        }
    }

    private boolean hasPermissions(){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermissions(){
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RC_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    Toast.makeText(context, "Sin permisos", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.post_image)), RC_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_GALLERY && data != null && data.getData() != null) {
                Uri image = data.getData();
                fileCoverPhoto = CompressImage.compressImage(image, context);
            }
        }
    }


    @Override
    public void onPositiveClick(int i, int i1, int i2, @NotNull Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        long mil = calendar.getTime().getTime();
        event.setDate(mil);
        showDateTextView.setText(format.format(calendar.getTime()));
    }
}
