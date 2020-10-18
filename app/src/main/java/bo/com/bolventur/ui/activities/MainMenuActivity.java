package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.viewModel.LoginViewModel;
import bo.com.bolventur.viewModel.MainMenuViewModel;

import bo.com.bolventur.R;

public class MainMenuActivity extends AppCompatActivity {

    private Context context;
    private MainMenuViewModel mainMenuViewModel;

    private List<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        context = this;
        mainMenuViewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);

        subscribeData();
    }

    private void subscribeData() {
        mainMenuViewModel.getEvents("").observe(this, new Observer<Base<List<Event>>>() {
            @Override
            public void onChanged(Base<List<Event>> listBase) {

                if (listBase.isSuccessful()) {
                    events = listBase.getData();
                    //adapter.updateItems(events);
                    Log.e("getStartups", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}