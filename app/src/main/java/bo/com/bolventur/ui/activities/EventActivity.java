package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.viewModel.EventViewModel;

public class EventActivity extends AppCompatActivity {

    private static final String LOG = EventActivity.class.getSimpleName();
    private EventViewModel viewModel;

    private ImageView coverImageView;
    private TextView eventNameTextView;
    private TextView dateTextView;
    private TextView locationTextView;
    private TextView hostTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private ToggleButton favoriteButton;

    private Event event;
    private User user;

    private Favorite favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        viewModel = new ViewModelProvider(this).get(EventViewModel.class);
        getSupportActionBar().hide();
        initViews();
        getIntentValues();
        subscribeToData();
    }

    private void isFavorite() {
        if (event.getCategory() == 0) {
            if (favorite != null) {
                if (favorite.isFavorite() == 0) {
                    favoriteButton.setChecked(false);
                    favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_24));
                } else {
                    favoriteButton.setChecked(true);
                    favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_24));
                }
            } else {
                favoriteButton.setChecked(false);
                favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_24));
            }
        } else {
            favoriteButton.setChecked(false);
            favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_border_24));
        }

        favoriteButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (favorite != null) {
                if (favorite.isFavorite() == 1) {
                    favorite.setFavorite(0);

                    viewModel.updateFavorite(favorite).observe(this, favoriteBase -> {
                        if (favoriteBase.isSuccessful()) {
                            Log.e(LOG, "updateFavorite.isSuccess:" + favoriteBase.getData());
                        } else {
                            Log.e(LOG, "updateFavorite.error", favoriteBase.getException());
                        }
                    });
                } else {
                    favorite.setFavorite(1);

                    viewModel.updateFavorite(favorite).observe(this, favoriteBase -> {
                        if (favoriteBase.isSuccessful()) {
                            Log.e(LOG, "updateFavorite.isSuccess:" + favoriteBase.getData());
                        } else {
                            Log.e(LOG, "updateFavorite.error", favoriteBase.getException());
                        }
                    });
                }
            } else {
                favorite = new Favorite();
                favorite.setUid(user.getUid());
                favorite.setEid(event.getUid());
                favorite.setFavorite(1);

                viewModel.createFavorite(favorite).observe(this, favoriteBase -> {
                    if (favoriteBase.isSuccessful()) {
                        Log.e(LOG, "createFavorite.isSuccess:" + favoriteBase.getData());
                    } else {
                        Log.e(LOG, "createFavorite.error", favoriteBase.getException());
                    }
                });
            }
            if (isChecked) {
                favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_star_24));
            } else {
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
                event = new Gson().fromJson(json, Event.class);
                Log.e("Event Activity event", json);

                String jsonUser = intent.getStringExtra(Constants.KEY_USER);
                user = new Gson().fromJson(jsonUser, User.class);
                Log.e("Event Activity user", jsonUser);

                Picasso.get().load(event.getPhoto()).into(coverImageView);
                eventNameTextView.setText(event.getTitle());
                dateTextView.setText(getDate(event.getDate()));
                locationTextView.setText(event.getLocation());
                hostTextView.setText(event.getHost());
                descriptionTextView.setText(event.getDescription());

                String price = "Bs. " + event.getTicket().get("price");
                priceTextView.setText(price);

            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("E, MMM dd yyyy HH:00", cal).toString();
        return date;
    }

    private void subscribeToData() {
        viewModel.getFavorites().observe(this, listBase -> {
            if (listBase.isSuccessful()) {
                List<Favorite> favorites = listBase.getData();
                Iterator<Favorite> it = favorites.iterator();
                while (it.hasNext()) {
                    Favorite current = it.next();
                    if (!current.getUid().equals(user.getUid()) || !current.getEid().equals(event.getUid())) {
                        it.remove();
                    }
                }
                if (!favorites.isEmpty()) {
                    favorite = favorites.get(0);
                }

                Log.e("Event favorites", new Gson().toJson(favorite));
                isFavorite();
            } else {
                Toast.makeText(getApplicationContext(), ErrorMapper.getError(getApplicationContext(), listBase.getErrorCode()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}