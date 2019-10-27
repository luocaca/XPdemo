package xp.luocaca.xpdemo;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Proxy;
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


        淘宝接口模拟();

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

    private static void 淘宝接口模拟() {
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
                    }

                    @Override
                    public void onResponse(String response, int id) {

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


        String string = ":method: POST\n" +
                ":path: /zz/transfer/getsearchword\n" +
                ":authority: app.zhuanzhuan.com\n" +
                ":scheme: https\n" +
                "cookie: t=15;sts=1569497335899;tk=5E73C295A0C1BC14D2C30A9F88469962;imei=352284043546120;v=6.11.1;channelid=market_1274;lat=33.999542;lon=106.567571;osv=22;model=Nexus+6;brand=Android;seq=117;uid=41978307012752000;PPU=\"TT=5fb1b816ad00d5dea8402ae2850e3e732e694d39&UID=41978307012752000&SF=ZHUANZHUAN&SCT=1569550077447&V=1&ET=1572138477447\"; Version=1; Domain=zhuanzhuan.com; Max-Age=2592000; Expires=Sun, 27-Oct-2019 01:23:07 GMT; Path=/;\n" +
                "content-type: application/x-www-form-urlencoded; charset=UTF-8\n" +
                "content-length: 0\n" +
                "accept-encoding: gzip\n" +
                "user-agent: Zhuan/6.11.1 (6011001) Dalvik/2.1.0 (Linux; U; Android 5.1.1; Nexus 6 Build/LYZ28N)\n" +
                "\n";

        Map<String, String> map = new HashMap<>();


        try {


            File file = new File("C:\\Users\\Administrator\\Desktop\\cookies.txt");


            System.out.println(file.exists());

            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] b = string.getBytes();
//            new ByteArrayInputStream(string.getBytes())

            InputStreamReader inputreader
                    = new InputStreamReader(fileInputStream);
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
                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888))).build();
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