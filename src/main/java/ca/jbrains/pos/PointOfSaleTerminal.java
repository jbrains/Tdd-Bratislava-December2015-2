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
                        new EnglishLanguageDisplay(
                                new LcdGateway(
                                        "localhost",
                                        7000,
                                        "UTF-8"
                                )
                        )
                )
        ).interpretCommands(new InputStreamReader(System.in));
    }
}
