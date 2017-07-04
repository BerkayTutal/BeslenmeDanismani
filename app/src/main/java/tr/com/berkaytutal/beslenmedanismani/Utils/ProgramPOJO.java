package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by berka on 21.05.2017.
 */

public class ProgramPOJO implements Serializable {
    private String difficulty;
    private byte[] programPhoto;
    private String programSpec;
    private String programTitle;
    private String programDescription;
    private int programID;
    private int trainerID;
    private boolean isPublished;

    private String trainerName;
    private String trainerSurname;

    private float rating;
    private int commentCount;



    private ArrayList<ExercisePOJO> exercisez = null;

    public ProgramPOJO(String difficulty, byte[] programPhoto, String programSpec, String programTitle, String programDescription, int programID, int trainerID, String trainerName, String trainerSurname, float rating, int commentCount) {
        this.difficulty = difficulty;
        this.programPhoto = programPhoto;
        this.programSpec = programSpec;
        this.programTitle = programTitle;
        this.programID = programID;
        this.trainerID = trainerID;

        this.trainerName = trainerName;
        this.trainerSurname = trainerSurname;
        this.programDescription = programDescription;
        this.rating = rating;
        this.commentCount = commentCount;
    }

    public ProgramPOJO(String difficulty, byte[] programPhoto, String programSpec, String programTitle, String programDescription, int programID, int trainerID, String trainerName, String trainerSurname, float rating, int commentCount, ArrayList<ExercisePOJO> exercisez) {
        this(difficulty, programPhoto, programSpec, programTitle, programDescription, programID, trainerID, trainerName, trainerSurname,  rating,  commentCount);
        this.exercisez = exercisez;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public ArrayList<ExercisePOJO> getExercisez() {
        return exercisez;
    }

    public void setExercisez(ArrayList<ExercisePOJO> exercisez) {
        this.exercisez = exercisez;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Bitmap getProgramPhoto() {
        return BitmapFactory.decodeByteArray(programPhoto, 0, programPhoto.length);
    }

    public void setProgramPhoto(byte[] programPhoto) {
        this.programPhoto = programPhoto;
    }

    public String getProgramSpec() {
        return programSpec;
    }

    public void setProgramSpec(String programSpec) {
        this.programSpec = programSpec;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }


    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerSurname() {
        return trainerSurname;
    }

    public void setTrainerSurname(String trainerSurname) {
        this.trainerSurname = trainerSurname;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public ExercisePOJO getExerciseByOrderNumber(int order) {

        for (ExercisePOJO exercise : exercisez) {
            if (exercise.getOrder() == order) {
                return exercise;
            }
        }
        return null;
    }
}

