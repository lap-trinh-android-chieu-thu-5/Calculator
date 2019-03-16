package calculator.team07.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calculator.team07.R;
import calculator.team07.fragment.ExpressionFragment;
import calculator.team07.fragment.IKeyClickListener;
import calculator.team07.fragment.KeyExtendFragment;
import calculator.team07.fragment.KeyMainFragment;

public class MainActivity extends AppCompatActivity implements IKeyClickListener {

    private   String mTextMath = "";
    private   String mTextAns = "0";
    private   String mScreenTextMath = "";

    private ExpressionFragment mExFragment;
    private KeyMainFragment mKeyMainFragment;
    private KeyExtendFragment mKeyExFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mExFragment = new ExpressionFragment();
        mKeyMainFragment = new KeyMainFragment();

        //mKeyExFragment = new KeyExtendFragment();

        fragmentTransaction.add(R.id.top, mExFragment);
        fragmentTransaction.add(R.id.bottom, mKeyMainFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onKeyClick(int keyId) {
        switch (keyId){
            case R.id.btn_ac: {
                break;
            }
            case R.id.btn_open: {
                break;
            }
            case R.id.btn_close: {
                break;
            }
            case R.id.btn_opposite: {
                break;
            }
            case R.id.btn_divide: {
                break;
            }
            case R.id.btn_num1: {
                break;
            }
            case R.id.btn_num2: {
                break;
            }
            case R.id.btn_num3: {
                break;
            }
            case R.id.btn_multiply: {
                break;
            }
            case R.id.btn_num4: {
                break;
            }
            case R.id.btn_num5: {
                break;
            }
            case R.id.btn_num6: {
                break;
            }
            case R.id.btn_minus: {
                break;
            }
            case R.id.btn_num7: {
                break;
            }
            case R.id.btn_num8: {
                break;
            }
            case R.id.btn_num9: {
                break;
            }
            case R.id.btn_add: {
                break;
            }
            case R.id.btn_extend_key: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //fragmentTransaction.replace(R.id.bottom , mKeyExFragment);
                //fragmentTransaction.replace(R.id.bottom , KeyExtendFragment.newInstance());
                fragmentTransaction.replace(R.id.bottom , new KeyExtendFragment());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
            case R.id.btn_num0: {
                break;
            }
            case R.id.btn_dot: {
                break;
            }
            case R.id.btn_submit: {
                break;
            }

            case R.id.btn_sin: {
                break;
            }
            case R.id.btn_cos: {
                break;
            }
            case R.id.btn_tan: {
                break;
            }
            case R.id.btn_factorial: {
                break;
            }
            case R.id.btn_pow: {
                break;
            }
            case R.id.btn_sqrt: {
                break;
            }

            case R.id.btn_percent: {
                break;
            }
            case R.id.btn_pi: {
                break;
            }
            case R.id.btn_main_key: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //fragmentTransaction.replace(R.id.bottom , mKeyMainFragment);
                //fragmentTransaction.replace(R.id.bottom , KeyMainFragment.newInstance());
                fragmentTransaction.replace(R.id.bottom , new KeyMainFragment());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
        }
    }
}
