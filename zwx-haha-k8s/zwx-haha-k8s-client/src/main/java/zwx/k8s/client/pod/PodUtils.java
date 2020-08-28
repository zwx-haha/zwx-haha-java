package zwx.k8s.client.pod;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-08-28 11:02
 */

@Slf4j
public class PodUtils {

    private ApiClient apiClient;
    public CoreV1Api apiInstance;

    public PodUtils(ApiClient apiClient) {
        this.apiClient = apiClient;
        apiInstance = new CoreV1Api(apiClient);
    }

    public static void main(String[] args) {
    }
}
