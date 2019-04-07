package calculator.team07.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import calculator.team07.R;
import calculator.team07.model.Entity.Result;

public class ExpressionLandscapeFragment extends Fragment implements View.OnClickListener {

    private  IKeyClickListener mIkeyClickListener;
    private TextView mTvAnswer;
    private TextView mTvExpression;
    private Button mBtnPrevious;
    private  Button mBtnNext;
    private  Button mBtnHistory;

    public ExpressionLandscapeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_expression_landscape, container, false);
        View view = inflater.inflate(R.layout.fragment_expression_landscape, container, false);

        mTvAnswer = view.findViewById(R.id.text_view_answer);
        mTvExpression = view.findViewById(R.id.text_view_expression);

        mBtnPrevious = view.findViewById(R.id.btn_previous);

        mBtnNext = view.findViewById(R.id.btn_next);

        mBtnHistory = view.findViewById(R.id.btn_history);

        mBtnPrevious.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mBtnHistory.setOnClickListener(this);

        return view;
    }

    @Override
    public  void onAttach(Context context) {
        super.onAttach(context);
        mIkeyClickListener = (IKeyClickListener) context;

        int a = 0;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        mIkeyClickListener.onKeyClick(id);
    }
    public  void setTextViewEpresion(String expression){
        mTvExpression.setText(expression);
    }
    public void setTextViewAnswer(String textAns){
        mTvAnswer.setText(textAns);
    }

}
