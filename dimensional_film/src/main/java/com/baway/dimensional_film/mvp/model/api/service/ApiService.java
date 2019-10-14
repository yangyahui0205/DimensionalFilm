package com.baway.dimensional_film.mvp.model.api.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 *@Auther:Helen
 *@Date: 19.8.6
 *@Time: 21:10:14
 *@Description:
 * */public interface ApiService {
    //注册
    //接口地址：http://172.17.8.100/movieApi/user/v1/registerUser
    @FormUrlEncoded
    @POST("movieApi/user/v1/registerUser")
    public Observable<ResponseBody> registerUser(@Field("nickName") String nickName,
                                                 @Field("phone") String phone,
                                                 @Field("pwd") String pwd,
                                                 @Field("pwd2") String pwd2,
                                                 @Field("sex") int sex,
                                                 @Field("birthday") String birthday,
                                                 @Field("imei") String imei,
                                                 @Field("ua") String ua,
                                                 @Field("screenSize") String screenSize,
                                                 @Field("os") String os,
                                                 @Field("email") String email);

    //登陆
    //http://172.17.8.100/ movieApi/user/v1/login
    @FormUrlEncoded
    @POST("movieApi/user/v1/login")
    public Observable<ResponseBody> login(@Field("phone") String phone,
                                          @Field("pwd") String pwd);
}
