package edtarawedding.elmund.io.taraandedswedding;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Password extends Activity implements ZXingScannerView.ResultHandler {
private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        
        setContentView(R.layout.activity_password);

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        boolean logged_in = pref.getBoolean("logged_in" ,false);

        if(!logged_in) {
            Button buttonTemplate = (Button) findViewById(R.id.passwordButton);


            setContentView(mScannerView);                // Set the scanner view as the content view


            Button yourButton;
            yourButton = new Button(this);

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) buttonTemplate.getLayoutParams();
            yourButton.setLayoutParams(lp);
            yourButton.setText("ENTER PASSWORD");
            mScannerView.addView(yourButton);

            yourButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    passwordDialogShow();
                }
            });
        }else{
            openSesame();
        }
    }

    public void openSesame() {

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("logged_in", true);
        editor.commit();

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
        return password.toUpperCase().equals("STARWARSDAY");
    }

    private void passwordDialogShow(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        b.setView(input);

        b.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (validatePassword(input.getText().toString())) {
                            openSesame();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        b.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = b.create();
        alertDialog.setTitle("PASSWORD");
        alertDialog.setMessage("Enter Password");




        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        alertDialog.show();
    }
}
