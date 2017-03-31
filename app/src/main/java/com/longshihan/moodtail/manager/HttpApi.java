package com.longshihan.moodtail.manager;


import com.longshihan.moodtail.model.bean.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author longshihan
 * @time 2017/3/23 13:22
 * @des
 */

public interface HttpApi {
    String HOST = "http://testapi.o2o.zhaioto.com/buyer/v1/";

    @GET("User.login")
    Observable<String> getLogin(@Query("name") String name, @Query("pw") String pw);

    @POST("msg.status")
    Observable<HttpResult> getStatus();



}
