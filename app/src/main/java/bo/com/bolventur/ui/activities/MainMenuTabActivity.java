package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.ui.adapters.MainMenuTabPagerAdapter;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.MainMenuTab2ViewModel;

public class MainMenuTabActivity extends AppCompatActivity {

    private static final String LOG = MainMenuTabActivity.class.getSimpleName();
    private Context context;

    private ViewPager viewPager;
    private TabLayout tabs;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_tab);
        context = this;

        getIntentValues();
        initViews();
        initEvents();
    }

    private void initViews(){
        MainMenuTabPagerAdapter mainMenuTabPagerAdapter = new MainMenuTabPagerAdapter(this, getSupportFragmentManager(), user);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(mainMenuTabPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_USER)){
            try {
                user = intent.getStringExtra(Constants.KEY_USER);
            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }

    private void initEvents(){

    }
}