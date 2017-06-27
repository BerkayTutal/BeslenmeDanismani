package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MUSTAFA on 27.05.2017.
 */

public class UserDataPOJO implements Serializable {
    static final long serialVersionUID = 1453L;
    private int user_ID;
    private String name;
    private String surname;
    private String email;
    private String sex;
    private String birthday;
    private byte[] photo;


    private boolean isTrainer = false;
    private boolean isPrivate;

    private ArrayList<BodyRatioPOJO> bodyRatios;

    private ArrayList<ProgramPOJO> myPrograms;

    public UserDataPOJO(int user_ID, String name, String surname, String email, String sex, String birthday, byte[] photo, boolean isPrivate, ArrayList<ProgramPOJO> myPrograms) {

        this.user_ID = user_ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.photo = photo;
        this.isPrivate = isPrivate;

        this.myPrograms = myPrograms;
    }

    public Bitmap getPhoto() {
        return BitmapFactory.decodeByteArray(photo, 0, photo.length);
    }

    public byte[] getPhotoByte() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public ArrayList<BodyRatioPOJO> getBodyRatios() {
        return bodyRatios;
    }

    public void setBodyRatios(ArrayList<BodyRatioPOJO> bodyRatios) {
        this.bodyRatios = bodyRatios;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public ArrayList<ProgramPOJO> getMyPrograms() {
        return myPrograms;
    }

    public void setMyPrograms(ArrayList<ProgramPOJO> myPrograms) {
        this.myPrograms = myPrograms;
    }

    public void setProgramByID(ProgramPOJO programPOJO) {

        for (int i = 0; i < myPrograms.size(); i++) {
            if (myPrograms.get(i).getProgramID() == programPOJO.getProgramID()) {
                myPrograms.set(i, programPOJO);
                return;
            }
        }
    }

    public ProgramPOJO getProgramByID(int programID) {

        for (int i = 0; i < myPrograms.size(); i++) {
            if (myPrograms.get(i).getProgramID() == programID) {
                return myPrograms.get(i);
            }
        }
        return null;
    }

    public boolean insertProgram(ProgramPOJO programPOJO) {
        for (ProgramPOJO program : myPrograms) {
            if (program.getProgramID() == programPOJO.getProgramID()) {
                return false;
            }
        }
        myPrograms.add(programPOJO);
        return true;
    }

    public boolean deleteProgram(ProgramPOJO programPOJO) {
        for (int i = 0; i < myPrograms.size(); i++) {
            if (myPrograms.get(i).getProgramID() == programPOJO.getProgramID()) {
                myPrograms.remove(i);
                return true;
            }
        }
        return false;
    }

    public void deleteProgramsExcept(ArrayList<ProgramPOJO> programs) {
        ArrayList<Integer> programIDs = new ArrayList<>();
        for (int i = 0; i < programs.size(); i++) {
            programIDs.add(programs.get(i).getProgramID());
        }
        for (int i = 0; i < myPrograms.size(); i++) {
            if (!programIDs.contains(myPrograms.get(i).getProgramID())) {
                myPrograms.remove(i);

            }
        }

    }

    public boolean deleteProgramExercisez(ProgramPOJO programPOJO) {
        for (int i = 0; i < myPrograms.size(); i++) {
            if (myPrograms.get(i).getProgramID() == programPOJO.getProgramID()) {
                myPrograms.get(i).setExercisez(null);
                return true;
            }
        }
        return false;
    }

    public boolean isTrainer() {
        return isTrainer;
    }

    public void setTrainer(boolean trainer) {
        isTrainer = trainer;
    }
}
