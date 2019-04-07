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
public class KeyMainLandscapeFragment extends Fragment implements View.OnClickListener {

    //Cau noi call back MainActivity
    private  IKeyClickListener mIkeyClickListener;
    private Button mBtnAc, mBtnDel, mBtnOpen, mBtnClose, mBtnOpposite, mBtnDevide, mBtnNum1, mBtnNum2, mBtnNum3,
            mBtnMutiply, mBtnNum4, mBtnNum5, mBtnNum6, mBtnMinus, mBtnNum7, mBtnNum8, mBtnNum9, mBtnAdd,
            mBtnNum0, mBtnDot, mBtnSubmit, mBtnSin, mBtnCos, mBtnTan, mBtnFactorial, mBtnPow, mBtnSqrt,
            mBtnPercent, mBtnPi, mBtnMainKey, mBtnInverse;

    public KeyMainLandscapeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_expression_landscape, container, false);
        View view = inflater.inflate(R.layout.fragment_key_main_landscape, container, false);

        mBtnAc = (Button) view.findViewById(R.id.btn_ac);
        mBtnAc.setOnClickListener(this);

        mBtnDel = (Button) view.findViewById(R.id.btn_del);
        mBtnDel.setOnClickListener(this);

        mBtnOpen = (Button) view.findViewById(R.id.btn_open);
        mBtnOpen.setOnClickListener(this);

        mBtnClose = (Button) view.findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);

        mBtnOpposite = (Button) view.findViewById(R.id.btn_opposite);
        mBtnOpposite.setOnClickListener(this);

        mBtnDevide = (Button) view.findViewById(R.id.btn_divide);
        mBtnDevide.setOnClickListener(this);

        mBtnNum1 = (Button) view.findViewById(R.id.btn_num1);
        mBtnNum1.setOnClickListener(this);

        mBtnNum2 = (Button) view.findViewById(R.id.btn_num2);
        mBtnNum2.setOnClickListener(this);

        mBtnNum3 = (Button) view.findViewById(R.id.btn_num3);
        mBtnNum3.setOnClickListener(this);

        mBtnMutiply = (Button) view.findViewById(R.id.btn_multiply);
        mBtnMutiply.setOnClickListener(this);

        mBtnNum4 = (Button) view.findViewById(R.id.btn_num4);
        mBtnNum4.setOnClickListener(this);

        mBtnNum5 = (Button) view.findViewById(R.id.btn_num5);
        mBtnNum5.setOnClickListener(this);

        mBtnNum6 = (Button) view.findViewById(R.id.btn_num6);
        mBtnNum6.setOnClickListener(this);

        mBtnMinus = (Button) view.findViewById(R.id.btn_minus);
        mBtnMinus.setOnClickListener(this);

        mBtnNum7 = (Button) view.findViewById(R.id.btn_num7);
        mBtnNum7.setOnClickListener(this);

        mBtnNum8 = (Button) view.findViewById(R.id.btn_num8);
        mBtnNum8.setOnClickListener(this);

        mBtnNum9 = (Button) view.findViewById(R.id.btn_num9);
        mBtnNum9.setOnClickListener(this);

        mBtnAdd = (Button) view.findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);

        mBtnNum0 = (Button) view.findViewById(R.id.btn_num0);
        mBtnNum0.setOnClickListener(this);

        mBtnDot = (Button) view.findViewById(R.id.btn_dot);
        mBtnDot.setOnClickListener(this);

        mBtnSin = (Button) view.findViewById(R.id.btn_sin);
        mBtnSin.setOnClickListener(this);

        mBtnCos = (Button) view.findViewById(R.id.btn_cos);
        mBtnCos.setOnClickListener(this);

        mBtnTan = (Button) view.findViewById(R.id.btn_tan);
        mBtnTan.setOnClickListener(this);

        mBtnFactorial = (Button) view.findViewById(R.id.btn_factorial);
        mBtnFactorial.setOnClickListener(this);

        mBtnPow = (Button) view.findViewById(R.id.btn_pow);
        mBtnPow.setOnClickListener(this);

        mBtnSqrt = (Button) view.findViewById(R.id.btn_sqrt);
        mBtnSqrt.setOnClickListener(this);

        mBtnPercent = (Button) view.findViewById(R.id.btn_percent);
        mBtnPercent.setOnClickListener(this);

        mBtnPi = (Button) view.findViewById(R.id.btn_pi);
        mBtnPi.setOnClickListener(this);

        mBtnSubmit = (Button) view.findViewById(R.id.btn_submit);
        mBtnSubmit.setOnClickListener(this);

        mBtnInverse = (Button) view.findViewById(R.id.btn_inverse);
        mBtnInverse.setOnClickListener(this);
        return view;
    }

    //Duoc goi ra khi no hien thi len MainActivity
    @Override
    public  void onAttach(Context context) {
        super.onAttach(context);
        mIkeyClickListener = (IKeyClickListener) context;

        int a = 0 ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Bat tat ca su kien click cua button
    @Override
    public void onClick(View v) {
        int id = v.getId();
        mIkeyClickListener.onKeyClick(id);
    }

    //Singleton duy tri duy nhat 1 instance
    public static Fragment newInstance() {
        KeyMainLandscapeFragment myFragment = new KeyMainLandscapeFragment();
        return myFragment;
    }
}