package bo.com.bolventur.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.users.User;

public interface RepositoryImpl {

    LiveData<Base<User>> loginEmailPassword(String email, String password);

    LiveData<Base<List<Event>>> getEventsTab1(String category);

    LiveData<Base<List<Event>>> getEventsTab2(String category);

    LiveData<Base<List<Event>>> getEventsTab3(String category);

    LiveData<Base<List<Event>>> getEventsTab4(String category);

    LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd);

    /* ************************* Events **************************/

    LiveData<Base<String>> addEventToHost(String uidHost, Event event, Uri image);

    LiveData<Base<List<Event>>> observeHostEvent(String uidHost);

    LiveData<Base<List<Event>>> observeMusicalEvent();

}
