package ezain.poc.oauth2.config;

import com.google.inject.Singleton;
import ezain.poc.oauth2.authentication.AuthValidator;
import ezain.poc.oauth2.utils.GsonMessageBodyHandler;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class Oauth2App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(AuthValidator.class);
        s.add(GsonMessageBodyHandler.class);
        return s;
    }
}
