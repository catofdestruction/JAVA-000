import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * OkHttpClientDemo
 *
 * @author wangxinyu
 * @date 2020/10/28
 */
@Slf4j
public class OkHttpClientDemo {

    public static final String TEST_GET_URL = "http://www.baidu.com";
    public static final String TEST_GET_UNKNOWN_URL = "http://www.baidu.com" + "1";
    public static final String TEST_GET_INVALID_URL = "1" + "http://www.baidu.com";

    public static void main(String[] args) {
        syncGet(TEST_GET_URL);
        log.warn("==========================================================================================\n");
        syncGet(TEST_GET_UNKNOWN_URL);
        log.warn("==========================================================================================\n");
        syncGet(TEST_GET_INVALID_URL);
        log.warn("==========================================================================================\n");

        // 问题: 抛出异常后无法继续执行下面的代码 暂时没有想到很好的办法 ：)

        asyncGet(TEST_GET_URL);
        log.warn("==========================================================================================\n");
        asyncGet(TEST_GET_UNKNOWN_URL);
        log.warn("==========================================================================================\n");
        asyncGet(TEST_GET_INVALID_URL);
        log.warn("==========================================================================================\n");
    }

    public static void syncGet(String url) {
        log.warn("===> start sync get for url: {}", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                log.info("===> async get(success)\nresponse:\n{}", bodyString(response.body()));
            } else {
                log.info("===> async get(failure)\nerror: {}({})", response.message(), response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void asyncGet(String url) {
        log.warn("===> start async get for url: {}", url);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                // onResponse from child thread
                log.info("===> async get(success)\nresponse: {}", bodyString(response.body()));
            }

            public void onFailure(Call call, IOException e) {
                // onFailure from child thread
                log.info("===> async get(failure)\nerror: {}", e.getMessage());
            }
        });
        try {
            Thread.currentThread().join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String bodyString(ResponseBody responseBody) {
        try {
            return responseBody == null ? null : responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
