package ca.jbrains.pos.test;

import ca.jbrains.pos.test.SellOneItemControllerTest.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        final Price price = Price.cents(795);
        final InMemoryCatalog catalog
                = new InMemoryCatalog(Collections.singletonMap("12345", price));
        Assert.assertEquals(price, catalog.findPrice("12345"));
    }

    @Test
    public void productFoundAmongOthers() throws Exception {
        final Price price = Price.cents(795);
        final InMemoryCatalog catalog
                = new InMemoryCatalog(new TreeMap<String, Price>() {{
            put("definitely not the barcode", Price.cents(-762));
            put("the barcode", price);
            put("also definitely not the barcode", Price.cents(-762));
            put("z still totally not the barcode", Price.cents(-762));

        }});

        Assert.assertEquals(price, catalog.findPrice("the barcode"));
    }

    @Test
    public void productNotFound() throws Exception {
        final InMemoryCatalog catalog
                = new InMemoryCatalog(new HashMap<String, Price>() {{
            put("definitely not the barcode", Price.cents(-762));
            put("also definitely not the barcode", Price.cents(-762));
            put("z still totally not the barcode", Price.cents(-762));

        }});

        Assert.assertEquals(null, catalog.findPrice("the barcode"));
    }

    public static class InMemoryCatalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
