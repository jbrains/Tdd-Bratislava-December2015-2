package ca.jbrains.pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextCommandInterpreter {
    private final BarcodeScannedListener barcodeScannedListener;

    public TextCommandInterpreter(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void interpretCommands(Reader source) throws IOException {
        new BufferedReader(source).lines()
                .filter((line) -> !line.isEmpty())
                .forEach(barcodeScannedListener::onBarcode);
    }
}
