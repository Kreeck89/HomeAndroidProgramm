package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import miracleit.com.homeandroidprogramm.model.User;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.txt_id)
    public EditText txtId;
    @BindView(R.id.txt_name)
    public EditText txtName;
    @BindView(R.id.txt_email)
    public EditText txtEmail;
    @BindView(R.id.txt_password)
    public EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Realm.init(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void registrationNewUser(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        User user = realm.createObject(User.class);
        if (!txtId.getText().toString().equals("")) {
            user.setId(Long.valueOf(txtId.getText().toString()));
            if (!txtName.getText().toString().equals("")) {
                user.setName(txtName.getText().toString());
                if (!txtEmail.getText().toString().equals("")) {
                    user.setEmail(txtEmail.getText().toString());
                    if (!txtPassword.getText().toString().equals("")) {
                        user.setPassword(txtPassword.getText().toString());
                        user.setId(Long.valueOf(txtId.getText().toString()));
                        realm.insert(user);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                        int boldStart = snackbarText.length();
                        snackbarText.append("WARNING!");
                        snackbarText.setSpan(new ForegroundColorSpan(0xFFFF0000), boldStart, snackbarText.length(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.setSpan(new StyleSpan(Typeface.BOLD), boldStart, snackbarText.length(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.append(" You must enter your password!");
                        Snackbar.make(view, snackbarText, Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Your email is empty!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "You need enter your name", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Enter your ID!", Toast.LENGTH_LONG).show();
        }
        realm.commitTransaction();
    }

    @OnClick(R.id.btn_cancel_reg)
    public void cancelFromRegistration(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
