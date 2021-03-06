package calculator.team07.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import calculator.team07.R;
import calculator.team07.fragment.ExpressionFragment;
import calculator.team07.fragment.ExpressionLandscapeFragment;
import calculator.team07.fragment.IKeyClickListener;
import calculator.team07.fragment.KeyExtendFragment;
import calculator.team07.fragment.KeyMainFragment;
import calculator.team07.fragment.KeyMainLandscapeFragment;
import calculator.team07.model.Entity.Result;
import calculator.team07.model.SubModel.CalculatorHandle;
import calculator.team07.presenter.IMainView;
import calculator.team07.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IKeyClickListener, IMainView {

    //Kiem tra man hinh xoay ngang hay doc
    private boolean mIsLandscape = false;

    //request code nhan tu history
    private final static int REQUEST_CODE = 200;

    //vi tri hien tai cua bieu thuc trong lich su
    private int mIndex = -1;

    //chuoi de tinh toan
    private String mTextMath = "";

    //chuoi ket qua de hien thi
    private String mTextAns = "0";

    //chuoi bieu thuc hien thi
    private String mScreenTextMath = "";

    private ExpressionFragment mExFragment;
    private KeyMainFragment mKeyMainFragment;
    private KeyExtendFragment mKeyExFragment;

    private ExpressionLandscapeFragment mExLandscapeFragment;
    private KeyMainLandscapeFragment mKeyMainLandscapeFragment;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Khoi tao mac dinh, luon co trong 2 man hinh
        mMainPresenter = new MainPresenter(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        //Kiem tra ngang doc
        mIsLandscape = isHorizontal();

        if (mIsLandscape == false) {

            //set layout main
            setContentView(R.layout.activity_main);

            //khoi tao fragment phu hop
            mExFragment = new ExpressionFragment();
            mKeyMainFragment = new KeyMainFragment();
            mKeyExFragment = new KeyExtendFragment();

            //paste fragment len main
            fragmentTransaction.add(R.id.top, mExFragment);
            fragmentTransaction.add(R.id.bottom, mKeyMainFragment);
            fragmentTransaction.commit();

        } else {
            //set layout main
            setContentView(R.layout.activity_main_landscape);

            //khoi tao fragment phu hop
            mExFragment = new ExpressionFragment();
            mKeyMainLandscapeFragment = new KeyMainLandscapeFragment();

            //paste fragment len main
            fragmentTransaction.add(R.id.top, mExFragment);
            fragmentTransaction.add(R.id.bottom, mKeyMainLandscapeFragment);
            fragmentTransaction.commit();
        }

        int b = 0;

        //Set lai gia tri khi xoay man hinh
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("mIndex");
            mTextMath = savedInstanceState.getString("mTextMath");
            mScreenTextMath = savedInstanceState.getString("mScreenTextMath");
            mTextAns = savedInstanceState.getString("mTextAns");
        }

        //Set ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Calculator");

    }

    //set text len man hinh
    @Override
    protected void onResume() {
        super.onResume();

        //binh thuong
        if (mExFragment != null) {
            mExFragment.setTextViewEpresion(mScreenTextMath);
            mExFragment.setTextViewAnswer(mTextAns);
        } else { //landscape
            if (mExLandscapeFragment != null) {
                mExLandscapeFragment.setTextViewEpresion(mScreenTextMath);
                mExLandscapeFragment.setTextViewAnswer(mTextAns);
            }
        }

    }

    //ham kiem tra xoay ngang doc
    private boolean isHorizontal() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        } else {
            return false;
        }
    }


    //luu lai gia tri truoc khi xoay man hinh
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt("mIndex", mIndex);
        savedInstanceState.putString("mScreenTextMath", mScreenTextMath);
        savedInstanceState.putString("mTextMath", mTextMath);
        savedInstanceState.putString("mTextAns", mTextAns);

        super.onSaveInstanceState(savedInstanceState);
    }

    //thuc hien tinh toan
    private void submit() {
        mMainPresenter.calculate(mTextMath, mScreenTextMath, mTextAns);
    }


    //bat cac phim duoc tra ve tu fragment thong qua IKeyClickListener
    @Override
    public void onKeyClick(int keyId) {
        int a = 0;
        switch (keyId) {
            case R.id.btn_ac: {
                mTextMath = "";
                mScreenTextMath = "";
                mTextAns = "0";


                mExFragment.setTextViewAnswer(mTextAns);
                mExFragment.setTextViewEpresion(mTextAns);

                break;
            }
            case R.id.btn_del: {
                if (mScreenTextMath.length() > 0) {
                    char c = mTextMath.charAt(mTextMath.length() - 1);
                    if (mTextMath.length() > 1 && c == '(' && mTextMath.charAt(mTextMath.length() - 2) == '^') {
                        mScreenTextMath = mScreenTextMath.substring(0, mScreenTextMath.length() - 2);
                        mTextMath = mTextMath.substring(0, mTextMath.length() - 2);
                    } else {
                        if (mTextMath.length() > 1 && c == '(' && (mTextMath.charAt(mTextMath.length() - 2) == 's'
                                || mTextMath.charAt(mTextMath.length() - 2) == 'c' || mTextMath.charAt(mTextMath.length() - 2) == 't')) {

                            mTextMath = mTextMath.substring(0, mTextMath.length() - 2);
                            mScreenTextMath = mScreenTextMath.substring(0, mScreenTextMath.length() - 4);
                        } else {
                            mTextMath = mTextMath.substring(0, mTextMath.length() - 1);
                            mScreenTextMath = mScreenTextMath.substring(0, mScreenTextMath.length() - 1);
                        }
                    }
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_open: {
                if (mTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "(";
                    mScreenTextMath += "(";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_close: {
                if (mTextMath.length() < 48 && mTextMath.length() > 0) {
                    mTextMath += ")";
                    mScreenTextMath += ")";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_opposite: {
                //TODO chua viet ham cho nut nay, hoan thanh trong version tiep theo
                if (mTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mTextMath = mScreenTextMath = mTextAns;

                        if (mScreenTextMath.charAt(0) == '-') {
                            mScreenTextMath = mScreenTextMath.replace("-", "");
                        } else {
                            mScreenTextMath = "-" + mScreenTextMath;
                        }
                        mTextMath = mScreenTextMath;
                        CalculatorHandle.sCheckSubmit = false;
                        mExFragment.setTextViewEpresion(mScreenTextMath);
                    }
                }
                break;
            }
            case R.id.btn_divide: {
                if (mScreenTextMath.length() < 48 && mScreenTextMath.length() > 0) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "/";
                    mScreenTextMath += "/";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num1: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "1";
                    mScreenTextMath += "1";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num2: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "2";
                    mScreenTextMath += "2";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num3: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "3";
                    mScreenTextMath += "3";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_multiply: {
                if (mScreenTextMath.length() < 48 && mScreenTextMath.length() > 0) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "*";
                    mScreenTextMath += "*";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num4: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "4";
                    mScreenTextMath += "4";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num5: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "5";
                    mScreenTextMath += "5";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num6: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "6";
                    mScreenTextMath += "6";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_minus: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "-";
                    mScreenTextMath += "-";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num7: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "7";
                    mScreenTextMath += "7";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num8: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "8";
                    mScreenTextMath += "8";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_num9: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "9";
                    mScreenTextMath += "9";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_add: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "+";
                    mScreenTextMath += "+";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_extend_key: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.bottom, mKeyExFragment);
                //fragmentTransaction.replace(R.id.bottom , KeyExtendFragment.newInstance());
                //fragmentTransaction.replace(R.id.bottom , new KeyExtendFragment());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
            case R.id.btn_num0: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "0";
                    mScreenTextMath += "0";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_dot: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    if (mTextMath.equals("")) {
                        mTextMath += "0";
                        mScreenTextMath = mTextMath;
                    }

                    mTextMath += ".";
                    mScreenTextMath += ".";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_submit: {
                submit();
                break;
            }

            case R.id.btn_sin: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "s(";
                    mScreenTextMath += "Sin(";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_cos: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "c(";
                    mScreenTextMath += "Cos(";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_tan: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "t(";
                    mScreenTextMath += "Tan(";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_factorial: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "!";
                    mScreenTextMath += "!";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_pow: {
                if (mScreenTextMath.length() < 48 && mScreenTextMath.length() > 0) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "^(";
                    mScreenTextMath += "^(";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_sqrt: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "@";
                    mScreenTextMath += "√";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }

            case R.id.btn_percent: {
                if (mScreenTextMath.length() == 0) {
                    mScreenTextMath = "0";
                }
                mScreenTextMath = "(" + mScreenTextMath + ")%";
                mExFragment.setTextViewEpresion(mScreenTextMath);

                if (CalculatorHandle.sCheckSubmit == true) {
                    submit();
                }
                mTextMath = mTextAns + "/100";
                submit();
                break;
            }
            case R.id.btn_pi: {
                if (mScreenTextMath.length() < 48) {
                    if (CalculatorHandle.sCheckSubmit == true) {
                        mScreenTextMath = mTextMath = "";
                        CalculatorHandle.sCheckSubmit = false;
                    }
                    mTextMath += "π";
                    mScreenTextMath += "π";
                }
                mExFragment.setTextViewEpresion(mScreenTextMath);
                break;
            }
            case R.id.btn_inverse: {
                if (mScreenTextMath.length() == 0) {
                    mScreenTextMath = "0";
                }
                mScreenTextMath = "1/(" + mScreenTextMath + ")";
                mExFragment.setTextViewEpresion(mScreenTextMath);

                if (CalculatorHandle.sCheckSubmit == false) {
                    submit();
                }
                mTextMath = "1/" + mTextAns;
                submit();
                break;
            }
            case R.id.btn_main_key: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //fragmentTransaction.replace(R.id.bottom , mKeyMainFragment);
                //fragmentTransaction.replace(R.id.bottom , KeyMainFragment.newInstance());
                fragmentTransaction.replace(R.id.bottom, new KeyMainFragment());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }


            //Su ly xem ket qua truoc sau tren main
            case R.id.btn_previous: {
                mMainPresenter.getPreviousResult(mIndex - 1);
                break;
            }

            case R.id.btn_next: {
                mMainPresenter.getPreviousResult(mIndex + 1);
                break;
            }
            case R.id.btn_history: {
                Intent intentHistory = new Intent(this, HistoryActivity.class);
                startActivityForResult(intentHistory, REQUEST_CODE);
                break;
            }
        }
    }

    //hien thi khi callback tinh toan fail
    @Override
    public void displayCalculatorFailure(String msgError) {
        mTextAns = "0";
        mExFragment.setTextViewAnswer(msgError);
    }

    //hien thi khi callback tinh toan thanh cong
    @Override
    public void displayCalculatorSuccess(String textAns) {
        mTextAns = textAns;
        mExFragment.setTextViewAnswer(textAns);
        mIndex++;
    }

    @Override
    public void displayPreviousResultFailure(String msgError) {
        return;
    }


    //hien thi ket qua truoc do thanh cong
    @Override
    public void displayPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index) {
        mScreenTextMath = screenTextMath;
        mTextMath = textMath;
        mTextAns = textAns;
        mIndex = index;

        mExFragment.setTextViewEpresion(mScreenTextMath);
        mExFragment.setTextViewAnswer(textAns);
    }

    //nhan ket qua tra ve khi dong HistoryActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            Result result = (Result) data.getSerializableExtra("result");
            int index = (int) data.getSerializableExtra("index");

            if (result != null) {
                this.mIndex = index;

                mScreenTextMath = result.ScreenMath;
                mTextMath = result.TextMath;
                mTextAns = result.Answer;
                mIndex = index;

                mExFragment.setTextViewEpresion(mScreenTextMath);
                mExFragment.setTextViewAnswer(mTextAns);
            }
        }
    }
}
