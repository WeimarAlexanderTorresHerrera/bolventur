package bo.com.bolventur.ui.activities;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import bo.com.bolventur.R;
import bo.com.bolventur.ui.adapters.MainMenuTabPagerAdapter;
import bo.com.bolventur.viewModel.MainMenuViewModel;

public class MainMenuTabActivity extends AppCompatActivity {

    private static final String LOG = MainMenuTabActivity.class.getSimpleName();
    private Context context;

    private ViewPager viewPager;
    private TabLayout tabs;
    private FloatingActionButton fab;

    private MainMenuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_tab);
        context =this;
        viewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);

        initViews();
        initEvents();

    }

    private void initViews(){
        MainMenuTabPagerAdapter mainMenuTabPagerAdapter = new MainMenuTabPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(mainMenuTabPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);

    }
    private void initEvents(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}