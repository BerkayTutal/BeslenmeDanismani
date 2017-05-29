package tr.com.berkaytutal.beslenmedanismani;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import tr.com.berkaytutal.beslenmedanismani.Utils.BaseDrawerActivity;
import tr.com.berkaytutal.beslenmedanismani.Utils.GlobalVariables;
import tr.com.berkaytutal.beslenmedanismani.Utils.UserDataPOJO;

public class EditProfileActivity extends BaseDrawerActivity {

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

    private int yearM = -1;
    private int monthM = -1;
    private int dayM = -1;

    private String[] dates ;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem profileButton = menu.findItem(R.id.appBarProfileButton);
        profileButton.setVisible(false);


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        UserDataPOJO userDataPOJO = ((GlobalVariables)getApplicationContext()).getUserDataPOJO();

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


        dates = userDataPOJO.getBirthday().split("-");
        yearM = Integer.parseInt(dates[0]);
        monthM = Integer.parseInt(dates[1])-1;
        dayM = Integer.parseInt(dates[2]);




        nameEditText.setText(userDataPOJO.getName());
        surnameEditText.setText(userDataPOJO.getSurname());
        emailEditText.setText(userDataPOJO.getEmail());
        birthdayEditText.setText(userDataPOJO.getBirthday());
        if(userDataPOJO.getSex().equals("M")){
            maleRadio.setChecked(true);
            femaleRadio.setChecked(false);
        }
        else{
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
                if(yearM == -1 || monthM == -1 || dayM == -1){
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
                        birthdayEditText.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                        dayM= dayOfMonth;
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
                Toast.makeText(view.getContext(),"magic happens here",Toast.LENGTH_SHORT).show();
                //TODO


            }
        });

    }
}
