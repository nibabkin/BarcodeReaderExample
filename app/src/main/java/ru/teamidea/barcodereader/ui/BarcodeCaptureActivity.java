package ru.teamidea.barcodereader.ui;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import ru.teamidea.barcodereader.R;

public class BarcodeCaptureActivity extends CaptureActivity {

    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_barcode_capture);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}
