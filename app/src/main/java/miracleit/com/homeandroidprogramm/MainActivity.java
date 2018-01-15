package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_register)
    public ImageView imgRegister;
    @BindView(R.id.img_login)
    public ImageView imgLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void goToRegistration(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
