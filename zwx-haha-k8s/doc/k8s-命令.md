#### kubectl version
> 查看k8s客户端和服务端版本信息
```shell script
$ kubectl version
Client Version: version.Info{Major:"1", Minor:"17", GitVersion:"v1.17.3", 
GitCommit:"06ad960bfd03b39c8310aaf92d1e7c12ce618213", GitTreeState:"clean", 
BuildDate:"2020-02-11T18:14:22Z", GoVersion:"go1.13.6", Compiler:"gc", Platform:"linux/amd64"}

Server Version: version.Info{Major:"1", Minor:"17", GitVersion:"v1.17.3", 
GitCommit:"06ad960bfd03b39c8310aaf92d1e7c12ce618213", GitTreeState:"clean", 
BuildDate:"2020-02-11T18:07:13Z", GoVersion:"go1.13.6", Compiler:"gc", Platform:"linux/amd64"}
```

#### kubectl cluster-info
> 查看k8s集群信息
```shell script
$ kubectl cluster-info
Kubernetes master is running at https://172.17.0.64:8443
KubeDNS is running at https://172.17.0.64:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
```

#### kubectl get nodes
> 查看k8s集群node信息
```shell script
$ kubectl get nodes
NAME       STATUS   ROLES    AGE   VERSION
minikube   Ready    master   17m   v1.17.3
```

#### kubectl cluster-info
> 查看k8s集群信息
```shell script
$ kubectl cluster-info
Kubernetes master is running at https://172.17.0.64:8443
KubeDNS is running at https://172.17.0.64:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
```
