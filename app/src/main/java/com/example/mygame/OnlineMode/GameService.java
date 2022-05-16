package com.example.mygame.OnlineMode;

import androidx.core.view.ViewCompat;

import com.example.mygame.OnlineMode.Classes.Format;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {
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
}
