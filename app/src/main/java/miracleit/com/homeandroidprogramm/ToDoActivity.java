package miracleit.com.homeandroidprogramm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import miracleit.com.homeandroidprogramm.model.Todo;
import miracleit.com.homeandroidprogramm.model.TodoDTO;

public class ToDoActivity extends AppCompatActivity {

    Realm realm = Realm.getDefaultInstance();

    private DatabaseReference reference;

    @BindView(R.id.img_all_todo)
    public ImageView imgAllTodo;
    @BindView(R.id.img_my_todo)
    public ImageView imgIdTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        Realm.init(this);
        ButterKnife.bind(this);

        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        reference = instance.getReference().child("Todo");
    }

    @OnClick(R.id.btn_all_todo)
    public void seeAllTodos(View view) {
        Intent intent = new Intent(this, ToDoAllActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_my_todo)
    public void seeByIdTodos(View view) {
        Intent intent = new Intent(this, ToDoIdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_return_main)
    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_pull)
    public void pullAllTodo(View view) {
        realm.beginTransaction();
        realm.delete(Todo.class);
        realm.commitTransaction();

        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = instance.getReference().child("Todo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                realm.beginTransaction();
                for (DataSnapshot elem : dataSnapshot.getChildren()) {
                    TodoDTO todoDTO = elem.getValue(TodoDTO.class);
                    Todo todo = new Todo();
                    todo.setName(todoDTO.getName());
                    todo.setUserId(todoDTO.getUserID());
                    realm.insert(todo);
                }
                realm.commitTransaction();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @OnClick(R.id.btn_push)
    public void pushAllTodo() {
        reference.removeValue();
        realm.beginTransaction();
        RealmResults<Todo> all = realm.where(Todo.class).findAll();
        for (Todo todo : all) {
            System.out.println(todo);
            TodoDTO todoDTO = new TodoDTO();
            todoDTO.setName(todo.getName());
            todoDTO.setUserID(todo.getUserId());
            reference.push().setValue(todoDTO);
        }
        realm.commitTransaction();
    }
}
