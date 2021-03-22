package kr.ac.kpu.game.s2016182019.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBox firewallCheckbox;
    private TextView outTextView;
    private TextView editTextView;
    private EditText userEditText;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            editTextView.setText("String length = " + s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firewallCheckbox = findViewById(R.id.checkbox);
        outTextView = findViewById(R.id.outTextView);
        userEditText = findViewById(R.id.userEditText);
        editTextView = findViewById(R.id.editTextView);


        userEditText.addTextChangedListener(textWatcher);
    }

    public void onBtnApply(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text = checked ? "Using Firewall" : "Not Using Firewall";
        outTextView.setText(text);

        String user = userEditText.getText().toString();
        editTextView.setText("User info = " + user);
    }

    public void onCheckFirewall(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text = checked ? "Checked Firewall" : "Unchecked Firewall";
        outTextView.setText(text);
    }



}