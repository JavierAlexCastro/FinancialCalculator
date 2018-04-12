package javiercastro.financialcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        int prevTextViewId = 0;
        int prevTextViewId1 = 100;
        int prevTextViewId2 = 200;
        for(i=0;i<=period;i++){

            final TextView textView = new TextView(this);
            final TextView textView1 = new TextView(this);
            final TextView textView2 = new TextView(this);
            textView.setText("Month "+i);
            textView1.setText("Paid "+i);
            textView2.setText("Balance "+i);

            textView.setTextColor(getResources().getColor(R.color.white));
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white));

            int curTextViewId = prevTextViewId + 1;
            int curTextViewId1 = prevTextViewId1 + 1;
            int curTextViewId2 = prevTextViewId2 + 1;
            textView.setId(curTextViewId);
            textView1.setId(curTextViewId1);
            textView2.setId(curTextViewId2);
            final RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            final RelativeLayout.LayoutParams params1 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            final RelativeLayout.LayoutParams params2 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i==0){
                params.addRule(RelativeLayout.BELOW, R.id.schedule_month);
                params1.addRule(RelativeLayout.BELOW,R.id.schedule_paid);
                params2.addRule(RelativeLayout.BELOW,R.id.schedule_balance);
            }
            else{
                params.addRule(RelativeLayout.BELOW, prevTextViewId);
                params1.addRule(RelativeLayout.BELOW, prevTextViewId);
                params2.addRule(RelativeLayout.BELOW, prevTextViewId);
            }

            textView.setLayoutParams(params);
            prevTextViewId = curTextViewId;
            layout.addView(textView, params);

            params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textView1.setLayoutParams(params1);
            prevTextViewId1 = curTextViewId1;
            layout.addView(textView1, params1);

            params2.addRule(RelativeLayout.ALIGN_PARENT_END);
            textView2.setLayoutParams(params2);
            prevTextViewId2 = curTextViewId2;
            layout.addView(textView2, params2);

        }

        /*prevTextViewId = 0;
        for(i=0;i<=period;i++){

            final TextView textView = new TextView(this);
            textView.setText("Amount "+i);
            textView.setTextColor(getResources().getColor(R.color.white));

            int curTextViewId = prevTextViewId + 1;
            textView.setId(curTextViewId);
            final RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i==0){
                params.addRule(RelativeLayout.BELOW, R.id.schedule_paid);
            }
            else{
                params.addRule(RelativeLayout.BELOW, prevTextViewId);

            }
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textView.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            layout.addView(textView, params);
        }

        prevTextViewId = 0;
        for(i=0;i<=period;i++){

            final TextView textView = new TextView(this);
            textView.setText("Balance "+i);
            textView.setTextColor(getResources().getColor(R.color.white));

            int curTextViewId = prevTextViewId + 1;
            textView.setId(curTextViewId);
            final RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            if(i==0){
                params.addRule(RelativeLayout.BELOW, R.id.schedule_balance);
            }
            else{
                params.addRule(RelativeLayout.BELOW, prevTextViewId);
            }
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            textView.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            layout.addView(textView, params);
        }*/
    }

}
