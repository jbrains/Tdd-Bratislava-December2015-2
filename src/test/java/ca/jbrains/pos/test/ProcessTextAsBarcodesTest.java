package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class ProcessTextAsBarcodesTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void noBarcodes() throws Exception {
        final BarcodeScannedListener barcodeScannedListener
                = context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            never(barcodeScannedListener);
        }});

        process(barcodeScannedListener, new StringReader(""));
    }

    @Test
    public void oneBarcode() throws Exception {
        final BarcodeScannedListener barcodeScannedListener
                = context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
        }});

        process(barcodeScannedListener, new StringReader("::barcode::"));
    }

    private void process(
            BarcodeScannedListener barcodeScannedListener,
            Reader source) throws IOException {

        final String line = new BufferedReader(source).readLine();
        if (line != null)
            barcodeScannedListener.onBarcode(line);
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
