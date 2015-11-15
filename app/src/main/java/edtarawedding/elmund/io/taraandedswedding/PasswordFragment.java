package edtarawedding.elmund.io.taraandedswedding;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(getActivity());   // Programmatically initialize the scanner view


        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);

        Button b = (Button) getActivity().findViewById(R.id.passwordButton);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                passwordDialogShow();
            }
        });

        boolean logged_in = pref.getBoolean("logged_in" ,false);

        if(!logged_in) {

        }else{
            openSesame();
        }

        return mScannerView;
    }

    public void openSesame() {

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("logged_in", true);
        editor.commit();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
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
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
            mScannerView.startCamera();
        }


    }

    private boolean validatePassword(String password){
        return password.toUpperCase().equals("STARWARSDAY");
    }

    private void passwordDialogShow(){
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        final EditText input = new EditText(getActivity());
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
                            Toast.makeText(getActivity().getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
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
