package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;
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

}
