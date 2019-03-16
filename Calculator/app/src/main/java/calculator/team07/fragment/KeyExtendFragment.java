package calculator.team07.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import calculator.team07.R;

public class KeyExtendFragment extends Fragment implements View.OnClickListener {

    private  IKeyClickListener mIkeyClickListener;
    private Button mBtnAc, mBtnDel, mBtnSin, mBtnCos, mBtnTan, mBtnFactorial, mBtnPow, mBtnSqrt,
            mBtnPercent, mBtnPi, mBtnMainKey, mBtnSubmit;

    public KeyExtendFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_key_extend, container, false);

        mBtnAc = (Button) view.findViewById(R.id.btn_ac);
        mBtnAc.setOnClickListener(this);

        mBtnDel = (Button) view.findViewById(R.id.btn_del);
        mBtnDel.setOnClickListener(this);

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

        mBtnPercent = (Button) view.findViewById(R.id.percent);
        mBtnPercent.setOnClickListener(this);

        mBtnPi = (Button) view.findViewById(R.id.btn_pi);
        mBtnPi.setOnClickListener(this);

        mBtnMainKey = (Button) view.findViewById(R.id.btn_main_key);
        mBtnMainKey.setOnClickListener(this);

        mBtnSubmit = (Button) view.findViewById(R.id.btn_submit);
        mBtnSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public  void onAttach(Context context) {
        super.onAttach(context);
        mIkeyClickListener = (IKeyClickListener) context;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        mIkeyClickListener.onKeyClick(id);
    }
    public static Fragment newInstance() {
        KeyExtendFragment myFragment = new KeyExtendFragment();
        return myFragment;
    }
}
