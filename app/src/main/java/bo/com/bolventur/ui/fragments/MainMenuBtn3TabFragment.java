package bo.com.bolventur.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import bo.com.bolventur.R;
import bo.com.bolventur.viewModel.MainMenuTab3ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn3TabFragment extends Fragment {

    private static final String LOG = MainMenuBtn3TabFragment.class.getSimpleName();
    private Context context;


    private MainMenuTab3ViewModel viewModel;

    public static MainMenuBtn3TabFragment newInstance() {
        MainMenuBtn3TabFragment fragment = new MainMenuBtn3TabFragment();
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
        viewModel = new ViewModelProvider(this).get(MainMenuTab3ViewModel.class);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mainmenu_btn3tab, container, false);
        initViews(root);
        initEvents();
        return root;
    }

    public void initViews(View view){
        //Buscar los ID dento del view.
    }

    public void initEvents(){

    }

}