package calculator.team07.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.R;
import calculator.team07.adapter.CustomAdapter;
import calculator.team07.model.Entity.Result;
import calculator.team07.model.SubModel.CalculatorHandle;
import calculator.team07.presenter.HistoryPresenter;
import calculator.team07.presenter.IHistoryView;

public class HistoryActivity extends AppCompatActivity implements IHistoryView {
    //Tang presenter dieu khien
    private HistoryPresenter mHistoryPresenter;

    //Danh sach ket qua lich su
    private List<Result> mListResult;

    //Adapter cho listview
    private CustomAdapter mCustomAdapter;

    private ListView mListView;

    //vi tri cua thanh phan duoc chon trong listview
    private int mPosition = -9999;

    private Result mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mHistoryPresenter = new HistoryPresenter(this);

        mHistoryPresenter.getListResut();

        //Set ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("History");
    }


    //truyen du lieu khi History Activity bi dong
    @Override
    public void finish() {
        Intent data = new Intent();
        if (this.mPosition != -9999) {
            this.mResult = mListResult.get(mPosition);
        } else {
            this.mResult = null;
        }
        data.putExtra("result", this.mResult);
        data.putExtra("index", this.mPosition);
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    //Show Dialog tuy chon khi chon tung thanh phan trong list
    public void ShowCustomDialog(final int position) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        Button btnAccept = (Button) dialog.findViewById(R.id.btn_accept);
        Button btnDelete = (Button) dialog.findViewById(R.id.btn_delete);
        Button btnCancle = (Button) dialog.findViewById(R.id.btn_cancle);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryActivity.this.mPosition = position;
                dialog.dismiss();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListResult.size() >= 1) {
                    HistoryActivity.this.mPosition = position;
                    mHistoryPresenter.deleteResult(position);
                } else {
                    Toast.makeText(HistoryActivity.this, "Không còn phần tử nào trong lịch sử !", Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                HistoryActivity.this.mPosition = -9999;
            }
        });
        dialog.show();
    }

    @Override
    public void displayDeleteResultFailure() {

    }


    //Hien thi lai danh sach khi xoa thanh cong
    @Override
    public void displayDeleteResultSuccess() {
        //mListResult.remove(this.mPosition);
        mCustomAdapter.notifyDataSetChanged();
        HistoryActivity.this.mPosition = -9999;
    }

    //Hien thi danh sach khi lay danh sach thanh cong
    @Override
    public void displayListResultSuccess(List<Result> lstAnswer) {
        mListResult = lstAnswer;

        mListView = (ListView) findViewById(R.id.lv_list_result);
        mCustomAdapter = new CustomAdapter(HistoryActivity.this, R.layout.row_item, mListResult);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowCustomDialog(position);
            }
        });
    }

    @Override
    public void displayListResultFailure() {

    }

    @Override
    public void displayPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index) {

    }

    @Override
    public void displayPreviousResultFailure() {

    }
}
