package ca.jbrains.pos;

public interface BarcodeScannedListener {
    // CONTRACT We assume barcode is not empty.
    void onBarcode(String barcode);
}
