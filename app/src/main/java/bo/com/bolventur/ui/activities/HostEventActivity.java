package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.HostEventViewModel;


public class HostEventActivity extends AppCompatActivity {

    private Context context;
    private static final String LOG = HostEventActivity.class.getName();
    private String uidHost;

    private HostEventViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event);

        context =this;
        viewModel = new ViewModelProvider(this).get(HostEventViewModel.class);

        getSupportActionBar().hide();
        initViews();
        initEvents();
        getIntentValues();


    }

    private void initViews() {

    }

    private void initEvents() {

    }
    private void getIntentValues(){
        Intent intent =getIntent();
        if (intent.hasExtra(Constants.KEY_EVENT_UID_LOGIN)){
            uidHost = intent.getStringExtra(Constants.KEY_EVENT_UID_LOGIN);
            observeEvent(uidHost);
        }

    }


    private void observeEvent(String uidHost){
        viewModel.observeEvents(uidHost).observe(this, listBase -> {
            if(listBase.isSuccessful()){
                List<Event> events = listBase.getData();
                Log.e("Events", new Gson().toJson(events));
            }else{

            }
        });
    }
}