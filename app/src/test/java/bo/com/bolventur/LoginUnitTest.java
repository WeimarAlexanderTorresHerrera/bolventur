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

public class LoginUnitTest {
    RepositoryImpl repository;

    @Before
    public void before() {
        repository = new MockRepository();
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loginRegularUser() {
        String email = "test@test.com";
        String password = "test";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertTrue(base.isSuccessful());
            assertEquals(UserProfile.REGULAR, base.getData().getUserProfile());
            assertEquals(email, base.getData().getEmail());
            assertEquals(password, base.getData().getPassword());
        });
    }

    @Test
    public void loginFailRegularUser() {
        String email = "testFail@test.com";
        String password = "testFail";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_LOGIN, base.getErrorCode());
        });
    }

    @Test
    public void loginEmptyValues() {
        String email = "";
        String password = "";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_EMPTY_LOGIN_VALUES, base.getErrorCode());
        });
    }

    @Test
    public void loginEmailEmpty() {
        String email = "";
        String password = "test";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_EMPTY_EMAIL_VALUE, base.getErrorCode());
        });
    }

    @Test
    public void loginPasswordEmpty() {
        String email = "test@test.com";
        String password = "";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_EMPTY_PASSWORD_VALUE, base.getErrorCode());
        });
    }

    @Test
    public void loginInvalidEmail() {
        String email = "test";
        String password = "testFail";
        LiveData<Base<User>> result = repository.loginEmailPassword(email, password);

        assertNotNull(result);
        result.observeForever(base -> {
            assertFalse(base.isSuccessful());
            assertEquals(Constants.ERROR_EMAIL, base.getErrorCode());
        });
    }

}
