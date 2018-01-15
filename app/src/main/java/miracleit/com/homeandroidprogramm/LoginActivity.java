package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import miracleit.com.homeandroidprogramm.model.User;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference reference;

    @BindView(R.id.txt_email_login)
    public EditText txtEmail;
    @BindView(R.id.txt_pass_login)
    public EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Realm.init(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).contains("email", txtEmail.getText().toString()).findFirst();
        realm.commitTransaction();

        if (user == null) {
            Toast.makeText(this, "User is null", Toast.LENGTH_LONG).show();
            return;
        }
        if (txtPassword.getText().toString().equals(user.getPassword())) {
            User.setIdEnteredUser(user.getId());
            Intent intent = new Intent(this, ToDoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Password is wrong", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btn_back)
    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
