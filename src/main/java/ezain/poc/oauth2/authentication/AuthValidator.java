package ezain.poc.oauth2.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.inject.Inject;
import ezain.poc.oauth2.dao.UserInfoDao;
import ezain.poc.oauth2.domain.UserInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

@Path("/validate")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class AuthValidator {

    private final static String CLIENT_ID = "1086496568808-cqu6q1naonuhn02kt3h618nn7gl2m36i.apps.googleusercontent.com";

    private final UserInfoDao userInfoDao;

    private final static Logger logger = Logger.getLogger(AuthValidator.class.getName());

    @Inject
    public AuthValidator(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @GET
    public Response doGoogleValidate(@QueryParam("tokenId") String tokenId) {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JsonFactory JSON_FACTORY = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
                .setAudience(Arrays.asList(CLIENT_ID))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(tokenId);
        } catch (GeneralSecurityException | IOException | NullPointerException e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Malformed Token").build();
        }


        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            UserInfo userInfo = new UserInfo(payload.get("sub").toString(),
                    payload.getEmail(),
                    payload.get("name").toString(),
                    payload.get("locale").toString());

            if (userInfoDao.getEntityById(userInfo.getUserId()) == null) {
                userInfoDao.insertEntity(userInfo);
                logger.info("User created.");
                logger.info(userInfo.toString());
            }


            return Response.ok().entity(payload).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Token").build();

    }


}
