package bo.com.bolventur.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.repository.MockRepository;
import bo.com.bolventur.repository.RepositoryImpl;

public class RegisterViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        repository = new MockRepository(application);
    }

    public LiveData<Base<User>> register(String email, String password, String name, String confirmPsswd) {
        return repository.register(email, password, name, confirmPsswd);
    }

}
