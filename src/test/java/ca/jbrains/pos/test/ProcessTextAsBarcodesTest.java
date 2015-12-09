package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

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

    private void process(
            BarcodeScannedListener barcodeScannedListener,
            Reader source) {
    }

    public interface BarcodeScannedListener {}
}
