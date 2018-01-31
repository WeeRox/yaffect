package se.yaffect.android.oauth.grant;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import se.yaffect.android.R;
import se.yaffect.android.oauth.ClientCredentials;
import se.yaffect.android.oauth.token.AccessToken;

public class ResourceOwnerPasswordCredentialsGrant {

    private Context context;
    private ClientCredentials credentials;

    public ResourceOwnerPasswordCredentialsGrant(Context context, ClientCredentials credentials) {
        this.context = context;
        this.credentials = credentials;
    }

    public AccessToken getAccessToken(String username, String password) {
        try {
            InputStream rawResource = context.getResources().openRawResource(R.raw.app);
            Properties properties = new Properties();
            properties.load(rawResource);

            String requestBody = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL(properties.getProperty("URL_OAUTH2") + "/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Basic " + credentials.toString());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.append(requestBody);
            writer.flush();
            writer.close();

            // TODO: read the response from the server
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}