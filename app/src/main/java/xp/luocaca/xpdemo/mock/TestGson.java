package xp.luocaca.xpdemo.mock;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;


public class TestGson {


    private static final String TAG = "TestGson";

    public static void main(String... args) throws Exception {


        淘宝接口模拟(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });

    }


    public static OkHttpRequestBuilder setScheme(Map<String, String> params) {

        if (params.get(":method").trim().equals("POST")) {
            if (params.get("body") != null) {
                if (params.get("Content-Type") != null) {
                    return OkHttpUtils.postString().mediaType(MediaType.parse(params.get("Content-Type").trim())).content(params.get("body"));
                } else if (params.get("content-type") != null) {
                    return OkHttpUtils.postString().mediaType(MediaType.parse(params.get("content-type").trim())).content(params.get("body"));

                } else {
                    return OkHttpUtils.postString().content(params.get("body"));
                }
            } else {
                return OkHttpUtils.post();
            }
        } else {
            return OkHttpUtils.get();
        }


    }


    public static Map<String, String> saveRemove(Map<String, String> map, String... tas) {
        for (String ta : tas) {
            map.remove(ta);
        }
        return map;
    }

    public static void 淘宝接口模拟(StringCallback callback) {
//        OkHttpClient client = new OkHttpClient();


        ;
//                .mediaType(MediaType.parse("multipart/form-data"));
        ;

//        String decod = "/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1569410448225&sign=6cc9042b8718ac7c318612b187df6dcb&api=mtop.order.queryboughtlist&v=4.0&ttid=%23%23h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data=%7B%22spm%22%3A%22a215s.7406091.toolbar.i2%22%2C%22page%22%3A1%2C%22tabCode%22%3A%22all%22%2C%22appVersion%22%3A%221.0%22%2C%22appName%22%3A%22tborder%22%7D";

        setMyClinet(OkHttpUtils.getInstance());

        Map<String, String> map = getCookiesString();

        setScheme(map)
//                .url("https://h5api.m.taobao.com/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1569410448225&sign=6cc9042b8718ac7c318612b187df6dcb&api=mtop.order.queryboughtlist&v=4.0&ttid=##h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data={\"spm\":\"a215s.7406091.toolbar.i2\",\"page\":1,\"tabCode\":\"all\",\"appVersion\":\"1.0\",\"appName\":\"tborder\"}")
                .url(getUrlByMap(map))


//                .addHeader("User-Agent", getCookiesString().get("user-agent"))
//                .addHeader("Accept", getCookiesString().get("accept"))
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "ec793c51-1509-4b85-9716-eb78e3c4ae2b,aa653df4-c081-4333-85f5-b67543476464")
////                .addHeader("Host", getCookiesString().get("Host"))
//                .addHeader("Cookie", getCookiesString().get("cookie"))
////                .addHeader("Connection", getCookiesString().get("Connection"))
////                .addHeader("cache-control", getCookiesString().get("cache-control"))
//                .addHeader(":authority", getCookiesString().get(":authority"))
//                .addHeader(":path", getCookiesString().get(":path"))
//                .addHeader(":method", getCookiesString().get(":method"))
//                .addHeader(":scheme", getCookiesString().get(":scheme"))
//                .addHeader("referer", getCookiesString().get("referer"))
                .headers(saveRemove(map, "body"))
//                .addHeader("Content-Type", "json")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        System.out.println("----------------" + e.getMessage());

                        callback.onError(call, e, id);

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        callback.onResponse(response, id);


                        try {
                            System.out.println("----------------" + response);
                        } catch (Exception e) {
                            System.out.println("----------------\n" + response);
                        }


//                        JdEntity jdEntity = GsonUtil.getGson().fromJson(response, JdEntity.class);


//                        System.out.println(jdEntity.toString());


                    }
                });
        ;

