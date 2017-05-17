package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFilterActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button applyButton;

    private EditText searchQueryEditText;
    private EditText trainerQueryEditText;
    private Spinner categorySpinner;
    private Spinner sortBySpinner;

    private String searchQueryString;
    private String trainerQueryString;

    private ArrayList<String> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        categories.add(getResources().getString(R.string.all));


        applyButton = (Button) findViewById(R.id.buttonFilterApply);
        cancelButton = (Button) findViewById(R.id.buttonFilterCancel);

        searchQueryEditText = (EditText) findViewById(R.id.filterSearchQueryEditText);
        trainerQueryEditText = (EditText) findViewById(R.id.filterTrainerQueryEditText);
        categorySpinner = (Spinner) findViewById(R.id.filterCategoriesSpinner);
        sortBySpinner = (Spinner) findViewById(R.id.filterSortBySpinner);

        ArrayAdapter<CharSequence> sortBySpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.sortBy, android.R.layout.simple_spinner_item);
        sortBySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortBySpinner.setAdapter(sortBySpinnerAdapter);

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoriesAdapter);


        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buraya settings kısmını koyup burayı finish edip önceki activity'ye intent ile göndericez

                searchQueryString = searchQueryEditText.getText().toString();
                trainerQueryString = trainerQueryEditText.getText().toString();

                Toast.makeText(view.getContext(), searchQueryString + " " + trainerQueryString, Toast.LENGTH_SHORT).show();
                finish();


            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
