package com.example.mygame;

import android.widget.ImageView;

public class ImageWithParams {
    private ImageView imageView;
    private int state = 3;
    private int id;
    private int[][] ids = new int[][]{
            new int[]{
                    R.drawable.coin_zero, R.drawable.coin_first, R.drawable.coin_second, R.drawable.coin_third, R.drawable.coin_fourth, R.drawable.coin_fivth, R.drawable.coin_full
            },
            new int[]{
                    R.drawable.bomb_zero, R.drawable.bomb_first, R.drawable.bomb_second, R.drawable.bomb_third, R.drawable.bomb_fourth, R.drawable.bomb_fivth, R.drawable.bomb_full
            },
            new int[]{
                    R.drawable.tie_zero, R.drawable.tie_first, R.drawable.tie_second, R.drawable.tie_third, R.drawable.tie_fourth, R.drawable.tie_fivth, R.drawable.tie_full
            },
            new int[]{
                    R.drawable.anvil_zero, R.drawable.anvil_first, R.drawable.anvil_second, R.drawable.anvil_third, R.drawable.anvil_fourth, R.drawable.anvil_fivth, R.drawable.anvil_full
            },
            new int[]{
                    R.drawable.bread_zero, R.drawable.bread_first, R.drawable.bread_second, R.drawable.bread_third, R.drawable.bread_fourth, R.drawable.bread_fivth, R.drawable.bread_full
            }
    };

    public ImageWithParams(ImageView imageView, int id){
        this.imageView = imageView;
        this.id = id;
    }

    public void setNewImage(double level){
        if (level <= 0){
            imageView.setImageResource(ids[id][0]);
            state = 0;
        }
        if (level > 0 && level <= 0.17){
            imageView.setImageResource(ids[id][1]);
            state = 1;
        }
        if (level > 0.17 && level <= 0.34){
            imageView.setImageResource(ids[id][2]);
            state = 2;
        }
        if (level > 0.34 && level <= 0.51){
            imageView.setImageResource(ids[id][3]);
            state = 3;
        }
        if (level > 0.51 && level <= 0.68){
            imageView.setImageResource(ids[id][4]);
            state = 4;
        }
        if (level > 0.68 && level < 0.85){
            imageView.setImageResource(ids[id][5]);
            state = 5;
        }
        if (level >= 0.85){
            imageView.setImageResource(ids[id][6]);
            state = 6;
        }
    }

    public boolean isStateChange(double level){
        int newState = 0;
        if (level <= 0) {
            newState = 0;
        }
        if (level > 0 && level <= 0.17){
            newState = 1;
        }
        if (level > 0.17 && level <= 0.34){
            newState = 2;
        }
        if (level > 0.34 && level <= 0.51){
            newState = 3;
        }
        if (level > 0.51 && level <= 0.68){
            newState = 4;
        }
        if (level > 0.68 && level < 0.85){
            newState = 5;
        }
        if (level >= 0.85){
            newState = 6;
        }
        if (state == newState){
            return false;
        }
        else{
            return true;
        }
    }

    public void setVis(int number){
        imageView.setImageAlpha(number);
    }

    public ImageView getImageView(){
        return imageView;
    }
}
