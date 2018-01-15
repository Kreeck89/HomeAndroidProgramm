package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import miracleit.com.homeandroidprogramm.model.Todo;

public class CreateTodoActivity extends AppCompatActivity {

    @BindView(R.id.txt_id_create)
    public EditText id;
    @BindView(R.id.txt_name_create)
    public EditText name;
    @BindView(R.id.txt_user_id_create)
    public EditText userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
        Realm.init(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save_new_todo)
    public void saveNewTodoToRealm(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Todo todo = realm.createObject(Todo.class);
        todo.setName(name.getText().toString());
        todo.setUserId(Long.valueOf(userId.getText().toString()));
        realm.insert(todo);
        realm.commitTransaction();

        id.getText().clear();
        name.getText().clear();
        userId.getText().clear();

        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_return)
    public void returnToMenu(View view) {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }
}
