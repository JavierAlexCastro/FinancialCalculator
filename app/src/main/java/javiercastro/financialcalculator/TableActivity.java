package javiercastro.financialcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class TableActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_table);
        TableLayout table = (TableLayout) findViewById(R.id.activity_table);
        table.setGravity(Gravity.CENTER);
        int i = 0;

        final TextView header = new TextView(this); //month
        final TextView header1 = new TextView(this); //principal
        final TextView header2 = new TextView(this); //balance
        final TextView header3 = new TextView(this); //interest

        header.setTextColor(getResources().getColor(R.color.white));
        header1.setTextColor(getResources().getColor(R.color.white));
        header2.setTextColor(getResources().getColor(R.color.white));
        header3.setTextColor(getResources().getColor(R.color.white));
        header.setTypeface(header.getTypeface(), Typeface.BOLD);
        header1.setTypeface(header1.getTypeface(), Typeface.BOLD);
        header2.setTypeface(header2.getTypeface(), Typeface.BOLD);
        header3.setTypeface(header3.getTypeface(), Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        header1.setGravity(Gravity.CENTER);
        header2.setGravity(Gravity.CENTER);
        header3.setGravity(Gravity.CENTER);
        header.setPadding(5, 10, 5, 10);
        header1.setPadding(5, 10, 5, 10);
        header2.setPadding(5, 10, 5, 10);
        header3.setPadding(5, 10, 5, 10);
        header.setText("MONTH");
        header1.setText("PRINCIPAL");
        header2.setText("INTEREST");
        header3.setText("BALANCE");

        TableRow head = new TableRow(this);
        head.setBackgroundColor(Color.BLACK);
        TableRow.LayoutParams lay_par = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        head.setLayoutParams(lay_par);
        head.addView(header);
        head.addView(header1);
        head.addView(header2);
        head.addView(header3);
        table.addView(head, i);
        i++;

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
            if(period==0){
                final TextView textView = new TextView(this);
                final TextView textView1 = new TextView(this);
                final TextView textView2 = new TextView(this);

                textView.setTextColor(getResources().getColor(R.color.white));
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
                textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
                textView.setGravity(Gravity.CENTER);
                textView1.setGravity(Gravity.CENTER);
                textView2.setGravity(Gravity.CENTER);
                textView.setPadding(5, 10, 5, 10);
                textView1.setPadding(5, 10, 5, 10);
                textView2.setPadding(5, 10, 5, 10);

                textView.setText("TOTAL:");
                textView1.setText(formatter.format(total_principal));
                textView2.setText(formatter.format(total_interest));

                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.BLACK);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                row.addView(textView);
                row.addView(textView1);
                row.addView(textView2);
                table.addView(row, i);

            } else {
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

                textView.setTextColor(getResources().getColor(R.color.white));
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView3.setTextColor(getResources().getColor(R.color.white));
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);
                textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD);
                textView3.setTypeface(textView3.getTypeface(), Typeface.BOLD);
                textView.setGravity(Gravity.CENTER);
                textView1.setGravity(Gravity.CENTER);
                textView2.setGravity(Gravity.CENTER);
                textView3.setGravity(Gravity.CENTER);

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

                TableRow row = new TableRow(this);
                if(i%2==0){
                    row.setBackgroundColor(Color.GRAY);
                }
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                row.addView(textView);
                row.addView(textView1);
                row.addView(textView3);
                row.addView(textView2);
                table.addView(row, i);
            }


            period--;
            month++;
            i++;
        }

    }
}
