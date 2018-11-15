package com.plgf.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.plgf.myretrofit.network.DataService;
import com.plgf.myretrofit.network.ServiceClient;
import com.plgf.myretrofit.network.base.BaseResult;
import com.plgf.myretrofit.network.base.GetUserinfoParams;
import com.plgf.myretrofit.network.help.DataServiceCallBack;
import com.plgf.myretrofit.network.help.DataServiceHandler;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DataService dataService = ServiceClient.getSingletonInstance(this).create(DataService.class);
//        dataService.getuserinfo("15755054169")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GetUserinfoParams>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                    @Override
//                    public void onNext(GetUserinfoParams baseResult) {
//                        Log.e("onDataResult", "onDataResult: "+baseResult.toString() );
//                    }
//                });


        DataServiceHandler.init(this);
        DataServiceHandler.Instance().handle(new DataServiceCallBack<GetUserinfoParams>() {
            @Override
            public Observable<GetUserinfoParams> onCall(DataService service, RequestBody requestBody) {


                return  service.getuserinfo("15755054169");
            }

            @Override
            protected void onDataResult(GetUserinfoParams result) {

                Log.e("onDataResult", "onDataResult: "+result.toString() );

            }

        });
    }
}
