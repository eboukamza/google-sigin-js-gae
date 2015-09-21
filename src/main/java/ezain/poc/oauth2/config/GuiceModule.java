package ezain.poc.oauth2.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.googlecode.objectify.ObjectifyFilter;
import ezain.poc.oauth2.dao.UserInfoDao;
import ezain.poc.oauth2.dao.impl.UserInfoDaoImpl;

public class GuiceModule extends AbstractModule {
    public GuiceModule() {
    }

    @Override
    public void configure() {

        bind(ObjectifyFilter.class).in(Singleton.class);

        bind(UserInfoDao.class).to(UserInfoDaoImpl.class).in(RequestScoped.class);

    }
}
