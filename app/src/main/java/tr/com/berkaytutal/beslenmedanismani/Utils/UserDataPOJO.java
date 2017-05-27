package tr.com.berkaytutal.beslenmedanismani.Utils;

/**
 * Created by MUSTAFA on 27.05.2017.
 */

public class UserDataPOJO {
    private String user_ID;
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

    public UserDataPOJO(String user_ID, String name, String surname, String email, String sex, String birthday, String tall,
                    String weight, String muscleRate, String fatRate, String waterRate) {

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
    }



    public UserDataPOJO(){

    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
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
}