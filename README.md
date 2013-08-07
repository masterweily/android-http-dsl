android-http-dsl
================

light weight dsl to make simple http requests easier with android

<h2>Install and configuration</h2>
<ul>
<li>
  Add library files to your project
</li>
<li>
  Add permission for internet access in the project manifest file:
</li>

</ul>
```xml
<uses-permission android:name="android.permission.INTERNET" />
```



<h2>Examples</h2>

<h3>
  Sending Simple Synchronous Post / Get Request
</h3>

```java

String response = new HttpDsl.Request("some-url.com").get().response().toString(); // get request

String response = new HttpDsl.Request("some-url.com").post().response().toString(); // post request
```

<h3>
  Reques With Parameters
</h3>

```java
String response = String response = new HttpDsl.Request("some-url.com").param("id","32").get().response().toString();
```

<h3>
  Asynchronous Requests
</h3>

```java
new HttpDsl.Request("some-url.com").post(new HttpDsl.Request.Listener() {
  @Override
  public void onSuccess(HttpDsl.Response response) {
    Log.d("http-dsl", response.toString());
  }
});
```
