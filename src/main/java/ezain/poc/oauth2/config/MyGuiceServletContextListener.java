package ezain.poc.oauth2.config;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Map;

import static org.glassfish.jersey.servlet.ServletProperties.JAXRS_APPLICATION_CLASS;

/**
 * Application listener. <p/>
 * Intercept /api requests and sends them to ItellicolocApp. <p/>
 * Use GuiceModule for dependency injection. <p/>
 */
public class MyGuiceServletContextListener extends GuiceServletContextListener {

    protected Injector getInjector() {

        final Map<String, String> params = Maps.newHashMap();

        params.put(JAXRS_APPLICATION_CLASS, Oauth2App.class.getCanonicalName());

        return Guice.createInjector(
                new GuiceModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {

                        filter("/auth/*").through(ObjectifyFilter.class);
                        serve("/auth/*").with(GuiceContainer.class, params);

                    }
                });
    }
}