//        Response response = client.newCall(request).execute();
    }


    /**
     * 判断是否是json结构
     */
    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    private static String getUrlByMap(Map<String, String> cookiesString) {
        StringBuffer url = new StringBuffer();
        url.append(cookiesString.get(":scheme"));
        url.append("://");
        url.append(cookiesString.get(":authority").trim());
        url.append(cookiesString.get(":path").trim());
        return url.toString();
    }


    public static Map<String, String> getCookiesString() {


        String string = "POST /fvp-qp/security/qp.do HTTP/1.1\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 1365\n" +
                "Host: nxt.nongxinyin.com\n" +
                "Connection: Keep-Alive\n" +
                "Accept-Encoding: gzip\n" +
                "User-Agent: okhttp/3.2.0\n" +
                "\n" +
                "reqJson=%7B%22currentPage%22%3A%221%22%2C%22date%22%3A%222019-10-27%22%2C%22loginId%22%3A%22hZ1YXMi%2FpmXAuVO4V7CHVw%5Cu003d%5Cu003d%5Cn%22%2C%22pageSize%22%3A%2215%22%2C%22phoneNum%22%3A%22hZ1YXMi%2FpmXAuVO4V7CHVw%5Cu003d%5Cu003d%5Cn%22%2C%22shopId%22%3A%2225846863%22%2C%22IMEI%22%3A%22864577043550898%22%2C%22IMSI%22%3A%22460015565038415%22%2C%22IP%22%3A%22192.168.0.9%22%2C%22MAC%22%3A%2202%3A00%3A00%3A00%3A00%3A00%22%2C%22appOs%22%3A%22%22%2C%22appSign%22%3A%22NGPAXdQEtZGGbdY%2BubIJIaW1dqdQWPqFqamkX0%2Byf0BvbE8roMOnfl9NIS0ewOFM%5Cn%22%2C%22appType%22%3A%22Android%22%2C%22appVersion%22%3A%222.1.9%22%2C%22deviceSN%22%3A%22f5e3fd70ca893b0c%22%2C%22deviceType%22%3A%22qcom%22%2C%22funCode%22%3A%2210008%22%2C%22mobKey%22%3A%22aVi2IhC2VMTivaF5xMFJg%2B5NZ1Pyd7eN%2F2FjhystoW%2FnRP5aFijpZLRh0BIynq4g%2FQhNGnwFOEnH%5Cn2UjIqW%2FPFXd9XTz91IrEeU7Ke8GXUt5qziMzrhjTnzoHVYhO4hgIjNLYiEgmaRyh7VaT21jfYOLN%5Cn60v98Wl9U%2FJUnWaTbmvGH81DnHzMsIbz66PpN6jSzgCnHSfj7Nxedqfkzpsa%2B63XcrVHrKAlXEQA%5Cn%2BHmnxM4IMEA4qSugcUy98rEyJizWW7jBocI99glN6L88%2FnDxPsWIiTMSpXROqI9W71RltQqh1Cct%5Cnvx4I3MNw%2BqgDbWgEdpDXK87lJZ3rM%2BBJFXTP6g%5Cu003d%5Cu003d%5Cn%22%2C%22orgId%22%3A%22007434%22%2C%22pid%22%3A%22999994%22%2C%22security%22%3A%22security%22%2C%22seq%22%3A%2220191027215031000010%22%2C%22sign%22%3A%22QnEFiOjC8FlPGqmMqcK1Wr35kUlLmpK9ny6RhoSBIrsPf0GxqQ13sw%5Cu003d%5Cu003d%5Cn%22%7D";
        Map<String, String> map = new HashMap<>();


        try {


            File file = new File("C:\\Users\\Administrator\\Desktop\\cookies.txt");


            System.out.println(file.exists());

//            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] b = string.getBytes();
            ByteArrayInputStream textArrayInputStream = new ByteArrayInputStream(b);

            InputStreamReader inputreader
                    = new InputStreamReader(textArrayInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputreader);


            byte[] bytes = new byte[2048];
            int lenth = -1;
            String lineString = "";

            StringBuffer stringBuffer = new StringBuffer();

            String cookie = "";

            boolean isBody = false;
            while ((lineString = bufferedReader.readLine()) != null) {
//            lenth = fileInputStream.read(bytes);


                if (lineString.startsWith("cookie")) {
                    cookie = cookie + lineString.replaceFirst("cookie: ", "") + ";";
                    continue;
                }
                if (lineString.startsWith("Cookie")) {
                    cookie = cookie + lineString.replaceFirst("Cookie: ", "") + ";";
                    continue;
                }

                if (lineString.startsWith("accept-encoding") || lineString.startsWith("Accept-Encoding")) {
                    System.out.println("accept-encoding--不要");
                    continue;
                }
                if (lineString.equals("")) {
                    System.out.println("next line is body");
                    isBody = true;
                    continue;

                }


                if (isBody) {
                    map.put("body", lineString.trim());
                    continue;
                }
                if (!lineString.contains(":")) {
                    lineString = lineString.replaceAll(" ", ":");
                    map.put(":method", lineString.split(":")[0].trim());
                    map.put(":path", lineString.split(":")[1].trim());

                    continue;
                }


                try {
                    stringBuffer.append(lineString);

                    if (lineString.startsWith(":")) {
                        lineString = lineString.replaceFirst(":", "");

                        if (lineString.split(lineString).length > 2) {
                            String value = "";

                            for (int i = 2; i < lineString.split(lineString).length; i++) {
                                value = value + lineString.split(lineString)[i];
                            }
                            map.put(":" + lineString.split(":")[0], lineString.split(":")[1].trim());
                        }
                        map.put(":" + lineString.split(":")[0], lineString.split(":")[1].trim());

                    } else {


                        if (lineString.split(":").length > 2) {


                            String value = "";

                            for (int i = 1; i < lineString.split(":").length; i++) {

                                if (value.trim().equals("https")) {
                                    value += ":";
                                } else if (value.trim().equals("http")) {
                                    value += ":";
                                }

                                value = value + lineString.split(":")[i];
                            }
                            map.put(lineString.split(":")[0], value.trim());

//                            map.put(lineString.split(":")[0], lineString.split(":")[1]);

                        } else {
                            map.put(lineString.split(":")[0], lineString.split(":")[1].trim());
                        }


                    }


                    System.out.println("getCookiesString: " + lineString);
                    System.out.println("getCookiesString: " + map);


                    stringBuffer.append(";");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }

            }

            System.out.println(stringBuffer.toString());
            map.put("cookie", cookie.trim());

            if (map.get("Host") != null) {
                map.put(":authority", map.get("Host").trim());
            }

            map.put(":scheme", getSchecmeHttpOrHttps(map));


            String result = stringBuffer.toString();

//            result = result.replaceAll("cookie: ", "");


            System.out.println(result);

            return map;
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return map;

    }

    private static String getSchecmeHttpOrHttps(Map<String, String> map) {


        if (map.get("Origin") != null && map.get("Origin").contains("https")) {
            return "https";
        }
        if (map.get("Referer") != null && map.get("Referer").contains("https")) {
            return "https";
        }
        if (map.get("Origin") != null && map.get("Origin").contains("http://")) {
            return "http";
        }
        if (map.get("Referer") != null && map.get("Referer").contains("http://")) {
            return "http";
        }
        return "https";

    }


    /**
     * OkHttpUtils
     * {
     * public static final long DEFAULT_MILLISECONDS = 10_000L;
     * private volatile static OkHttpUtils mInstance;
     * private OkHttpClient mOkHttpClient;
     *
     * @param instance
     */
    public static void setMyClinet(OkHttpUtils instance) {
        SSLSocketFactory sslSocketFactory = null;

        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new X509TrustManager[]{trustManager}, null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }


        try {
            Field field = instance.getClass().getDeclaredField("mOkHttpClient");


            field.setAccessible(true);

            OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient().newBuilder()

                    .sslSocketFactory(sslSocketFactory, trustManager)
//                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                    .build();
//            client.sslSocketFactory(sslSocketFactory, trustManager);
//            OkHttpClient OkHttpClient = (okhttp3.OkHttpClient) field.get(instance);
//设置代理,需要替换
            field.set(instance, client);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //mOkHttpClient


    }

}