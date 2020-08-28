package zwx.k8s.client;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: haha
 * @date : 2020-08-27 13:01
 */
public class K8sClientInit {

    private static final String CONFIG_PATH = "tke/cls-keze9x93-config";

    private static ApiClient client;

    public static ApiClient getClient() throws IOException {
        if (client == null) {
            synchronized (K8sClientInit.class) {
                if (client == null) {
                    client = initApiClient();
                }
            }
        }
        return client;
    }

    private static InputStream loadConfig() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(CONFIG_PATH);
        InputStream inputStream = classPathResource.getInputStream();
        return inputStream;
    }

    private static ApiClient initApiClient() throws IOException {
        InputStream inputStream = loadConfig();
        client = Config.fromConfig(inputStream);
        client.setReadTimeout(60 * 1000);
        client.setConnectTimeout(60 * 1000);
        return client;
    }

    public static void main(String[] args) throws IOException {
        ApiClient client = K8sClientInit.getClient();
        KeyManager[] keyManagers = client.getKeyManagers();
        System.out.println(client.getBasePath());
    }
}
