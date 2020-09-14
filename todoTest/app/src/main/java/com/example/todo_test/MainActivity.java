package com.example.todo_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<String> menuList;
    ArrayAdapter<String> adapter;
    int row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //各種ウィジェットの情報を取得
        ListView lvMenu = findViewById(R.id.lvMenu);
        Button btAdd = findViewById(R.id.btAdd);

        //リストを作成
        menuList = new ArrayList<>();

        //リストにデータを追加
        menuList.add("aaa");
        menuList.add("bbb");
        menuList.add("ccc");


        //アダプターオブジェクトを作成（android標準のもの）
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuList);

        //ListViewにアダプターをセット
        lvMenu.setAdapter(adapter);

        //各種ウィジェットにリスナを設定
        btAdd.setOnClickListener(new ButtonClickListener());    //Buttonタップイベント
        lvMenu.setOnItemLongClickListener(new ListItemLongClickListener()); //ListViewロングタップイベント

        lvMenu.setOnItemClickListener(new ListItemClickListener()); //ListViewタップイベント
    }

    //ListViewタップイベント
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = (String) parent.getItemAtPosition(position);
//            Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();

//            Intentオブジェクトを作成して値を追加・起動
            Intent intent = new Intent(MainActivity.this, EditAct.class);
            intent.putExtra("todo", item); // 値を追加

            // positionを渡す
            row = position;
            // startActivity(intent); //Intentを実行
            startActivityForResult(intent, 1);
        }
    }

    // Subから値を取得
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("todoBk");

            menuList.set(row, name);
            adapter.notifyDataSetChanged();

            Toast.makeText(MainActivity.this, "Change：" + name, Toast.LENGTH_LONG).show();
        }
    }

    //Buttonタップイベント
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //EditTextの文字列を取得
            EditText etAdd = findViewById(R.id.etAdd);
            String etGetText = etAdd.getText().toString();

            //リストにデータを追加してアダプターを更新
            menuList.add(etGetText);
            adapter.notifyDataSetChanged();

            etAdd.setText(null);
        }
    }

    //ListViewロングタップイベント
    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            //リストからデータを削除してアダプターを更新
            DeleteAct delete = new DeleteAct(position, menuList, adapter);
            delete.show(getSupportFragmentManager(), "DeleteDialog");
            return true;
        }
    }


}

