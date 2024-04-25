package com.phonegap.plugins.barcodescanner;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraPreview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraPreview extends Fragment {
    public interface BarcodeScanInterface {
        void onBarcodeScanned(String barcodeData);
    }
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;


    public CameraPreview() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CameraPreview.
     */
    // TODO: Rename and change types and number of parameters
    public static CameraPreview newInstance() {
        CameraPreview fragment = new CameraPreview();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    private BarcodeCallback callback = new BarcodeCallback() {
//        @Override
//        public void barcodeResult(BarcodeResult result) {
//            if (result.getText() != null) {
//                barcodeScannerView.pause();  // Pause scanning
//                handleDecodeInternally(result);  // Handle the scan result
//            }
//        }
//    };

    private void handleDecodeInternally(BarcodeResult result) {
        // Here you can handle the scan result as needed
        Log.i("ScanResult", "Scanned code: " + result.getText());
//        return result.getText();
        // Optionally, send the result back to the JavaScript part of your Cordova app
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_camera, container, false);
        barcodeScannerView = rootView.findViewById(com.google.zxing.client.android.R.id.zxing_barcode_scanner);
        barcodeScannerView.initializeFromIntent(getActivity().getIntent());
        barcodeScannerView.setStatusText("");
        capture = new CaptureManager(getActivity(), barcodeScannerView);
        capture.initializeFromIntent(getActivity().getIntent(), savedInstanceState);
        barcodeScannerView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if(result != null) {
                    handleBarcodeResult(result);
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
            }
        });

        return rootView;
    }

    private void handleBarcodeResult(BarcodeResult result) {
        barcodeScannerView.pause();

        Activity activity = getActivity();
        if (activity instanceof BarcodeScanInterface) {
            ((BarcodeScanInterface)activity).onBarcodeScanned(result.getText());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }
}