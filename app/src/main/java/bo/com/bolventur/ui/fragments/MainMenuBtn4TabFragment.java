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
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.ui.adapters.EventAdapter;
import bo.com.bolventur.ui.activities.EventActivity;
import bo.com.bolventur.ui.callback.EventCallback;
import bo.com.bolventur.utils.ErrorMapper;
import bo.com.bolventur.utils.Constants;
import bo.com.bolventur.viewModel.MainMenuTab4ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuBtn4TabFragment extends Fragment implements EventCallback {

    private static final String LOG = MainMenuBtn4TabFragment.class.getSimpleName();
    private Context context;

    private EventAdapter eventAdapter;
    private List<Event> events = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();

    private RecyclerView eventRecyclerView;

    private MainMenuTab4ViewModel viewModel;

    private String user;

    public static MainMenuBtn4TabFragment newInstance(String user) {
        MainMenuBtn4TabFragment fragment = new MainMenuBtn4TabFragment(user);
        return fragment;
    }

    public MainMenuBtn4TabFragment(String user) {
        this.user = user;
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
        subscribeData();

        return root;
    }

    public void initViews(View view){
        //Buscar los ID dento del view.
        eventRecyclerView = view.findViewById(R.id.favoritesEventsRecyclerView);

        eventAdapter = new EventAdapter(events, context);
        eventRecyclerView.setAdapter(eventAdapter);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    public void initEvents(){
        eventAdapter.setCallback(this);
    }

    private void subscribeData() {
        viewModel.getFavorites().observe(getViewLifecycleOwner(), listBase -> {
            if (listBase.isSuccessful()) {
                favorites = listBase.getData();
                Log.e("getFavoritesBD", new Gson().toJson(favorites));
            } else {
                Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getEvents("").observe(getViewLifecycleOwner(), listBase -> {
            if (listBase.isSuccessful()) {
                events = listBase.getData();
                User user = new Gson().fromJson(this.user, User.class);

                Iterator<Event> it = events.iterator();
                while (it.hasNext()) {
                    Event current = it.next();

                    boolean aux = false;
                    Iterator<Favorite> itF = favorites.iterator();
                    while (itF.hasNext()) {
                        Favorite currentF = itF.next();
                        if (currentF.getFavorite() == 1 && currentF.getUid().equals(user.getUid()) && currentF.getEid().equals(current.getUid())) {
                            aux = true;
                        }
                    }
                    if (!aux) {
                        it.remove();
                    }
                }

                eventAdapter.updateEvents(events);
                Log.e("getFavorites", new Gson().toJson(events));
            } else {
                Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEventClicked(Event event) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra(Constants.KEY_EVENT_SELECTED, new Gson().toJson(event));
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        Log.e("clicked", event.getTitle());
    }
}