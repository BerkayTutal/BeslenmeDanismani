package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;
import android.provider.MediaStore;

public class ExercisePOJO {



    private String description;
    private String exerciseType;
    private int exercises_ID;
    private String name;
    private int orderExercise;
    private Bitmap photo1;
    private Bitmap photo2;
    private int restTime;
    private String title;
    private MediaStore.Video video;


    public ExercisePOJO(String description, String exerciseType, int exercises_ID, String name, int orderExercise, Bitmap photo1, Bitmap photo2, int restTime, String title, MediaStore.Video video) {
        this.description = description;
        this.exerciseType = exerciseType;
        this.exercises_ID = exercises_ID;
        this.name = name;
        this.orderExercise = orderExercise;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.restTime = restTime;
        this.title = title;
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getExercises_ID() {
        return exercises_ID;
    }

    public void setExercises_ID(int exercises_ID) {
        this.exercises_ID = exercises_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderExercise() {
        return orderExercise;
    }

    public void setOrderExercise(int orderExercise) {
        this.orderExercise = orderExercise;
    }

    public Bitmap getPhoto1() {
        return photo1;
    }

    public void setPhoto1(Bitmap photo1) {
        this.photo1 = photo1;
    }

    public Bitmap getPhoto2() {
        return photo2;
    }

    public void setPhoto2(Bitmap photo2) {
        this.photo2 = photo2;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaStore.Video getVideo() {
        return video;
    }

    public void setVideo(MediaStore.Video video) {
        this.video = video;
    }

}
