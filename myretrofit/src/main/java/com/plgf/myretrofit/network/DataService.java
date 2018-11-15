package com.plgf.myretrofit.network;



import com.plgf.myretrofit.network.base.BaseResult;
import com.plgf.myretrofit.network.base.GetUserinfoParams;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;



public interface DataService {

//   @FormUrlEncoded
//   @POST("user/register")
//   Observable<BaseResult> register(@Field("mobilephone") String mobilephone,
//                                   @Field("password") String password, @Field("verifyCode") String verifyCode);


    @GET("user/getuserinfo")
    Observable<GetUserinfoParams> getuserinfo(@Query("account") String account);




//   @Headers({"X-Requested-With: XMLHttpRequest"})
//   @POST("/h5.php")
//   @Multipart
//   Observable<UpdateUserInfoResult> updatePortrait(@Part("api") RequestBody api, @Part("edatas") RequestBody edatas, @Part MultipartBody.Part file);
//
//
//   @Headers({"X-Requested-With: XMLHttpRequest"})
//   @POST("/h5.php")
//   @Multipart
//   Observable<FeedBackResult> feedBack(@Part("api") RequestBody api, @Part("edatas") RequestBody edatas
//           , @Part MultipartBody.Part file1, @Part MultipartBody.Part file2
//           , @Part MultipartBody.Part file3, @Part MultipartBody.Part file4);

}
