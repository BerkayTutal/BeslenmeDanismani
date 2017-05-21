package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;

/**
 * Created by berka on 21.05.2017.
 */

public class ProgramPOJO {
    private String difficulty;
    private Bitmap programPhoto;
    private String programSpec;
    private String programTitle;
    private int programID;
    private int trainerID;
    private int userID;

    public ProgramPOJO(String difficulty, Bitmap programPhoto, String programSpec, String programTitle, int programID, int trainerID, int userID) {
        this.difficulty = difficulty;
        this.programPhoto = programPhoto;
        this.programSpec = programSpec;
        this.programTitle = programTitle;
        this.programID = programID;
        this.trainerID = trainerID;
        this.userID = userID;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Bitmap getProgramPhoto() {
        return programPhoto;
    }

    public void setProgramPhoto(Bitmap programPhoto) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
