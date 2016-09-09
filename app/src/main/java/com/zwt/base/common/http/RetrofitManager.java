package com.zwt.base.common.http;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 * Created by laucherish on 16/3/15.
 */
public class RetrofitManager {


    private static OkHttpClient mOkHttpClient;
    public final ApiManagerService apiService;

    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> mInstanceManager = new SparseArray<>(
            HostType.TYPE_COUNT);


    public static RetrofitManager builder(int hostType) {
        RetrofitManager instance = mInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            mInstanceManager.put(hostType, instance);
            return instance;
        } else {
            return instance;
        }
    }


    private RetrofitManager(@HostType.HostTypeChecker int hostType) {
        initOkHttpClient();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getHost(hostType))
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiManagerService.class);
    }

    /**
     * 初始化okhttp
     */
    private void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build();
                }
            }
        }
    }

    /**
     * 初始化okhttp,增加cookie缓存
     */
    private void initOkHttpClientAddCookie() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .cookieJar(new CookieJar() {
                                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                                //Tip：這裡key必須是String
                                @Override
                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                                    cookieStore.put(url.host(), cookies);
                                }

                                @Override
                                public List<Cookie> loadForRequest(HttpUrl url) {
                                    List<Cookie> cookies = cookieStore.get(url.host());
                                    return cookies != null ? cookies : new ArrayList<Cookie>();
                                }
                            })
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build();
                }
            }
        }
    }
    // Define the interceptor, add authentication headers
    // 增加头部请求字段
   /* Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (!BaseApplication.getInstance().isLogin()) {
                return chain.proceed(originalRequest);
            } else {
                String token = BaseApplication.getInstance().getUserinfo().getToken();
                Request authorised = originalRequest.newBuilder()
                        .header("token", token)
                        .build();
                return chain.proceed(authorised);
            }
        }
    };*/

    /**
     * 获取对应的host
     * @param hostType host类型
     * @return host
     */
    private String getHost(int hostType) {
        switch (hostType) {
            case HostType.BASE_PATH:
                return ApiManagerService.MAIN_HOST;
            case HostType.BASE_LOCAL_PATH:
                return ApiManagerService.LOCAL_HOST;
        }
        return "";
    }
}
