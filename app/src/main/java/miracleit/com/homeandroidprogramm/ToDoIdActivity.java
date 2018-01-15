package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import miracleit.com.homeandroidprogramm.model.Todo;
import miracleit.com.homeandroidprogramm.model.User;

public class ToDoIdActivity extends AppCompatActivity {

    @BindView(R.id.list_todo_byid)
    public ListView listTodoById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_id);
        Realm.init(this);
        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        List<Todo> all = realm.where(Todo.class).findAll();
        List<Todo> allById = new ArrayList<>();
        for (Todo elem : all) {
            if (elem.getUserId() == User.getIdEnteredUser()) {
                allById.add(elem);
            }
        }
        ArrayAdapter<Todo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allById);
        listTodoById.setAdapter(adapter);
    }

    @OnClick(R.id.btn_crt_todo_fromid)
    public void addNewTodoAndExit(View view) {
        Intent intent = new Intent(this, CreateTodoActivity.class);
        startActivity(intent);
    }
}
