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
    Call<List<Event>> getEvents(@Query("alt") String alt);

    @GET(Constants.RESOURCE_EVENTS2)
    Call<List<Event>> getEventsFav(@Query("alt") String alt);

    //Esto no se utiliza
    @GET(Constants.RESOURCE_EVENTS + "/{id}")
    Call<Event> getOnEvent(@Path("id") int id);

    @POST(Constants.RESOURCE_EVENTS)
    Call<Event> createEvent(@Body Event event);

    @PUT(Constants.RESOURCE_EVENTS)
    Call<Event> create(@Body Event event);

    @PATCH(Constants.RESOURCE_EVENTS + "/{id}")
    Call<Event> update(@Path("id") int id,@Body Event event);

    @DELETE(Constants.RESOURCE_EVENTS + "/{id}")
    Call delete(@Path("id") int id);
}
