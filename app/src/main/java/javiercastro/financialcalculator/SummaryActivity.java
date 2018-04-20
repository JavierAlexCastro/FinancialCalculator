package javiercastro.financialcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class SummaryActivity extends Activity {

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
        setContentView(R.layout.activity_summary);

        final SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        loan_amount = settings.getFloat("LOAN",0);
        d_loan_amount = Double.parseDouble(loan_amount.toString());
        balance = d_loan_amount;
        period = settings.getInt("PERIOD",0);
        interest = settings.getFloat("INTEREST",0);
        month = settings.getInt("MONTH",0);
        year = settings.getInt("YEAR",0);

        DecimalFormat formatter = new DecimalFormat("$#,###,###.##");

        total_interest = 0.00;
        total_principal = 0.00;

        interest_pm = interest/1200;

        while(period>=0) {
            if (period == 0) {
                TextView monthly_payment = (TextView)findViewById(R.id.text_loanAmount);
                TextView paid_interest = (TextView)findViewById(R.id.out_interest);

                monthly_payment.setText(formatter.format(paid_amount));
                paid_interest.setText(formatter.format(total_interest));

                period --;
            } else {
                if(period == 1){
                    TextView payoff_date = (TextView)findViewById(R.id.out_payOffDate2);
                    payoff_date.setText(list_months.get(month)+ " "+Integer.toString(year));
                }

                paid_interest = balance * (interest_pm);

                temp = Math.pow((1 + interest_pm), period);
                paid_amount = (balance * interest_pm * temp) / (temp - 1);
                Log.d("paid: ", formatter.format(paid_amount));
                balance = balance - paid_amount + paid_interest;
                principal = paid_amount - paid_interest;

                total_interest = total_interest + paid_interest;
                if (period == 1) {
                    total_principal = total_principal + (balance + paid_amount - paid_interest);
                } else {
                    total_principal = total_principal + principal;
                }

                if (month > 11) {
                    month = month - 12;
                    year++;
                }

                period--;
                month++;
            }
        }


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //Intent schedule = new Intent(SummaryActivity.this, ScheduleActivity.class);
                //startActivity(schedule);

                Intent table = new Intent(SummaryActivity.this, TableActivity.class);
                startActivity(table);
            }
        });
    }

}
