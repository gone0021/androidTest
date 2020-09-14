package com.example.todo_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("EditPage"); //

        //インテントオブジェクトを取得。
        Intent intent = getIntent();
        //リスト画面から渡されたデータを取得。
        String menuName = intent.getStringExtra("todo");

        //定食名と金額を表示させるTextViewを取得。
        TextView tvMenuName = findViewById(R.id.editText);

        //TextViewに定食名と金額を表示。
        tvMenuName.setText(menuName);
    }

    /**
     * ボタンをタップした時の処理。
     * @param view 画面部品。
     */
    public void onBtSub(View view) {
        Intent intentBack = new Intent();

        EditText edit = findViewById(R.id.editText);

        String name = edit.getText().toString();

        intentBack.putExtra("todoBk", name);

        setResult(RESULT_OK,intentBack);

        finish();
    }
}
