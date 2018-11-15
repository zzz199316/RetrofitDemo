package com.plgf.myretrofit.network.help;

import com.google.gson.Gson;
import com.plgf.myretrofit.network.DataService;
import com.plgf.myretrofit.network.base.BaseResult;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;


public abstract class DataServiceCallBack<T>{

    /**
     * 获取API接口
     *
     * @param service
     * @return
     */
    public abstract Observable<T> onCall(DataService service, RequestBody requestBody);

    /**
     * 数据成功返回调用
     *
     * @param result
     */
    protected abstract void onDataResult(T result);



    /**
     * 请求完成，成功失败都会调用，可以实现此方法用来隐藏loading框等UI操作
     */
    protected void onComplete() {

    }

    /**
     * 服务器自定义错误
     *
     * @param code
     * @param message
     */
    protected void onError(String code, String message) {
    }

}
