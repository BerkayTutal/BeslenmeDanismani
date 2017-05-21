package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by berka on 21.05.2017.
 */

public class GlobalVariables extends Application {

    private ArrayList<ProgramPOJO> allPrograms;
    private ArrayList<UserPOJO> allUsers;

    public ArrayList<ProgramPOJO> getAllPrograms() {
        return allPrograms;
    }

    public void setAllPrograms(ArrayList<ProgramPOJO> allPrograms) {
        this.allPrograms = allPrograms;
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

    public ArrayList<UserPOJO> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<UserPOJO> allUsers) {
        this.allUsers = allUsers;
    }

    public UserPOJO getUserByID(int userID){
        for (UserPOJO user: allUsers) {

            if(user.getUserID() == userID){
                return user;
            }
        }
        return null;
    }

    public UserPOJO getUserByIndex(int index){
        return allUsers.get(index);
    }
}
