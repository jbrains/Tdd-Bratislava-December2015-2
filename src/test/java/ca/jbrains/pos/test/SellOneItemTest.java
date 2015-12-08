package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "EUR 7.95");
        }}));

        sale.onBarcode("12345");

        Assert.assertEquals("EUR 7.95", display.getText());
    }

    @Test
    public void productFoundAmongOthers() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new TreeMap<String, String>() {{
            put("1 anything that comes before 23456", "I don't care what this price is");
            put("23456", "EUR 12.50");
            put("3 anything that comes after 23456", "I don't care what this price is, either");
        }}));

        sale.onBarcode("23456");

        Assert.assertEquals("EUR 12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>()));

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(null));

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private Display display;
        private final Catalog catalog;

        public Sale(Display display, Catalog catalog) {
            this.display = display;
            this.catalog = catalog;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                display.displayScannedEmptyBarcodeMessage();
                return;
            }

            final String price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }

    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        private void displayProductNotFoundMessage(String barcode) {
            this.text = String.format("Product not found for %s", barcode);
        }

        private void displayPrice(String price) {
            this.text = price;
        }

        private void displayScannedEmptyBarcodeMessage() {
            this.text = "Scanning error: empty barcode";
        }
    }

    public static class Catalog {
        private final Map<String, String> pricesByBarcode;

        private Catalog(Map<String, String> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        private String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
