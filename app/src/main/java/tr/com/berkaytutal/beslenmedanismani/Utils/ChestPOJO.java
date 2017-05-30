package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;
import android.provider.MediaStore;

public class ChestPOJO extends ExercisePOJO{
    private int agirlik;
    private int setSayisi;
    private int tekrarSayisi;

    public ChestPOJO(String description, String exerciseType, int exercises_ID, String name, int orderExercise, Bitmap photo1, Bitmap photo2, int restTime, String title, MediaStore.Video video, int agirlik, int setSayisi, int tekrarSayisi) {
        super(description, exerciseType, exercises_ID, name, orderExercise, photo1, photo2, restTime, title, video);
        this.agirlik = agirlik;
        this.setSayisi = setSayisi;
        this.tekrarSayisi = tekrarSayisi;
    }

    public int getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(int agirlik) {
        this.agirlik = agirlik;
    }

    public int getSetSayisi() {
        return setSayisi;
    }

    public void setSetSayisi(int setSayisi) {
        this.setSayisi = setSayisi;
    }

    public int getTekrarSayisi() {
        return tekrarSayisi;
    }

    public void setTekrarSayisi(int tekrarSayisi) {
        this.tekrarSayisi = tekrarSayisi;
    }
}
