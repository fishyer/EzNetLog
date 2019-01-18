# EzNetLog

#### 解决问题

拓展Chunk库，可以自定义想要打印的网络日志，与网络库解耦(Chunk只支持OkHttp)

#### 使用方式

maven地址：
```
maven { url "http://192.168.199.65:8081/repository/maven-public/"}
```

库依赖方式
```
implementation 'com.ezbuy:ezutil:5'
```

#### 使用示例

```
ChuckUtil chuckUtil = new ChuckUtil(context);
HttpTransaction transaction = new HttpTransaction();
transaction.setRequestDate(new Date());
transaction.setMethod(getMethodName(modal));
transaction.setRequestHeaders(getHttpHeaderList(modal));
transaction.setRequestBody(getRequestBody(modal));
transaction.setResponseBody(getResponseBody(modal));
transaction.setUrl(getUrl(modal));
transaction.setPath(modal.methodName);
transaction.setResponseCode(200);
chuckUtil.create(transaction);
```