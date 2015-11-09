package edtarawedding.elmund.io.taraandedswedding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.util.Log;

/**
 * A placeholder fragment containing a simple view.
 */
public class RSVPFragment extends Fragment {

    public RSVPFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rsvp , container, false);
        WebView mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.loadUrl("https://www.google.com");
        Log.i("MyActivity", "lol ");
        return inflater.inflate(R.layout.fragment_rsvp, container, false);
    }
}
