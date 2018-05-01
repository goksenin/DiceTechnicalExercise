package com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {

        @SerializedName("success")
        public boolean success;
        @SerializedName("errors")
        public List<String> errors;
    }