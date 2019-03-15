package calculator.team07.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calculator.team07.R;
import calculator.team07.fragment.ExpressionFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ExpressionFragment frm = new ExpressionFragment();
        fragmentTransaction.add(R.id.top, frm);
        fragmentTransaction.commit();
    }
}
