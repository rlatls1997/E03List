package net.skhu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class RecyclerView3Activity extends AppCompatActivity {
    private static final int REQUEST_CREATE = 0;
    private static final int REQUEST_EDIT = 1;

    RecyclerView3Adapter recyclerView3Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view3);

        recyclerView3Adapter = new RecyclerView3Adapter(this,
                (memo) -> startMemoActivityForResult(REQUEST_EDIT, memo),
                (count) -> { if (count <= 1) invalidateOptionsMenu(); });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView3Adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler_view3, menu);
        MenuItem menuItem = menu.findItem(R.id.action_remove);
        menuItem.setVisible(recyclerView3Adapter.checkedCount > 0);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            startMemoActivityForResult(REQUEST_CREATE, null);
            return true;
        } else if (id == R.id.action_remove) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirm);
            builder.setMessage(R.string.doYouWantToDelete);
            builder.setPositiveButton(R.string.yes, (dialog, index) -> recyclerView3Adapter.removeCheckedMemo());
            builder.setNegativeButton(R.string.no, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Memo memo = (Memo)intent.getSerializableExtra("MEMO");
            if (requestCode == REQUEST_CREATE)
                recyclerView3Adapter.add(memo);
            else if (requestCode == REQUEST_EDIT)
                recyclerView3Adapter.update(memo);
        }
    }

    private void startMemoActivityForResult(int requestCode, Memo memo) {
        Intent intent = new Intent(this, MemoActivity.class);
        intent.putExtra("MEMO", memo);
        startActivityForResult(intent, requestCode);
    }
}
