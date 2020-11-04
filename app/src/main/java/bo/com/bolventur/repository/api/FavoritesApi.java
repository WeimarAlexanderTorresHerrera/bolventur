package bo.com.bolventur.repository.api;

import java.util.List;

import bo.com.bolventur.model.Favorite;
import bo.com.bolventur.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FavoritesApi {
    @GET(Constants.RESOURCE_FAVORITES)
    Call<List<Favorite>> getFavorites();

    @PUT(Constants.RESOURCE_FAVORITES + "/{id}")
    Call<Favorite> updateFavorite(@Path("id") int id, @Body Favorite favorite);

    @POST(Constants.RESOURCE_FAVORITES)
    Call<Favorite> createFavorite(@Body Favorite favorite);
}
