package edtarawedding.elmund.io.taraandedswedding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Password extends Activity implements ZXingScannerView.ResultHandler {
private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button buttonTemplate = (Button) findViewById(R.id.passwordButton);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view


        Button yourButton;
        yourButton = new Button(this);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) buttonTemplate.getLayoutParams();
        yourButton.setLayoutParams(lp);
        yourButton.setText("ENTER PASSWORD");
        mScannerView.addView(yourButton);

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("lol", "lol");
            }
        });
    }

    public void openSesame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        // Do something with the result here
        Log.v("lol", result.getText()); // Prints scan results
        Log.v("lol", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        if(validatePassword(result.getText())) {
            openSesame();
            mScannerView.removeAllViews();
        }else{
            Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
            mScannerView.startCamera();
        }


    }

    private boolean validatePassword(String password){
        return password.equals("STARWARSDAY");
    }
}
