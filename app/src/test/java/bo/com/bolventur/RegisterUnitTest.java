package bo.com.bolventur;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bo.com.bolventur.model.Base;
import bo.com.bolventur.model.users.User;
import bo.com.bolventur.model.users.UserProfile;
import bo.com.bolventur.repository.MockRepository;
import bo.com.bolventur.repository.RepositoryImpl;
import bo.com.bolventur.utils.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegisterUnitTest {
    RepositoryImpl repository;

    @Before
    public void before() {
        repository = new MockRepository(null);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void registerConfirmPassword() {
        //String uid = "0000";
        String email = "test@test.com";
        String password = "test";
        String confPassword = "test";
        String name = "Test";
        LiveData<Base<User>> result = repository.register(email, password, name, confPassword);

        assertNotNull(result);
        result.observeForever(base -> {
            assertTrue(base.isSuccessful());
            assertEquals(UserProfile.REGULAR, base.getData().getUserProfile());
            assertEquals(email, base.getData().getEmail());
            assertEquals(password, base.getData().getPassword());
            assertEquals(name, base.getData().getName());
        });
    }

    @Test
    public void registerFailConfirmPassword() {
        String email = "test@test.com";
        String password = "test123";
        String confPassword = "test";
        String name = "Test";
        LiveData<Base<User>> result = repository.register(email, password, name, confPassword);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_DONT_MATCH, base.getErrorCode());
        });
    }

    @Test
    public void registerEmptyValues() {
        String email = "test@test.com";
        String password = "test123";
        String confPassword = "test";
        String name = "";
        LiveData<Base<User>> result = repository.register(email, password, name, confPassword);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_EMPTY_REGISTER_VALUES, base.getErrorCode());
        });
    }

    @Test
    public void registerInvalidEmail() {
        String email = "test.com";
        String password = "test";
        String confPassword = "test";
        String name = "Test";
        LiveData<Base<User>> result = repository.register(email, password, name, confPassword);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_INVALID_EMAIL, base.getErrorCode());
        });
    }

}
