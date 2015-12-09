package ca.jbrains.pos;

public class EnglishLanguageDisplay implements Display {
    private final PostOffice postOffice;

    public EnglishLanguageDisplay(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void displayPrice(Price price) {
        final String text = String.format("EUR %.2f", price.euroValue());
        postOffice.sendMessage(text);
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {
        final String text = String.format("Product not found for %s", barcode);
        postOffice.sendMessage(text);
    }
}
