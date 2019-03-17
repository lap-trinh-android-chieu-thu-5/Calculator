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

    private List<Result> listResult;
    private CustomAdapter customAdapter;
    private ListView listView;
    private int position=-9999;
    private Result result;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = (ListView)findViewById(R.id.lv_list_result);
        btnBack = (Button) findViewById(R.id.btn_back);
        KhoiTao();
        customAdapter = new CustomAdapter(HistoryActivity.this,R.layout.row_item,listResult);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowCustomDialog(position);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void KhoiTao()
    {
        //TODO lay tam truc tiep, test xong thi xoa
        listResult = CalculatorHandle.sLstAnswer;
    }

    @Override
    public void finish() {

        // Chuẩn bị dữ liệu Intent.
        Intent data = new Intent();
        if (this.position!=-9999)
        {
            this.result = listResult.get(position);
            this.position=-9999;

        }
        else
        {
            this.result=null;
        }
        data.putExtra("feedback", this.result);
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    public void ShowCustomDialog(final int position)
    {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnOk = (Button)dialog.findViewById(R.id.btn_accept);
        Button btnNo = (Button)dialog.findViewById(R.id.btn_delete);
        Button btnCancle = (Button)dialog.findViewById(R.id.btn_cancle);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryActivity.this.position=position;
                dialog.dismiss();
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listResult.size()>=1)
                {
                    HistoryActivity.this.position=position;
                    Toast.makeText(HistoryActivity.this,"lay position"+String.valueOf(position),Toast.LENGTH_LONG).show();listResult.remove(position);
                    customAdapter.notifyDataSetChanged();
                    HistoryActivity.this.position=-9999;
                }
                else
                {
                    Toast.makeText(HistoryActivity.this,"ko con phan tu",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                HistoryActivity.this.position=-9999;
            }
        });
        dialog.show();
    }
}
