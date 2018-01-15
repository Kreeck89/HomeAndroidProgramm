package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import miracleit.com.homeandroidprogramm.model.Todo;

public class ToDoAllActivity extends AppCompatActivity {

    @BindView(R.id.list_all_todo)
    public ListView listTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_all);
        Realm.init(this);
        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        List<Todo> all = realm.where(Todo.class).findAll();
        ArrayAdapter<Todo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, all);
        listTodo.setAdapter(adapter);
    }

    @OnClick(R.id.btn_cancel_fromall)
    public void cancelFromAllActivity(View view) {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_add_todo_all)
    public void CreateActivityFromAll(View view) {
        Intent intent = new Intent(this, CreateTodoActivity.class);
        startActivity(intent);
    }
}
