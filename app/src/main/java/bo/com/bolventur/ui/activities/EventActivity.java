package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.MainMenuTab1ViewModel;

public class EventActivity extends AppCompatActivity {

    private static final String LOG = EventActivity.class.getSimpleName();
    private MainMenuTab1ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        viewModel = new ViewModelProvider(this).get(MainMenuTab1ViewModel.class);

        getIntentValues();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_EVENT_SELECTED)){
            try {
                String json = intent.getStringExtra(Constants.KEY_EVENT_SELECTED);
                Event event = new Gson().fromJson(json, Event.class);
                Log.e("Tiempo", "getIntentValues");
                viewModel.setEvent(event);
                Log.e("EventName", event.getTitle());
            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }
}