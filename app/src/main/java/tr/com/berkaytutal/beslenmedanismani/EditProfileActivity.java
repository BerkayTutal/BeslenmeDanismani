package tr.com.berkaytutal.beslenmedanismani;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.mvc.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.DBHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.DataSenderHelper;
import tr.com.berkaytutal.beslenmedanismani.Utils.FunctionUtils;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.PasswordHashingMD5;
import tr.com.berkaytutal.beslenmedanismani.Utils.PublicVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class EditProfileActivity extends BaseDrawerActivity {

    private Bitmap selectedBitmap;
    private byte[] photoByte;

    private ImageView profileImage;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText emailEditText;
    private EditText birthdayEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;
    private EditText currentPasswordEditText;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private Button updateButton;
    private Switch isPrivateSwitch;
    private ImageView infoPrivate;

    private int yearM = -1;
    private int monthM = -1;
    private int dayM = -1;

    private String[] dates;

    private JSONObject jsonObject;
    private SharedPreferences userDetails;
    private String userPass;


    UserDataPOJO userDataPOJO;


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem profileButton = menu.findItem(R.id.appBarProfileButton);
        profileButton.setVisible(false);


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        selectedBitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (selectedBitmap != null) {
            profileImage.setImageBitmap(selectedBitmap);
        }
        // TODO do something with the bitmap
    }

    public void onPickImage(View view) {
        // Click on image button
        ImagePicker.pickImage(this, "Select your image:");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


        userDataPOJO = ((GlobalVariables) getApplicationContext()).getUserDataPOJO();

        userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
        userPass = userDetails.getString("userPass", "");

        profileImage = (ImageView) findViewById(R.id.editUserImage);
        nameEditText = (EditText) findViewById(R.id.editUserName);
        surnameEditText = (EditText) findViewById(R.id.editUserSurname);
        emailEditText = (EditText) findViewById(R.id.editUserMail);
        birthdayEditText = (EditText) findViewById(R.id.editUserBirthday);
        maleRadio = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadio = (RadioButton) findViewById(R.id.femaleRadioButton);
        updateButton = (Button) findViewById(R.id.userProfileUpdateDetails);
        passwordEditText = (EditText) findViewById(R.id.editUserPassword);
        passwordAgainEditText = (EditText) findViewById(R.id.editUserPasswordAgain);
        currentPasswordEditText = (EditText) findViewById(R.id.editUserCurrentPassword);
        isPrivateSwitch = (Switch) findViewById(R.id.editUserPrivateSwitch);
        infoPrivate = (ImageView) findViewById(R.id.infoPrivateProfileEdit);

        profileImage.setImageBitmap(userDataPOJO.getPhoto());

        infoPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO dialog

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        view.getContext());

                // set title
                alertDialogBuilder.setTitle(R.string.privateProfile);

                // set dialog message
                alertDialogBuilder
                        .setMessage(R.string.infoPrivateProfile)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                }
                        );

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });


        dates = userDataPOJO.getBirthday().split("-");
        yearM = Integer.parseInt(dates[0]);
        monthM = Integer.parseInt(dates[1]) - 1;
        dayM = Integer.parseInt(dates[2]);


        if (userDataPOJO.isPrivate()) {
            isPrivateSwitch.setChecked(true);
        }


        nameEditText.setText(userDataPOJO.getName());
        surnameEditText.setText(userDataPOJO.getSurname());
        emailEditText.setText(userDataPOJO.getEmail());
        birthdayEditText.setText(userDataPOJO.getBirthday());
        if (userDataPOJO.getSex().equals("M")) {
            maleRadio.setChecked(true);
            femaleRadio.setChecked(false);
        } else {
            maleRadio.setChecked(false);
            femaleRadio.setChecked(true);
        }

        maleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleRadio.setChecked(true);
                femaleRadio.setChecked(false);
            }
        });
        femaleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleRadio.setChecked(false);
                femaleRadio.setChecked(true);
            }
        });

        birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                if (yearM == -1 || monthM == -1 || dayM == -1) {
                    yearM = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                    monthM = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                    dayM = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz
                }


                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        birthdayEditText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                        dayM = dayOfMonth;
                        monthM = monthOfYear;
                        yearM = year;

                    }
                }, yearM, monthM, dayM);//başlarken set edilcek değerlerimizi atıyoruz
