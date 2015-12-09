package ca.jbrains.pos.test;

import ca.jbrains.pos.test.SellOneItemControllerTest.Catalog;
import ca.jbrains.pos.test.SellOneItemControllerTest.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {
    @Override
    protected Catalog createCatalogWith(String barcode, Price price) {
        return new InMemoryCatalog(new TreeMap<String, Price>() {{
            put("definitely not " + barcode, Price.cents(-762));
            put(barcode, price);
            put("also definitely not " + barcode, Price.cents(-762));
            put("z still totally not " + barcode, Price.cents(-762));
        }});
    }

    @Override
    protected Catalog createCatalogWithout(final String barcode) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("definitely not " + barcode, Price.cents(-762));
            put("also definitely not " + barcode, Price.cents(-762));
            put("z still totally not " + barcode, Price.cents(-762));
        }});
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
