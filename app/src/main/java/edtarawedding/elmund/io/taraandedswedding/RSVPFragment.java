package edtarawedding.elmund.io.taraandedswedding;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

import android.util.Log;
import android.webkit.WebViewFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class RSVPFragment extends Fragment {

    public RSVPFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String url = getResources().getString(R.string.rsvp_url);

        View rootView = inflater.inflate(R.layout.fragment_rsvp, container, false);

        final WebView wbvBrowser = (WebView) rootView.findViewById(R.id.webView);

        wbvBrowser.getSettings().setJavaScriptEnabled(true);

        wbvBrowser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setVisibility(View.VISIBLE);

                String javascript = "(function() { document.getElementsByClassName('ss-footer')[0].style.display = 'none' })();";

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    wbvBrowser.evaluateJavascript(javascript,null);
                } else {
                    wbvBrowser.loadUrl("javascript:"+ javascript);
                }

            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        wbvBrowser.loadUrl(url);

        return rootView;
    }

}
