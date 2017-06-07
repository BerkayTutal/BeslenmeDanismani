package tr.com.berkaytutal.beslenmedanismani.Utils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MUSTAFA on 27.05.2017.
 */

public class UserDataPOJO implements Serializable {
    private int user_ID;
    private String name;
    private String surname;
    private String email;
    private String sex;
    private String birthday;
    private String tall;
    private String weight;
    private String muscleRate;
    private String fatRate;
    private String waterRate;
    private ArrayList<ProgramPOJO> myPrograms;

    public UserDataPOJO(int user_ID, String name, String surname, String email, String sex, String birthday, String tall,
                        String weight, String muscleRate, String fatRate, String waterRate, ArrayList<ProgramPOJO> myPrograms) {

        this.user_ID = user_ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.tall = tall;
        this.weight = weight;
        this.muscleRate = muscleRate;
        this.fatRate = fatRate;
        this.waterRate = waterRate;
        this.myPrograms = myPrograms;
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

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMuscleRate() {
        return muscleRate;
    }

    public void setMuscleRate(String muscleRate) {
        this.muscleRate = muscleRate;
    }

    public String getFatRate() {
        return fatRate;
    }

    public void setFatRate(String fatRate) {
        this.fatRate = fatRate;
    }

    public String getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(String waterRate) {
        this.waterRate = waterRate;
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
    public void deleteProgramsExcept(ArrayList<ProgramPOJO> programs){
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
}
