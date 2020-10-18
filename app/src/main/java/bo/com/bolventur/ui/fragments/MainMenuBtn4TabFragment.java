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
import bo.com.bolventur.viewModel.MainMenuTab4ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn4TabFragment extends Fragment {

    private static final String LOG = MainMenuBtn4TabFragment.class.getSimpleName();
    private Context context;


    private MainMenuTab4ViewModel viewModel;

    public static MainMenuBtn4TabFragment newInstance() {
        MainMenuBtn4TabFragment fragment = new MainMenuBtn4TabFragment();
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
        viewModel = new ViewModelProvider(this).get(MainMenuTab4ViewModel.class);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mainmenu_btn4tab, container, false);
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