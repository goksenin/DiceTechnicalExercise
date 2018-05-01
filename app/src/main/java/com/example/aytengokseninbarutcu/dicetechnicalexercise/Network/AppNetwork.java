package com.example.aytengokseninbarutcu.dicetechnicalexercise.Network;


import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.BaseResponse;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.GetDetailsReponse;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.GetTagsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by goksenin.barutcu on 07/11/2017.
 */

public interface AppNetwork {

    @GET("tag")
    Call<GetTagsResponse> getAllTags();

    @GET("tag/{name}")
    Call<GetDetailsReponse> getDetailByTag(@Path("name") String name, @Query("page") int pageNumber);


}
