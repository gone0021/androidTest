package com.example.todo_test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DeleteAct extends DialogFragment {
    public DeleteAct(int position, List menuList, ArrayAdapter adapter) {
        this.position = position;
        this.menuList = menuList;
        this.adapter = adapter;
    }

    int position;
    List<String> menuList;
    ArrayAdapter<String> adapter;

    // ダイアログの表示
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListere());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListere());

        AlertDialog dialog = builder.create();

        return dialog;
    }

    // 実行処理
    private class DialogButtonClickListere implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            String msg = "";

            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    menuList.remove(position);
                    adapter.notifyDataSetChanged();
                    msg = getString(R.string.dialog_ok_toast);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = getString(R.string.dialog_ng_toast);
                    break;
            }
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
