package javiercastro.financialcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class ScheduleActivity extends AppCompatActivity {

    Integer period;
    Float loan_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_schedule);

        final SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        loan_amount = settings.getFloat("LOAN",0);
        period = settings.getInt("PERIOD",0);

        int i;
        //for(i=0;i<=period;i++){

        //}
    }

}
