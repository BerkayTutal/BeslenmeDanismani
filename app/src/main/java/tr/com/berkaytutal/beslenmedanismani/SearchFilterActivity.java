package tr.com.berkaytutal.beslenmedanismani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchFilterActivity extends AppCompatActivity {

    private Button cancelButton;
    private Button applyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);


        applyButton = (Button) findViewById(R.id.buttonFilterApply);
        cancelButton = (Button) findViewById(R.id.buttonFilterCancel);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buraya settings kısmını koyup burayı finish edip önceki activity'ye intent ile göndericez
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
