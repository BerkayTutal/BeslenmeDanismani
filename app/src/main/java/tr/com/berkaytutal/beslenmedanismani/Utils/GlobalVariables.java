package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by berka on 21.05.2017.
 */

public class GlobalVariables extends Application {

    private ArrayList<ProgramPOJO> allPrograms;
    private ArrayList<TrainerPOJO> allUsers;
    private UserDataPOJO userDataPOJO;

    public void setUserDataPOJO(UserDataPOJO userDataPOJO){
        this.userDataPOJO=userDataPOJO;
    }

    public UserDataPOJO getUserDataPOJO(){
        return  userDataPOJO;
    }

    public ArrayList<ProgramPOJO> getAllPrograms() {
        return allPrograms;
    }

    public void setAllPrograms(ArrayList<ProgramPOJO> allPrograms) {
        this.allPrograms = allPrograms;
    }

    public ArrayList<ProgramPOJO> getProgramsByTrainerID(int trainerID){
        ArrayList<ProgramPOJO> programs = new ArrayList<>();

        for (ProgramPOJO program : allPrograms) {
            if(program.getTrainerID() == trainerID){
                programs.add(program);
            }

        }
        return programs;


    }

    public ProgramPOJO getProgramByID(int programID){
        for (ProgramPOJO program: allPrograms) {

            if(program.getProgramID() == programID){
                return program;
            }
        }
        return null;
    }

    public ProgramPOJO getProgramByIndex(int index){
        return allPrograms.get(index);
    }

    public ArrayList<TrainerPOJO> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<TrainerPOJO> allUsers) {
        this.allUsers = allUsers;
    }

    public TrainerPOJO getUserByID(int userID){
        for (TrainerPOJO user: allUsers) {

            if(user.getUserID() == userID){
                return user;
            }
        }
        return null;
    }

    public TrainerPOJO getUserByIndex(int index){
        return allUsers.get(index);
    }
}
