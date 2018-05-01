package com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aytengokseninbarutcu on 1.05.2018.
 */

public class GetTagsResponse extends BaseResponse {

    @SerializedName("count")
    public int count;
    @SerializedName("total")
    public int total;
    @SerializedName("_embedded")
    public List<String> embeded;
    @SerializedName("_links")
    public Link links;


    private class Link {
        @SerializedName("self")
        public Self self;
    }

    private class Self {
        @SerializedName("href")
        public String href;
    }
}
