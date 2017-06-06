package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by berka on 6.06.2017.
 */

public class TekrarPOJO extends CircleTekrarAbsPOJO {

    private ArrayList<ExercisePOJO> exercisez;
    private ExercisePOJO ex;


    public TekrarPOJO(ArrayList<ExercisePOJO> exercisez) {
        this.exercisez = exercisez;
        ex=exercisez.get(0);

    }

    public ArrayList<ExercisePOJO> getExercisez() {
        return exercisez;
    }

    public void setExercisez(ArrayList<ExercisePOJO> exercisez) {
        this.exercisez = exercisez;
    }

    public String getName() {
        return ex.getName();
    }
    public String getTitle() {
        return ex.getTitle();
    }

    public int getID() {
        return ex.getExercises_ID();
    }
    public Bitmap getPhoto1(){
        return ex.getPhoto1();
    }

    public Bitmap getPhoto2(){
        return ex.getPhoto2();
    }



    public int getTekrarCount() {
        return exercisez.size();
    }


    @Override
    public boolean isCircle() {
        return false;
    }

    @Override
    public boolean isTekrar() {
        return true;
    }
}
