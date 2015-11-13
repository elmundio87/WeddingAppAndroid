package edtarawedding.elmund.io.taraandedswedding;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A placeholder fragment containing a simple view.
 */
public class MapFragment extends Fragment {

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        String url = "file:///android_asset/map.html";

        View rootView = inflater.inflate(R.layout.fragment_rsvp, container, false);

        WebView wbvBrowser = (WebView) rootView.findViewById(R.id.webView);

        wbvBrowser.getSettings().setJavaScriptEnabled(true);
        wbvBrowser.getSettings().setBuiltInZoomControls(true);
        wbvBrowser.getSettings().setDisplayZoomControls(false);

        wbvBrowser.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        wbvBrowser.loadUrl(url);

        return rootView;
    }
}
