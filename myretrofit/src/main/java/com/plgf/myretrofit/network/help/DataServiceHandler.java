package com.plgf.myretrofit.network.help;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plgf.myretrofit.network.DataService;
import com.plgf.myretrofit.network.ServiceClient;
import com.plgf.myretrofit.network.base.BaseRequestData;
import com.plgf.myretrofit.network.base.BaseResult;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DataServiceHandler {

    private static DataServiceHandler instance;

    private final Context context;
    private DataService service;

    private DataServiceHandler(Context context) {
        this.context = context;

    }

    public static void init(Context context) {
        if (instance == null)
            instance = new DataServiceHandler(context);
    }

    public static DataServiceHandler Instance() {

        if (instance == null)
            throw new RuntimeException("请先初始化DataServiceHandler！");
        return instance;
    }


    /**
     * 处理请求
     *
     * @param callback 回调
     * @param <T>
     */
    public <T> void handle(BaseRequestData baseRequestData, final DataServiceCallBack<T> callback) {
        service = ServiceClient.getSingletonInstance(context).getRetrofit().create(DataService.class);
        Gson gson = new GsonBuilder().serializeNulls().create();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(baseRequestData));
        final Observable<T> call = callback.onCall(service, requestBody);
        // call.enqueue(callback);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("500", e.toString());
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onDataResult(t);
                    }
                });
    }

    /**
     * 处理请求
     *
     * @param callback 回调
     * @param <T>
     */
    public <T> void handle(DataServiceCallBack<T> callback) {
        handle(null, callback);


    }

    public <T> void handle(String baseUrl, String paramsJson, final DataServiceCallBack<T> callback) {
        service = ServiceClient.geteRetrofitByBaseUrl(context, baseUrl).create(DataService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramsJson);
        Observable<T> call = callback.onCall(service, requestBody);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {
                        callback.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("500", e.toString());
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onDataResult(t);
                    }
                });

    }


}
