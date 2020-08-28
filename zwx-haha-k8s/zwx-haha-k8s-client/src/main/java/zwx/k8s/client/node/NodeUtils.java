package zwx.k8s.client.node;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import lombok.extern.slf4j.Slf4j;
import zwx.k8s.client.K8sClientInit;

import java.io.IOException;
import java.util.List;

/**
 * @author: haha
 * @date : 2020-08-27 13:27
 */
@Slf4j
public class NodeUtils {
    private ApiClient apiClient;

    public NodeUtils(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public V1NodeList queryAllNodes() throws ApiException {
        CoreV1Api apiInstance = new CoreV1Api(apiClient);
        V1NodeList v1NodeList =
                apiInstance.listNode(null, null, null, null, null, null, null, null, null);
        return v1NodeList;
    }

    public static void main(String[] args) throws IOException, ApiException {
        ApiClient client = K8sClientInit.getClient();
        NodeUtils nodeUtils = new NodeUtils(client);
        V1NodeList v1NodeList = nodeUtils.queryAllNodes();
        List<V1Node> items = v1NodeList.getItems();
        for (V1Node node : items) {
            System.out.println("节点名称:   " + node.getMetadata().getName());
            System.out.println("节点ip:   " + node.getStatus().getAddresses().get(0).getAddress());
            System.out.println("版本: " + node.getStatus().getNodeInfo().getKubeletVersion());
            System.out.println("节点创建时间: " + node.getMetadata().getCreationTimestamp());
            System.out.println();
        }
    }
}
