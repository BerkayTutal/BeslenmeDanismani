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

    private String trainerName;
    private String trainerSurname;

    public ProgramPOJO(String difficulty, Bitmap programPhoto, String programSpec, String programTitle, int programID, int trainerID, String trainerName, String trainerSurname) {
        this.difficulty = difficulty;
        this.programPhoto = programPhoto;
        this.programSpec = programSpec;
        this.programTitle = programTitle;
        this.programID = programID;
        this.trainerID = trainerID;

        this.trainerName = trainerName;
        this.trainerSurname = trainerSurname;
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
}
