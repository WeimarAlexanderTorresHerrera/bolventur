package bo.com.bolventur.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import bo.com.bolventur.viewModel.MainMenuViewModel;

import bo.com.bolventur.R;

public class MainMenuActivity extends AppCompatActivity {

    private Context context;
    private MainMenuViewModel mainMenuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        context = this;

    }


}