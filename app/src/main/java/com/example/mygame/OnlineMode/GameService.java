package com.example.mygame.OnlineMode;

import androidx.core.view.ViewCompat;

import com.example.mygame.OnlineMode.Classes.Format;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {

    final String address = "http://912939-cf66069.tmweb.ru/";

    @GET("theking")
    Call<Format> getIDOfRoom();

    @GET("theking/room")
    Call<Format> getUserCode(@Query("id") String idOfRoom);

    @GET("theking/room")
    Call<Format> setUserName(@Query("id") String idOfRoom, @Query("user-code") int userCode, @Query("user-name") String userName);

    @GET("theking/room")
    Call<Format> getStartStatus(@Query("id") String idOfRoom, @Query("user-code") int userCode);

    @GET("theking/start")
    Call<Format> getInitialStatus(@Query("id") String idOfRoom, @Query("user-code") int userCode);

    @FormUrlEncoded
    @POST("theking/data")
    Call<Format> sendData(@Query("id") String idOfRoom, @Query("user-code") int userCode, @Field("json") String json);

    @GET("theking/next")
    Call<Format> getContinue(@Query("id") String idOfRoom, @Query("user-code") int userCode);
}
