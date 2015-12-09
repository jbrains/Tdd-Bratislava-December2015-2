package ca.jbrains.pos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PointOfSaleTerminal {
    public static void main(String[] args) throws IOException {
        new TextCommandInterpreter(
            new SellOneItemController(
                    new InMemoryCatalog(
                            new HashMap<String, Price>() {{
                                put("8586009260802", Price.cents(80));
                                put("4028700035814", Price.cents(1000));
                            }}
                    ),
                    new Display() {
                        @Override
                        public void displayPrice(Price price) {
                            final String text = String.format("EUR %.2f", price.euroValue());
                            System.out.println(text);
                        }

                        @Override
                        public void displayProductNotFoundMessage(String barcode) {
                            final String text = String.format("Product not found for %s", barcode);
                            System.out.println(text);
                        }
                    }
            )
        ).interpretCommands(new InputStreamReader(System.in));
    }
}
