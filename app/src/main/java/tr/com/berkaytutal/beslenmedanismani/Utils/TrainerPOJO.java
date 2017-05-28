package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;

/**
 * Created by berka on 21.05.2017.
 */

public class TrainerPOJO {
    private String name;
    private String surname;
    private String sex;
    private Bitmap photo;
    private int userID;
    private String birthday;

    public TrainerPOJO(String name, String surname, String sex, Bitmap photo, int userID, String birthday) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.photo = photo;
        this.userID = userID;
        this.birthday = birthday;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
