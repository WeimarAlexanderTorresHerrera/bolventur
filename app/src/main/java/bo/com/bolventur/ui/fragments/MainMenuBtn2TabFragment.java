package bo.com.bolventur.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.ui.activities.EventActivity;
import bo.com.bolventur.ui.activities.MainMenuTabActivity;
import bo.com.bolventur.ui.adapters.EventAdapter;
import bo.com.bolventur.ui.callback.EventCallback;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.viewModel.MainMenuTab2ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn2TabFragment extends Fragment implements EventCallback{

    private static final String LOG = MainMenuBtn2TabFragment.class.getSimpleName();
    private Context context;

    private EventAdapter eventAdapter;
    private List<Event> events = new ArrayList<>();
    private RecyclerView eventRecyclerView;

    private MainMenuTab2ViewModel viewModel;

    public static MainMenuBtn2TabFragment newInstance() {
        MainMenuBtn2TabFragment fragment = new MainMenuBtn2TabFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainMenuTab2ViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mainmenu_btn2tab, container, false);
        initViews(root);
        initEvents();
        subscribeData();

        return root;
    }

    public void initViews(View view){
        eventRecyclerView = view.findViewById(R.id.musicalEventsRecyclerView);

        eventAdapter = new EventAdapter(events, context);
        eventRecyclerView.setAdapter(eventAdapter);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    public void initEvents(){
        eventAdapter.setCallback(this);
    }

    private void subscribeData() {
        viewModel.getEvents("").observe(getViewLifecycleOwner(), new Observer<Base<List<Event>>>() {
            @Override
            public void onChanged(Base<List<Event>> listBase) {
                if (listBase.isSuccessful()) {
                    events = listBase.getData();
                    eventAdapter.updateEvents(events);
                    Log.e("getEvents", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onEventClicked(Event event) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra(Constants.KEY_EVENT_SELECTED, new Gson().toJson(event));
        startActivity(intent);
        Log.e("clicked", event.getTitle());
    }
}