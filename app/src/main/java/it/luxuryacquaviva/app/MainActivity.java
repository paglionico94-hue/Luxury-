package it.luxuryacquaviva.app;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.graphics.Color;
public class MainActivity extends Activity {
 private WebView web;
 private static final String HOME = "https://headless-nwpdqsrbioi-paglionico94-140c.wix-site-host.com/";
 @Override public void onCreate(Bundle state) {
  super.onCreate(state);
  web = new WebView(this);
  web.setBackgroundColor(Color.rgb(13,13,12));
  setContentView(web);
  WebSettings settings = web.getSettings();
  settings.setJavaScriptEnabled(true);
  settings.setDomStorageEnabled(true);
  settings.setAllowFileAccess(false);
  settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
  CookieManager.getInstance().setAcceptCookie(true);
  CookieManager.getInstance().setAcceptThirdPartyCookies(web,true);
  web.setWebChromeClient(new WebChromeClient());
  web.setWebViewClient(new WebViewClient() {
   @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
    Uri uri=request.getUrl();
    String scheme=uri.getScheme(), host=uri.getHost();
    if(("https".equals(scheme)||"http".equals(scheme)) && host!=null && host.endsWith("wix-site-host.com")) return false;
    try { startActivity(new Intent(Intent.ACTION_VIEW,uri)); } catch(Exception ignored) {}
    return true;
   }
  });
  if(state==null) web.loadUrl(HOME); else web.restoreState(state);
 }
 @Override protected void onSaveInstanceState(Bundle out) { web.saveState(out); super.onSaveInstanceState(out); }
 @Override public void onBackPressed() { if(web.canGoBack()) web.goBack(); else super.onBackPressed(); }
 @Override protected void onDestroy() { web.destroy(); super.onDestroy(); }
}
