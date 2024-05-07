package com.phonegap.plugins.barcodescanner;
//package com.google.zxing.qrcode.encoder;

public class EncodeActivity extends android.app.Activity {
    private static final java.lang.String TAG = null;
    private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
    private static final java.util.regex.Pattern NOT_ALPHANUMERIC = null;
    private static final java.lang.String USE_VCARD_KEY = "USE_VCARD";
    private com.google.zxing.qrcode.encoder.Encoder qrCodeEncoder;

    public EncodeActivity() { /* compiled code */ }

    public void onCreate(android.os.Bundle icicle) { /* compiled code */
        super.onCreate(icicle);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) { /* compiled code */
        return false;
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) { /* compiled code */
        return false;
    }

    private void share() { /* compiled code */ }

    private static java.lang.CharSequence makeBarcodeFileName(java.lang.CharSequence contents) { /* compiled code */
        return contents;
    }

    protected void onResume() { /* compiled code */
        super.onResume();
    }

    private void showErrorMessage(int message) { /* compiled code */ }
}