package bo.com.bolventur.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.ui.activities.EventActivity;
import bo.com.bolventur.ui.adapters.EventAdapter;
import bo.com.bolventur.ui.callback.EventCallback;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.MainMenuTab4ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn4TabFragment extends Fragment implements EventCallback {

    private static final String LOG = MainMenuBtn4TabFragment.class.getSimpleName();
    private Context context;

    private EventAdapter eventAdapter;
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
        eventAdapter.setCallback(this);
    }

    @Override
    public void onEventClicked(Event event) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra(Constants.KEY_EVENT_SELECTED, new Gson().toJson(event));
        startActivity(intent);
        Log.e("clicked", event.getTitle());
    }
}