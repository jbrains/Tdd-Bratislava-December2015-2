package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.StringReader;

public class ProcessTextAsBarcodesTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    private final BarcodeScannedListener barcodeScannedListener
            = context.mock(BarcodeScannedListener.class);
    private final TextCommandInterpreter textCommandInterpreter
            = new TextCommandInterpreter(barcodeScannedListener);

    @Test
    public void noBarcodes() throws Exception {
        context.checking(new Expectations() {{
            never(barcodeScannedListener);
        }});

        textCommandInterpreter.process(new StringReader(""));
    }

    @Test
    public void oneBarcode() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
        }});

        textCommandInterpreter.process(new StringReader("::barcode::"));
    }

    @Test
    public void severalBarcodes() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 4::");
        }});

        textCommandInterpreter.process(new StringReader(
                "::barcode 1::"
                + "\n::barcode 2::"
                + "\n::barcode 3::"
                + "\n::barcode 4::"
        ));
    }
    @Test
    public void someEmptyLines() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 4::");
        }});

        textCommandInterpreter.process(new StringReader(
                "\n\n\n\n::barcode 1::"
                + "\n\r\n\n\r\n::barcode 2::"
                + "\n\n::barcode 3::"
                + "\n\n\n\n\n\r\r\n\n\n\n::barcode 4::"
                + "\n\n\r\r\r\n\n\n"
        ));
    }

    @Test
    public void sameProductSeveralTimes() throws Exception {
        context.checking(new Expectations() {{
            exactly(4).of(barcodeScannedListener).onBarcode("::barcode::");
        }});

        textCommandInterpreter.process(new StringReader(
                "::barcode::"
                + "\n::barcode::"
                + "\n::barcode::"
                + "\n::barcode::"
        ));
    }

    public interface BarcodeScannedListener {
        // CONTRACT We assume barcode is not empty.
        void onBarcode(String barcode);
    }
}
