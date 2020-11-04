package bo.com.bolventur.repository.api;

import java.util.List;

import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsApi {
    @GET(Constants.RESOURCE_EVENTS)
    Call<List<Event>> getEvents();

    @GET(Constants.RESOURCE_EVENTS)
    Call<List<Event>> getEventsFav();
}
