package ca.jbrains.pos.test;

import ca.jbrains.pos.test.SellOneItemControllerTest.Catalog;
import ca.jbrains.pos.test.SellOneItemControllerTest.Price;
import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        final Price price = Price.cents(795);
        final Catalog catalog = createCatalogWith("the barcode", price);
        Assert.assertEquals(price, catalog.findPrice("the barcode"));
    }

    // Make sure that there are other barcodes and other prices,
    // so that we can feel confident that the price we find
    // matches the barcode.
    protected abstract Catalog createCatalogWith(String barcode, Price price);

    @Test
    public void productNotFound() throws Exception {
        Assert.assertEquals(
                null,
                createCatalogWithout("the barcode").findPrice("the barcode"));
    }

    protected abstract Catalog createCatalogWithout(String barcode);
}
