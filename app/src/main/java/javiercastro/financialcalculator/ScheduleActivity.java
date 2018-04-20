package javiercastro.financialcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    Integer period;
    Double balance;
    Float interest;
    Double principal;
    Double paid_interest;
    Float loan_amount;
    Double d_loan_amount;
    Double paid_amount;
    Double temp;
    Float interest_pm;
    Integer month;
    Integer year;
    Double total_interest;
    Double total_principal;
    List<String> list_months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_schedule);

        final SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        loan_amount = settings.getFloat("LOAN",0);
        d_loan_amount = Double.parseDouble(loan_amount.toString());
        balance = d_loan_amount;
        period = settings.getInt("PERIOD",0);
        interest = settings.getFloat("INTEREST",0);
        month = settings.getInt("MONTH",0);
        year = settings.getInt("YEAR",0);

        DecimalFormat formatter = new DecimalFormat("$#,###,###.##");

        int i=0;
        int prevTextViewId = 0;
        int prevTextViewId1 = 1000;
        int prevTextViewId2 = 2000;
        int prevTextViewId3 = 3000;
        total_interest = 0.00;
        total_principal = 0.00;

        interest_pm = interest/1200;

        while(period>=0){
            if(period==0) {
                final TextView textView = new TextView(this);
                final TextView textView1 = new TextView(this);
                final TextView textView2 = new TextView(this);

                textView.setText("Total:");
                textView1.setText(formatter.format(total_principal));
                textView2.setText(formatter.format(total_interest));

                textView.setTextColor(getResources().getColor(R.color.white));
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
                textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

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

                params.addRule(RelativeLayout.BELOW, prevTextViewId);
                params1.addRule(RelativeLayout.BELOW, prevTextViewId1);
                params2.addRule(RelativeLayout.BELOW, prevTextViewId2);

                params.setMargins(0, 30, 0, 100);
                textView.setLayoutParams(params);
                prevTextViewId = curTextViewId;
                layout.addView(textView, params);

                params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params1.setMargins(0, 50, 0, 100);
                textView1.setLayoutParams(params1);
                textView1.setPadding(0, 0, 300, 0);
                prevTextViewId1 = curTextViewId1;
                layout.addView(textView1, params1);

                params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params2.setMargins(0, 50, 0, 100);
                textView2.setLayoutParams(params2);
                textView2.setPadding(300, 0, 0, 0);
                prevTextViewId2 = curTextViewId2;
                layout.addView(textView2, params2);

                period--;
            }
            else{
                paid_interest = balance * (interest_pm);

                temp = Math.pow((1 + interest_pm), period);
                paid_amount = (balance * interest_pm * temp) / (temp - 1);
                balance = balance - paid_amount + paid_interest;
                principal = paid_amount - paid_interest;

                total_interest = total_interest + paid_interest;
                if(period==1){
                    total_principal = total_principal + (balance+paid_amount-paid_interest);
                }else {
                    total_principal = total_principal + principal;
                }

                final TextView textView = new TextView(this); //month
                final TextView textView1 = new TextView(this); //principal
                final TextView textView2 = new TextView(this); //balance
                final TextView textView3 = new TextView(this); //interest

                if (month > 11) {
                    month = month - 12;
                    year++;
                }

                textView.setText(list_months.get(month) + " " + Integer.toString(year));
                if(period==1){
                    textView1.setText(formatter.format(balance+paid_amount-paid_interest));
                    textView2.setText(formatter.format(0.00));
                } else {
                    textView1.setText(formatter.format(principal));
                    textView2.setText(formatter.format(balance));
                }
                textView3.setText(formatter.format(paid_interest));

                textView.setTextColor(getResources().getColor(R.color.white));
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView3.setTextColor(getResources().getColor(R.color.white));
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
                textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
                textView3.setTypeface(textView2.getTypeface(), Typeface.BOLD);

                int curTextViewId = prevTextViewId + 1;
                int curTextViewId1 = prevTextViewId1 + 1;
                int curTextViewId2 = prevTextViewId2 + 1;
                int curTextViewId3 = prevTextViewId2 + 1;

                textView.setId(curTextViewId);
                textView1.setId(curTextViewId1);
                textView2.setId(curTextViewId2);
                textView3.setId(curTextViewId3);

                final RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                final RelativeLayout.LayoutParams params1 =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                final RelativeLayout.LayoutParams params2 =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                final RelativeLayout.LayoutParams params3 =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);

                if (i == 0) {
                    params.addRule(RelativeLayout.BELOW, R.id.schedule_month);
                    params1.addRule(RelativeLayout.BELOW, R.id.text_principal);
                    params2.addRule(RelativeLayout.BELOW, R.id.schedule_balance);
                    params3.addRule(RelativeLayout.BELOW, R.id.text_interest);
                } else {
                    params.addRule(RelativeLayout.BELOW, prevTextViewId);
                    params1.addRule(RelativeLayout.BELOW, prevTextViewId1);
                    params2.addRule(RelativeLayout.BELOW, prevTextViewId2);
                    params3.addRule(RelativeLayout.BELOW, prevTextViewId3);
                }

                params.setMargins(0, 15, 0, 0);
                textView.setLayoutParams(params);
                prevTextViewId = curTextViewId;
                layout.addView(textView, params);

                params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params1.setMargins(0, 15, 0, 0);
                textView1.setLayoutParams(params1);
                textView1.setPadding(0, 0, 300, 0);
                prevTextViewId1 = curTextViewId1;
                layout.addView(textView1, params1);

                params2.addRule(RelativeLayout.ALIGN_PARENT_END);
                params2.setMargins(0, 15, 0, 0);
                textView2.setLayoutParams(params2);
                prevTextViewId2 = curTextViewId2;
                layout.addView(textView2, params2);

                params3.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params3.setMargins(0, 15, 0, 0);
                textView3.setLayoutParams(params3);
                textView3.setPadding(300, 0, 0, 0);
                prevTextViewId3 = curTextViewId3;
                layout.addView(textView3, params3);

                period--;
                month++;
                i++;
            }
        }
    }

}
