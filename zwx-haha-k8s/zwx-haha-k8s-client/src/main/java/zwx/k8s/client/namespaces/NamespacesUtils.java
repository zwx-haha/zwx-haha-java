package zwx.k8s.client.namespaces;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import lombok.extern.slf4j.Slf4j;
import zwx.k8s.client.K8sClientInit;

import java.io.IOException;

/**
 * @author: haha
 * @date : 2020-08-27 13:17
 */
@Slf4j
public class NamespacesUtils {
    private ApiClient apiClient;

    public NamespacesUtils(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public V1NamespaceList queryAllNameSpaces() throws ApiException {
        CoreV1Api apiInstance = new CoreV1Api(apiClient);
        /**
         * String pretty, Boolean allowWatchBookmarks, String _continue, String fieldSelector, String
         * labelSelector, Integer limit, String resourceVersion, Integer timeoutSeconds, Boolean watch
         */
        V1NamespaceList v1NamespaceList =
                apiInstance.listNamespace(null, null, null, null, null, null, null, null, null);
        return v1NamespaceList;
    }

    public static void main(String[] args) throws IOException, ApiException {
        ApiClient client = K8sClientInit.getClient();
        NamespacesUtils namespacesUtils = new NamespacesUtils(client);
        V1NamespaceList v1NamespaceList = namespacesUtils.queryAllNameSpaces();
        for (V1Namespace namespace : v1NamespaceList.getItems()) {
            System.out.println(namespace.getApiVersion());
            System.out.println(namespace.getMetadata().getName());
        }
    }
}
