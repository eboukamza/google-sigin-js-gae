package ezain.poc.oauth2.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ezain.poc.oauth2.dao.UserInfoDao;
import ezain.poc.oauth2.domain.UserInfo;

@Singleton
public class UserInfoDaoImpl extends GenericDaoImpl<UserInfo> implements UserInfoDao {

    @Inject
    public UserInfoDaoImpl() {
        super(UserInfo.class);
    }
}
