package com.zwx.k8s.test.deployment;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import zwx.k8s.client.K8sClientInit;

import java.io.IOException;

/**
 * @author: haha
 * @date : 2020-08-27 15:11
 */
@Slf4j
public class Test1 {
    static ApiClient client;

    static {
        try {
            client = K8sClientInit.getClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String name = "k8s-springboot-demo";
    String namespace = "default";

    @Test
    public void test1() throws ApiException {
        //        AppsApi appsApi = new AppsApi(client);
        AppsV1Api appsV1Api = new AppsV1Api(client);
        V1Deployment v1Deployment = appsV1Api.readNamespacedDeployment(name, namespace, null, null, null);
        System.out.println(v1Deployment);
    }
}
