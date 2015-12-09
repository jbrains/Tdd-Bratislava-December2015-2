package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
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

    @Test
    public void severalBarcodes() throws Exception {
        final BarcodeScannedListener barcodeScannedListener
                = context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 4::");
        }});

        process(barcodeScannedListener, new StringReader("::barcode 1::"
                + "\n::barcode 2::"
                + "\n::barcode 3::"
                + "\n::barcode 4::"
        ));
    }

    private void process(
            BarcodeScannedListener barcodeScannedListener,
            Reader source) throws IOException {

        new BufferedReader(source).lines()
                .forEach(barcodeScannedListener::onBarcode);
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
