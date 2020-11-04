package bo.com.bolventur.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;

    private EventsApi eventsApi;
    private FavoritesApi favoritesApi;

    public static ApiRepository getInstance(){
        if (instance == null){
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository(){
        eventsApi = ApiService.createService(EventsApi.class);
        favoritesApi = ApiService.createService(FavoritesApi.class);
    }

    public LiveData<Base<List<Event>>> getEvents(){
        MutableLiveData<Base<List<Event>>> result = new MutableLiveData<>();
        eventsApi.getEvents().enqueue(new Callback<List<Event>>() {
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

    public LiveData<Base<List<Favorite>>> getFavorites() {
        MutableLiveData<Base<List<Favorite>>> result = new MutableLiveData<>();
        favoritesApi.getFavorites().enqueue(new Callback<List<Favorite>>() {
            @Override
            public void onResponse(Call<List<Favorite>> call, Response<List<Favorite>> response) {
                if (response.isSuccessful()) {
                    result.postValue(new Base<>(response.body()));
                } else {
                    result.postValue(new Base<>(Constants.ERROR_SERVER, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<List<Favorite>> call, Throwable t) {
                result.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, new Exception(t)));
            }
        });

        return result;
    }

    public LiveData<Base<Favorite>> updateFavorite(Favorite favorite) {
        MutableLiveData<Base<Favorite>> result = new MutableLiveData<>();
        favoritesApi.updateFavorite(favorite.getId(), favorite).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if(response.isSuccessful()) {
                    result.postValue(new Base<>(response.body()));
                } else {
                    result.postValue(new Base<>(Constants.ERROR_SERVER, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                result.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, new Exception(t)));
            }
        });

        return result;
    }

    public LiveData<Base<Favorite>> createFavorite(Favorite favorite) {
        MutableLiveData<Base<Favorite>> result = new MutableLiveData<>();
        favoritesApi.createFavorite(favorite).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if(response.isSuccessful()) {
                    result.postValue(new Base<>(response.body()));
                } else {
                    result.postValue(new Base<>(Constants.ERROR_SERVER, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
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
