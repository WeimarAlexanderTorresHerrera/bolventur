package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.MainMenuTab1ViewModel;

public class EventActivity extends AppCompatActivity {

    private static final String LOG = EventActivity.class.getSimpleName();
    //private MainMenuTab1ViewModel viewModel;

    private ImageView coverImageView;
    private TextView eventNameTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private TextView hostTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private ToggleButton favoriteButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //viewModel = new ViewModelProvider(this).get(MainMenuTab1ViewModel.class);

        initViews();
        isFavorite();
        getIntentValues();
    }

    private void isFavorite() {
        favoriteButton.setChecked(false);
        favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_24));
        favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_star_24));
                else
                    favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_24));
            }
        });
    }

    private void initViews() {
        coverImageView = findViewById(R.id.coverImageView);
        eventNameTextView = findViewById(R.id.eventNameTextView);
        dateTextView = findViewById(R.id.dateTextView);
        locationTextView = findViewById(R.id.locationTextView);
        hostTextView = findViewById(R.id.hostTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        favoriteButton = findViewById(R.id.favoriteButton);
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_EVENT_SELECTED)){
            try {
                String json = intent.getStringExtra(Constants.KEY_EVENT_SELECTED);
                Event event = new Gson().fromJson(json, Event.class);
                Log.e("Tiempo", "getIntentValues");
                //viewModel.setEvent(event);
                //Log.e("EventName", event.getTitle());
                Picasso.get().load(event.getPhoto()).into(coverImageView);
                eventNameTextView.setText(event.getTitle());
                dateTextView.setText(getDate(event.getDate()));
                locationTextView.setText(event.getLocation());
                hostTextView.setText(event.getHost());
                descriptionTextView.setText(event.getDescription());

                String price = "Bs " + event.getPrice();
                priceTextView.setText(price);

            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}