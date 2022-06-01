package com.example.mygame.OnlineMode;

import com.example.mygame.OnlineMode.ForServer.Format;
import com.example.mygame.OnlineMode.ForServer.Reply;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GameService {

    final String address = "http://912939-cf66069.tmweb.ru/";

    @GET("theking")
    Call<Format> getIDOfRoom();

    @GET("theking/room")
    Call<Format> getUserCode(@Query("id") String idOfRoom);

    @GET("theking/room")
    Call<Format> getTimer(@Query("id") String idOfRoom, @Query("user-code") int userCode);

    @GET("theking/start")
    Call<Reply> getReply(@Query("id") String idOfRoom, @Query("user-code") int userCode);

    @FormUrlEncoded
    @POST("theking/data")
    Call<Format> sendData(@Query("id") String idOfRoom, @Query("user-code") int userCode, @Field("json") String json);

    @GET("theking/next")
    Call<Format> getContinue(@Query("id") String idOfRoom, @Query("user-code") int userCode);
}
