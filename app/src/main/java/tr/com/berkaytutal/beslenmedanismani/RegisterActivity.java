package tr.com.berkaytutal.beslenmedanismani;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;
    private EditText birthdayEditText;
    private Button registerButton;

    private int yearM = -1;
    private int monthM = -1;
    private int dayM = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText) findViewById(R.id.nameRegisterScreen);
        surnameEditText = (EditText) findViewById(R.id.surnameRegisterScreen);
        emailEditText = (EditText) findViewById(R.id.emailAddressRegisterScreen);
        passwordEditText = (EditText) findViewById(R.id.passwordRegisterScreen);
        passwordAgainEditText = (EditText) findViewById(R.id.passwordAgainRegisterScreen);
        birthdayEditText = (EditText) findViewById(R.id.birthdayEditTextRegisterScreen);
        registerButton = (Button) findViewById(R.id.registerButtonRegisterScreen);

        if(getIntent().hasExtra("email")){
            emailEditText.setText(getIntent().getStringExtra("email").toString());
        }
        if(getIntent().hasExtra("password")){
            passwordEditText.setText(getIntent().getStringExtra("password"));
        }


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
                        birthdayEditText.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO buradan login'e email ve password ile yönlendirip loginde direkt login olma kımına geçeceğiz

                if(!passwordEditText.getText().toString().equals(passwordAgainEditText.getText().toString())){
                    Toast.makeText(getApplicationContext(),"şifreleriniz aynı omalı",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"register olduk",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
