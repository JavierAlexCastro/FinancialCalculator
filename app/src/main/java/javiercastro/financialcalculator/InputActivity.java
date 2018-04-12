package javiercastro.financialcalculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setupUI(findViewById(R.id.activity_input));

        Button continue_btn = (Button)findViewById(R.id.button_continue);
        final EditText e_loan = (EditText)findViewById(R.id.input_amount);
        final EditText e_interest = (EditText)findViewById(R.id.input_interest);
        final EditText e_period = (EditText)findViewById(R.id.input_period);

        continue_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Float amount = Float.valueOf(e_loan.getText().toString());
                Float interest = Float.valueOf(e_interest.getText().toString());
                Integer period = Integer.valueOf(e_period.getText().toString());
                SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putFloat("LOAN", amount);
                editor.putFloat("INTEREST", interest);
                editor.putInt("PERIOD", period);
                editor.apply();
            }
        });
    }

    /**
     * This callback method, call DatePickerFragment class,
     * DatePickerFragment class returns calendar view.
     * @param view
     */
    public void datePicker(View view){

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    /**
     * To set date on TextView
     * @param calendar
     */
    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.input_date_d))
                .setText(dateFormat.format(calendar.getTime()));

    }

    /**
     * To receive a callback when the user sets the date.
     * @param view
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("DAY", day);
        editor.putInt("MONTH",month);
        editor.putInt("YEAR", year);
        editor.apply();
        Log.d("Year: ", Integer.toString(year));
        Log.d("Month: ", Integer.toString(month));
        Log.d("Day: ", Integer.toString(day));
        setDate(cal);
    }

    /**
     * Create a DatePickerFragment class that extends DialogFragment.
     * Define the onCreateDialog() method to return an instance of DatePickerDialog
     */
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            //Log.d("Year: ", Integer.toString(year));
            //Log.d("Month: ", Integer.toString(month));
            //Log.d("Day: ", Integer.toString(day));


            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(InputActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

