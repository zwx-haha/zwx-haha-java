package zwx.k8s.client.deployment;

import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.ApiResponse;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.ExtensionsV1beta1Deployment;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1Scale;
import lombok.extern.slf4j.Slf4j;
import zwx.k8s.client.K8sClientInit;

import java.io.IOException;

/**
 * @author: haha
 * @date : 2020-08-27 13:13
 */
@Slf4j
public class DeploymentUtils {
    private ApiClient apiClient;
    public AppsV1Api apiInstance;

    public DeploymentUtils(ApiClient apiClient) {
        this.apiClient = apiClient;
        apiInstance = new AppsV1Api(apiClient);
    }

    /**
     * 查询所有的deployment
     *
     * @return
     * @throws ApiException
     */
    public V1DeploymentList queryAllDeployment() throws ApiException {
        V1DeploymentList v1DeploymentList =
                apiInstance.listDeploymentForAllNamespaces(
                        null, null, null, null, null, null, null, null, null);
        return v1DeploymentList;
    }

    /**
     * 根据 名称和命名空间查询deployment信息
     *
     * @param name
     * @param namespacename
     * @return
     * @throws ApiException
     */
    public V1Deployment querDeployment(String name, String namespacename) throws ApiException {
        V1Deployment v1Deployment =
                apiInstance.readNamespacedDeployment(name, namespacename, null, null, null);
        return v1Deployment;
    }

    public ExtensionsV1beta1Deployment querDeployment(String namespacename) {
        return null;
    }

    /**
     * 根据 名称和命名空间 更新 deployment 配置文件
     *
     * @param name
     * @param namespace
     * @param body
     * @return
     * @throws ApiException
     */
    public ApiResponse<V1Scale> updateDeployment(String name, String namespace, V1Patch body)
            throws ApiException {
        ApiResponse<V1Scale> aFalse = apiInstance.patchNamespacedDeploymentScaleWithHttpInfo(name, namespace, body, "false", null, null, null);
        return aFalse;
    }

    public static void main(String[] args) throws IOException, ApiException, InterruptedException {
        ApiClient client = K8sClientInit.getClient();
        //TODO: debug模式，开发环境打开，生成环境关闭
//        client.setDebugging(true);
        DeploymentUtils deploymentUtils = new DeploymentUtils(client);

        //腾讯云，esc部署命名空间
        String namespace = "tke-cluster-inspection";
        //腾讯云，esc部署deployment名称
        String name = "esc-gateway";

        //TODO: 查询所有的deployment信息
//        V1DeploymentList v1DeploymentList = deploymentUtils.queryAllDeployment();
//        System.out.println(v1DeploymentList);

        //TODO: 查询腾旭云 esc deployment信息
        V1Deployment v1Deployment = deploymentUtils.querDeployment(name, namespace);
        System.out.println("名称: " + v1Deployment.getMetadata().getName());
        System.out.println("实例数量: " + v1Deployment.getSpec().getReplicas());
        System.out.println("正在运行实例数: " + v1Deployment.getStatus().getReadyReplicas());


        //TODO: 修改腾旭云 esc deployment 副本数量
        String jsonPatchStr = "[{\"op\":\"replace\",\"path\":\"/spec/replicas\", \"value\": " + 5 + " }]";
        V1Patch body = new V1Patch(jsonPatchStr);
        ApiResponse<V1Scale> response = deploymentUtils.updateDeployment(name, namespace, body);
        System.out.println(response.getStatusCode() + "" + response.getHeaders());

        // TODO: 查询腾旭云 esc deployment 当前部署pod数量
        v1Deployment = deploymentUtils.querDeployment(name, namespace);
        System.out.println("名称: " + v1Deployment.getMetadata().getName());
        System.out.println("实例数: " + v1Deployment.getSpec().getReplicas());
        System.out.println("正在运行实例数: " + v1Deployment.getStatus().getReadyReplicas());

        // TODO: 演示3秒，POD动态更新，k8s需要耗时，不是实时响应
        Thread.sleep(3000);

        // TODO: 查询腾旭云 esc deployment 当前部署pod数量
        v1Deployment = deploymentUtils.querDeployment(name, namespace);
        System.out.println("名称: " + v1Deployment.getMetadata().getName());
        System.out.println("实例数: " + v1Deployment.getSpec().getReplicas());
        System.out.println("正在运行实例数: " + v1Deployment.getStatus().getReadyReplicas());
    }
}
