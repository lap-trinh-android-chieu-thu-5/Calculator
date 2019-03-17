package calculator.team07.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

public class HistoryActivity extends AppCompatActivity {

    private List<Result> mListResult;
    private CustomAdapter mCustomAdapter;
    private ListView mListView;
    private int mPosition = -9999;
    private Result mResult;
    private Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mListView = (ListView) findViewById(R.id.lv_list_result);
        mBtnBack = (Button) findViewById(R.id.btn_back);

        initList();

        mCustomAdapter = new CustomAdapter(HistoryActivity.this, R.layout.row_item, mListResult);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowCustomDialog(position);
            }
        });
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initList() {
        //TODO lay tam truc tiep, test xong thi xoa
        mListResult = CalculatorHandle.sLstAnswer;
    }

    @Override
    public void finish() {

        Intent data = new Intent();
        if (this.mPosition != -9999) {
            this.mResult = mListResult.get(mPosition);
            this.mPosition = -9999;
        } else {
            this.mResult = null;
        }
        data.putExtra("feedback", this.mResult);
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

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
                    mListResult.remove(position);
                    mCustomAdapter.notifyDataSetChanged();
                    HistoryActivity.this.mPosition = -9999;
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
}
