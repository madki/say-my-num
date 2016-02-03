package xyz.madki.saymynum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by madki on 03/02/16.
 */
public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    TextView tvNumStr;
    TextView tvNumPretty;

    CheckBox cbCapitalize;

    Digits digits;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        this.etNumber = (EditText) findViewById(R.id.et_number);
        this.tvNumStr = (TextView) findViewById(R.id.tv_num_str);
        this.tvNumPretty = (TextView) findViewById(R.id.tv_num_pretty);

        this.cbCapitalize = (CheckBox) findViewById(R.id.cb_capitalize);

        cbCapitalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNumStr();
            }
        });

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateNumStr();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        updateNumStr();
    }

    private void updateNumStr() {
        String str = etNumber.getText().toString();
        try {
            digits = new Digits(str);
            tvNumPretty.setText(digits.prettify());
            if(cbCapitalize.isChecked()) {
                tvNumStr.setText(TextUtils.getProperCaseForSentence(digits.asReadableText(), "and"));
            } else {
                tvNumStr.setText(digits.asReadableText());
            }
        } catch (NullPointerException e) {
            tvNumPretty.setText("0");
            tvNumStr.setText(R.string.prompt_for_num);
        } catch (IllegalArgumentException e) {
            if(e.getMessage().equals(Digits.ERROR_NUM_TOO_LARGE)) {
                tvNumPretty.setText(R.string.max);
                tvNumStr.setText(R.string.cant_count);
            } else {
                tvNumPretty.setText(R.string.nan);
                tvNumStr.setText(R.string.invalid_num);
            }
        }
    }


}
