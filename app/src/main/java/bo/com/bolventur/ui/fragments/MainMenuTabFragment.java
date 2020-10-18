package bo.com.bolventur.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import bo.com.bolventur.R;
import bo.com.bolventur.viewModel.MainMenuTabViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuTabFragment extends Fragment {

    //private static final String LOG = PlaceholderFragment.class.getSimpleName();
    private Context context;


    private MainMenuTabViewModel mainMenuTabViewModel;

    public static MainMenuTabFragment newInstance() {
        MainMenuTabFragment fragment = new MainMenuTabFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context =context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mainMenuTabViewModel = new ViewModelProvider(this).get(MainMenuTabViewModel.class);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_menu_tab, container, false);
        return root;
    }
}