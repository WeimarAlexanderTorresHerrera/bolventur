package bo.com.bolventur.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;
    private EventsApi eventsApi;

    public static ApiRepository getInstance(){
        if (instance == null){
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository(){
        eventsApi = ApiService.createService(EventsApi.class);
    }

    public LiveData<Base<List<Event>>> getEvents(){
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();
        eventsApi.getEvents(Constants.QUERY_PARAM_ALT).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    result.postValue(new Base<>(response.body()));
                }else{
                    result.postValue(new Base<>(Constants.ERROR_SERVER, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                result.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, new Exception(t)));
            }
        });
        return result;
    }

    public LiveData<Base<List<Event>>> getEventsFav(){
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();
        eventsApi.getEventsFav(Constants.QUERY_PARAM_ALT).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    result.postValue(new Base<>(response.body()));
                }else{
                    result.postValue(new Base<>(Constants.ERROR_SERVER, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                result.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, new Exception(t)));
            }
        });
        return result;
    }

}
