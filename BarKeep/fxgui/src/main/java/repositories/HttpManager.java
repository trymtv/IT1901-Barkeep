package repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpManager {

  static final String BASE_URL = "http://localhost:8080";

  static final HttpHost targetHost = new HttpHost("localhost", 8080, "http");

  private static HttpClientContext context;

  /**
   * Sets the user session credentials for authentication.
   *
   * @param username the username to login
   * @param password the password to login
   */
  public static void setContext(String username, String password) {
    CredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials(username, password));
    AuthCache authCache = new BasicAuthCache();
    authCache.put(targetHost, new BasicScheme());
    context = HttpClientContext.create();
    context.setCredentialsProvider(credsProvider);
    context.setAuthCache(authCache);
  }

  public static void clearContext() {
    context = null;
  }

  /**
   * Gets from the BASE_URL + url and returns the json response
   * using the given authentication context.
   *
   * @param url the end of the url to get from
   * @return the json returned
   * @throws IOException if the given json can not be parsed correctly
   */
  public static String getFrom(String url) throws IOException {
    CloseableHttpClient client = HttpClientBuilder.create().build();
    CloseableHttpResponse response = client.execute(
        new HttpGet(BASE_URL + url), context);
    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
  }


  /**
   * Post to the given BASE_URL + uri with the request body
   * with the set authentication context.
   *
   * @param uri         the end of the to post to.
   * @param requestBody the json to post.
   * @return the response code from the request.
   * @throws IOException             if the json can not be parsed correctly.
   * @throws AuthenticationException if the context set is not correctly configured.
   */
  public static int postJsonTo(String uri, String requestBody)
      throws IOException, AuthenticationException {
    HttpPost httpPost = new HttpPost(BASE_URL + uri);
    httpPost.setEntity(new StringEntity(requestBody));
    return executeHttp(httpPost);
  }

  /**
   * Deletes from the given BASE_URL + uri.
   *
   * @param uri the end of the url to delete from.
   * @return the response code from the request.
   * @throws IOException             if the uri is not correct
   * @throws AuthenticationException if the context set is not correctly configured.
   */
  public static int deleteFrom(String uri) throws IOException, AuthenticationException {
    HttpDelete httpDelete = new HttpDelete(BASE_URL + uri);
    return executeHttp(httpDelete);
  }

  private static int executeHttp(HttpRequestBase httpBase) throws IOException {
    CloseableHttpClient client = HttpClients.createDefault();
    httpBase.setHeader("Accept", "application/json");
    httpBase.setHeader("Content-type", "application/json");
    if (context != null) {
      return client.execute(httpBase, context).getStatusLine().getStatusCode();
    }
    return client.execute(httpBase).getStatusLine().getStatusCode();
  }

}
