package edtarawedding.elmund.io.taraandedswedding;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;


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

    public void openMap(View view){
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 51.760453, -0.209228);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        view.getContext().startActivity(intent);
    }
}
