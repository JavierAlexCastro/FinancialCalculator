package javiercastro.financialcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button start_btn = (Button) findViewById(R.id.start_btn);
        final Button about_btn = (Button) findViewById(R.id.about_btn);

        start_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent calculator = new Intent(HomeActivity.this, CalculatorActivity.class);
                startActivity(calculator);

            }
        });

        about_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent about = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(about);


            }
        });

    }
}
