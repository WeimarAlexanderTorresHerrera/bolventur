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
import bo.com.bolventur.viewModel.MainMenuTab1ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn1TabFragment extends Fragment {

    private static final String LOG = MainMenuBtn1TabFragment.class.getSimpleName();
    private Context context;


    private MainMenuTab1ViewModel viewModel;

    public static MainMenuBtn1TabFragment newInstance() {
        MainMenuBtn1TabFragment fragment = new MainMenuBtn1TabFragment();
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
        viewModel = new ViewModelProvider(this).get(MainMenuTab1ViewModel.class);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mainmenu_btn1tab, container, false);
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