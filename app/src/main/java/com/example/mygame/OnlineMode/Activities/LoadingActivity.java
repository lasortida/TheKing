package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygame.MainActivity;
import com.example.mygame.OnlineMode.Classes.StorageOnline;
import com.example.mygame.OnlineMode.ForServer.Format;
import com.example.mygame.OnlineMode.ForServer.Game;
import com.example.mygame.OnlineMode.ForServer.Reply;
import com.example.mygame.OnlineMode.GameService;
import com.example.mygame.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadingActivity extends AppCompatActivity {

    String idOfRoom;
    int userCode;
    boolean start;
    String personName;
    boolean block = false;

    int flag = 0;
    Retrofit retrofit;
    GameService service;
    TextView view;
    TextView timer;
    TextView advice;
    int seconds = 10000;
    int value;
    int time;

    String[] advices = {
            "Совет: Будьте внимательны, когда обрабатываете обращения! От этого зависит положение нашей страны!",
            "Правило: Сразу после создания альянса, вы не можете отправлять приглашения на вступление, мировое сообщество должно утвердить ваш альянс!",
            "Правило: Устраивать обмен можно только 1 раз в игровую неделю!",
            "Совет: Сделайте хорошее описание вашему альянсу, чтобы в него хотелось вступить!",
            "Совет: Старайтесь держать уровни (Деньги, Армия, Экономика, Промышленность, Еда) в средней позиции! (Не много и не мало)",
            "Совет: Старайтесь нажимать кнопку завершения недели до исхода времени, иначе ваши жители будут вами недовольны!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Bundle args = getIntent().getExtras();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        personName = (String) args.get("userName");
        view = findViewById(R.id.textViewTitle);
        timer = findViewById(R.id.textViewTimer);
        advice = findViewById(R.id.textViewAdvice);
        view.setText("Поиск сервера");
        value = (int) (Math.random() * advices.length);
        advice.setText(advices[value]);

        new CountDownTimer(seconds, 1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                int index = (int) (Math.random() * advices.length);
                while (index == value){
                    index = (int) (Math.random() * advices.length);
                }
                value = index;
                advice.setText(advices[index]);
                start();
            }
        }.start();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(GameService.address)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GameService.class);

        thread.start();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            idOfRoom = getRoomID();
            while (idOfRoom == null){
                idOfRoom = getRoomID();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setText("Комната найдена!");
                }
            });
            userCode = getUserCode(idOfRoom);
            while (userCode == -1){
                userCode = getUserCode(idOfRoom);
            }
            Log.d("user", String.valueOf(userCode));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setText("Ожидание игроков!");
                }
            });
            time = getTimeReminder(idOfRoom, userCode);
            while (time == -1){
                Log.d("user", String.valueOf(time));
                time = getTimeReminder(idOfRoom, userCode);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("user", String.valueOf(time));
            while (time > 0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.setVisibility(View.VISIBLE);
                        timer.setText("Осталось: " + time);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time = getTimeReminder(idOfRoom, userCode);
            }
            if (time == 0){
                Reply reply = getReply(idOfRoom, userCode);
                Game game = new Game(reply);
                StringBuilder builder = new StringBuilder();
                builder.append("Добро пожаловать! \n\n");
                builder.append("Заботьтесь о процветании вашей страны и её жителей! Отстаивайте свои интересы, объединяйтесь в Альянсы с другими странами и поддерживайте друг друга! \n\n");
                builder.append("Весь процесс игры разделён на игровые недели! Одна игровая неделя - 1 минута и 30 секунд реального времени!\n\n");
                builder.append("На карте мира можно рассмотреть свою страну, а также обстановку в целом!");
                game.news = builder.toString();
                game.storage = new StorageOnline();
                game.setWeek();
                startActivity(new Intent(LoadingActivity.this, MapActivityOnline.class).putExtra("GAME", game));
            }
        }
    };

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Назад идти некуда!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public String getRoomID(){
        try{
            Call<Format> call = service.getIDOfRoom();
            Response<Format> r = call.execute();
            Format f = r.body();
            return f.id;
        } catch (IOException e){ }
        return null;
    }

    public int getUserCode(String idOfRoom){
        try{
            Call<Format> call = service.getUserCode(idOfRoom);
            Response<Format> r = call.execute();
            Format f = r.body();
            if (!f.error){
                return f.userCode;
            }
        } catch (IOException e){ }
        return -1;
    }

    public int getTimeReminder(String idOfRoom, int userCode){
        try{
            Call<Format> call = service.getTimer(idOfRoom, userCode);
            Response<Format> r = call.execute();
            Format f = r.body();
            if (!f.error){
                if (f.isTimerStarted){
                    return f.time;
                }
            }
        } catch (IOException e){
            Log.d("error", e.getLocalizedMessage());
        }
        return -1;
    }

    public Reply getReply(String idOfRoom, int userCode) {
        try {
            Call<Reply> call = service.getReply(idOfRoom, userCode);
            Response<Reply> r = call.execute();
            Reply reply = r.body();
            if (reply.isGameStarted && !reply.error) {
                return reply;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}