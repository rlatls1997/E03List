package net.skhu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class Exam3Activity extends AppCompatActivity {
    RecyclerView1Adapter recyclerView1Adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);

        arrayList = new ArrayList<String>();
        arrayList.add("one");
        arrayList.add("two");

        recyclerView1Adapter = new RecyclerView1Adapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView1Adapter);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler_view4, menu);
        MenuItem menuItem = menu.findItem(R.id.action_create);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            Intent intent = new Intent(this, Exam3EditActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Memo memo = (Memo)intent.getSerializableExtra("MEMO");

        }
    }




}


