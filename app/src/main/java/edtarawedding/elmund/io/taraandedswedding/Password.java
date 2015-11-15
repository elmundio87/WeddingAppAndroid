package edtarawedding.elmund.io.taraandedswedding;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


public class Password extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        PasswordFragment fragment = new PasswordFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

}