//                datePicker.setTitle("Tarih Seçiniz");
//                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
//                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.getDatePicker().setMaxDate(mcurrentTime.getTimeInMillis());

                datePicker.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "magic happens here", Toast.LENGTH_SHORT).show();

                if (currentPasswordEditText.getText().toString().equals(userPass)) {


                    jsonObject = new JSONObject();
                    boolean isPrivate = isPrivateSwitch.isChecked();
                    if (isPrivate) {
                        try {
                            jsonObject.accumulate("isPrivate", "Y");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            jsonObject.accumulate("isPrivate", "N");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {

                        jsonObject.accumulate("user_ID", userDataPOJO.getUser_ID() + "");
                        jsonObject.accumulate("name", nameEditText.getText().toString());
                        jsonObject.accumulate("surname", surnameEditText.getText().toString());
                        jsonObject.accumulate("birthday", birthdayEditText.getText().toString());
                        jsonObject.accumulate("email", emailEditText.getText().toString());
                        if (maleRadio.isChecked()) {
                            jsonObject.accumulate("sex", "M");
                        } else if (femaleRadio.isChecked()) {
                            jsonObject.accumulate("sex", "F");

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!passwordEditText.getText().toString().replaceAll("[\\D]", "").equals("")) {
                        if (passwordEditText.getText().toString().equals(passwordAgainEditText.getText().toString())) {
                            try {
                                jsonObject.accumulate("password", PasswordHashingMD5.md5(passwordEditText.getText().toString()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "iki şifre de aynı olmalı", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        try {
                            jsonObject.accumulate("password", PasswordHashingMD5.md5(userPass));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                    if (selectedBitmap != null) {
                        photoByte = FunctionUtils.bitmapToByte(selectedBitmap);
                    } else {
                        photoByte = userDataPOJO.getPhotoByte();
                    }

                    try {
                        jsonObject.accumulate("userPhoto", Base64.encodeToString(photoByte, Base64.DEFAULT));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MyRegisterAsync async = new MyRegisterAsync();
                    async.execute("test");


                } else {
                    Toast.makeText(getApplicationContext(), "şifren yanlış", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }


    private class MyRegisterAsync extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            Log.i("json", jsonObject.toString());
            return DataSenderHelper.POST(PublicVariables.userUpdateURL, jsonObject);

        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("false")) {
                Toast.makeText(getApplicationContext(), "sorun oluştu", Toast.LENGTH_SHORT).show();
            } else if (result.equals("true")) {

                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putString("userEmail", emailEditText.getText().toString());
                if (passwordEditText.getText().toString().equals("")) {
                    edit.putString("userPass", currentPasswordEditText.getText().toString());
                } else {
                    edit.putString("userPass", passwordEditText.getText().toString());
                }
                edit.commit();
                Toast.makeText(getApplicationContext(), "Login details are saved..", Toast.LENGTH_LONG).show();

                String sex = "M";
                if (maleRadio.isChecked()) {
                    sex = "M";
                } else if (femaleRadio.isChecked()) {
                    sex = "F";
                }


                UserDataPOJO newUserDataPOJO = new UserDataPOJO(userDataPOJO.getUser_ID(), nameEditText.getText().toString(), surnameEditText.getText().toString(), emailEditText.getText().toString()
                        , sex, birthdayEditText.getText().toString(), photoByte, isPrivateSwitch.isChecked(), userDataPOJO.getMoney(), userDataPOJO.getMyPrograms());
                newUserDataPOJO.setBodyRatios(userDataPOJO.getBodyRatios());

                ((GlobalVariables) getApplicationContext()).setUserDataPOJO(newUserDataPOJO);

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.updateUser(newUserDataPOJO);
//                ProfileActivity.profileActivity.finish();
//
//                Intent i = new Intent(EditProfileActivity.this, ProfileActivity.class);
//                startActivity(i);

                finish();
            }
        }
    }
}
