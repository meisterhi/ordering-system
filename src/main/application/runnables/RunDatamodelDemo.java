package application.runnables;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import application.ApplicationContext;
import application.Runnable;
import datamodel.Customer;
import datamodel.Article;
import datamodel.Article.TAX;
import datamodel.Order;

/**
 * The driver creates some {@link Customer}, {@link Article} and {@link Order}
 * objects and prints tables:
 * <pre>
 * Customers:
 * +------+----------------+--------------------+----------------------------+
 * |   ID | NAME           | FIRSTNAMES         | CONTACTS                   |
 * +------+----------------+--------------------+----------------------------+
 * |  100 | Meyer          | Eric               | +49 030 515 141345         |
 * |      |                |                    | eme@gmail.com              |
 * |      |                |                    | fax: 030 234-134651        |
 * +------+----------------+--------------------+----------------------------+
 * |  101 | Bayer          | Anne               | anne24@yahoo.de            |
 * |      |                |                    | (030) 3481-23352           |
 * +------+----------------+--------------------+----------------------------+
 * |  102 | Schulz-Mueller | Tim                | tim2346@gmx.de             |
 * |  103 | Blumenfeld     | Nadine-Ulla        | +49 152-92454              |
 * |  104 | Abdelalim      | Khaled Saad Mohamed| +49 1524-12948210          |
 * +------+----------------+--------------------+----------------------------+
 * 
 * Articles:
 * +------------+-------------------------------+-----+------------+
 * | ID         | DESCRIPTION                   | TAX | UNIT_PRICE |
 * +------------+-------------------------------+-----+------------+
 * | SKU-458362 | Tasse                         |     |        299 |
 * | SKU-693856 | Becher                        |     |        149 |
 * | SKU-638035 | Kanne                         |     |       1999 |
 * | SKU-278530 | Buch "Java"                   |   R |       4990 |
 * | SKU-425378 | Buch "OOP"                    |   R |       7995 |
 * | SKU-300926 | Pfanne                        |     |       4999 |
 * | SKU-663942 | Fahrradhelm                   |     |      16900 |
 * | SKU-583978 | Fahrradkarte                  |   R |        695 |
 * +------------+-------------------------------+-----+------------+
 * 
 * Orders:
 * +------------+-------------------------------+-----+------------+
 * | ORDER-ID   | CUSTOMER                      | CID |      ITEMS |
 * +------------+-------------------------------+-----+------------+
 * | 8592356245 | Meyer, Eric                   | 100 |          4 |
 * | 3563561357 | Bayer, Anne                   | 101 |          2 |
 * | 5234968294 | Meyer, Eric                   | 100 |          1 |
 * | 6135735635 | Blumenfeld, Nadine-Ulla       | 103 |          3 |
 * | 6173043537 | Neumann, Lena                 | 105 |          2 |
 * | 7372561535 | Meyer, Eric                   | 100 |          2 |
 * | 4450305661 | Meyer, Eric                   | 100 |          3 |
 * +------------+-------------------------------+-----+------------+
 * </pre>
 */
public class RunDatamodelDemo implements Runnable {

    /**
     * Method to run the application.
     * @param context {@link ApplicationContext} instance
     * @return chainable self-reference
     */
    @Override
    public Runnable run(ApplicationContext context) {

        /*
         * Customers:
         */
        final Customer eric = new Customer("Eric Meyer")
            .setId(100L)
            .addContact("eme@gmail.com")
            .addContact("+49 030 515 141345")
            .addContact("fax: 030 234-134651")  // duplicate entry
            .addContact("fax: 030 234-134651");

        final Customer anne = new Customer("Bayer, Anne")
            .setId(101L)
            .addContact("anne24@yahoo.de")
            .addContact("(030) 3481-23352");

        final Customer tim = new Customer("Tim Schulz-Mueller")
            .setId(102L)
            .addContact("tim2346@gmx.de");

        final Customer nadine = new Customer("Nadine-Ulla Blumenfeld")
            .setId(103L)
            .addContact("+49 152-92454");

        final Customer khaled = new Customer("Khaled Saad Mohamed Abdelalim")
            .setId(104L)
            .addContact("+49 1524-12948210");

        final Customer lena = new Customer("Lena Neumann")
            .setId(105L)
            .addContact("lena228@gmail.com");

        final List<Customer> customers = new ArrayList<>(List.of(
            eric, anne, tim, nadine, khaled, lena
        ));

        /*
         * Articles:
         */
        var tasse = new Article("SKU-458362", "Tasse", 299);
        var becher = new Article("SKU-693856", "Becher", 149);
        var kanne = new Article("SKU-638035", "Kanne", 1999);
        var teller = new Article("SKU-638035", "Teller", 649);
        var buch_Java = new Article("SKU-278530", "Buch \"Java\"", 4990).tax(TAX.GER_VAT_REDUCED);
        var buch_OOP = new Article("SKU-425378", "Buch \"OOP\"", 7995).tax(TAX.GER_VAT_REDUCED);
        var pfanne = new Article("SKU-300926", "Pfanne", 4999);
        var fahrradhelm = new Article("SKU-663942", "Fahrradhelm", 16900);
        var fahrradkarte = new Article("SKU-583978", "Fahrradkarte", 695).tax(TAX.GER_VAT_REDUCED);

        List<Article> articles = new ArrayList<>(List.of(
            tasse, becher, kanne, buch_Java, buch_OOP, pfanne, fahrradhelm, fahrradkarte
        ));

        /*
         * Orders:
         */
        List<Order> orders = new ArrayList<>(List.of(
            // 
            // Eric's 1st order
            new Order(8592356245L, eric)
                .addItem(teller, 4)     // + item: 4 Teller, 4x 6.49 €
                .addItem(becher, 8)     // + item: 8 Becher, 8x 1.49 €
                .addItem(buch_OOP, 1)   // + item: 1 Buch "OOP", 1x 79.95 €, 7% MwSt (5.23€)
                .addItem(tasse, 4),     // + item: 4 Tassen, 4x 2.99 €
            // 
            // Anne's order
            new Order(3563561357L, anne)
                .addItem(teller, 2)
                .addItem(tasse, 2),
            // 
            // Eric's 2nd order
            new Order(5234968294L, eric)
                .addItem(kanne, 1),
            // 
            // Nadine's order
            new Order(6135735635L, nadine)
                .addItem(teller, 12)
                .addItem(buch_Java, 1)
                .addItem(buch_OOP, 1),
            // 
            // Lena's order
            new Order(6173043537L, lena)
                .addItem(buch_Java, 1)
                .addItem(fahrradkarte, 1),
            // 
            // Eric's 3rd order
            new Order(7372561535L, eric)
                .addItem(fahrradhelm, 1)
                .addItem(fahrradkarte, 1),
            // 
            // Eric's 4th order
            new Order(4450305661L, eric)
                .addItem(tasse, 3)
                .addItem(becher, 3)
                .addItem(kanne, 1)
        ));

        System.out.println(String.format(   // print numbers of objects in collections
            "(%d) Customer objects built.\n" +
            "(%d) Article objects built.\n" +
            "(%d) Order objects built.\n---",
            customers.size(), articles.size(), orders.size()));

        /*
         * Print Customer table
         */
        customerTableFormatter
            .header()
                .row(eric)
                .row(anne)
                .row(tim)
                .row(nadine)
                .row(khaled)
                // .row(customers)
            .footer()
            .print(System.out);
        // 
        System.out.println();

        /*
         * Print Article table
         */
        var atf = articleTableFormatter;
        atf.header();
        articles.forEach(article -> atf.row(article));
        atf.footer()
            .print(System.out);
        // 
        System.out.println();

        /*
         * Print Order table
         */
        var otf = orderTableFormatter;
        otf.header();
        orders.forEach(article -> otf.row(article));
        otf.footer()
            .print(System.out);

        return this;
    }


    /**
     * TableFormatter used to format a table of {@link Customer} objects.
     */
    private final TableFormatter customerTableFormatter =
        TableFormatter.builder()
            // 
            .columns("| ID | NAME | FIRSTNAMES | CONTACTS |")
            .widths(6, 16, 20, 28)
            .alignments("R")
            // 
            // .rowMapper(Customer.class, rowMapper())
            .multiRowMapper(Customer.class, customerMultiRowMapper())
            // 
            .build();

    /**
     * TableFormatter used to format a table of {@link Article} objects.
     */
    private final TableFormatter articleTableFormatter =
        TableFormatter.builder()
            // 
            .columns("| ID | DESCRIPTION | TAX | UNIT_PRICE |")
            .widths(12, 31, 5, 12)
            .alignments("LLRR")
            // 
            .rowMapper(Article.class, articleRowMapper())
            // 
            .build();

    /**
     * TableFormatter used to format a table of {@link Order} objects.
     */
    private final TableFormatter orderTableFormatter =
        TableFormatter.builder()
            // 
            .columns("| ORDER-ID | CUSTOMER | CID | ITEMS |")
            .widths(12, 31, 5, 12)
            .alignments("LLLR")
            // 
            .rowMapper(Order.class, orderRowMapper())
            // 
            .build();

    /**
     * Row mapper maps a {@link Customer} object to {@code String[]} fields
     * that corresond to consecutive fields in one table row.
     * <pre>
     * +------+----------------+--------------------+----------------------------+
     * |  100 | Meyer          | Eric               | +49 030 515 141345         |
     * +------+----------------+--------------------+----------------------------+
     * </pre>
     */
    Function<Customer, String[]> customerRowMapper() {
        return c -> {
            var id = Long.toString(c.getId());
            var name = c.getName().length()==0? " " : c.getName();
            var firstNames = c.getFirstNames().length()==0? " " : c.getFirstNames();
            var contact = c.contact(0).length()==0? " " : c.contact(0);
            return new String[] {id, name, firstNames, contact};
        };
    }

    /**
     * A multi-row mapper maps a {@link Customer} object to multiple table rows,
     * e.g. to include multiple contacts.
     * <pre>
     * +------+----------------+--------------------+----------------------------+
     * |  100 | Meyer          | Eric               | +49 030 515 141345         |
     * |      |                |                    | eme@gmail.com              |
     * |      |                |                    | fax: 030 234-134651        |
     * +------+----------------+--------------------+----------------------------+
     * </pre>
     */
    Function<Customer, String[][]> customerMultiRowMapper() {
        return c -> {
            String[][] rows = null;
            if(c != null) {
                var id = Long.toString(c.getId());
                var name = c.getName().length()==0? " " : c.getName();
                var firstNames = c.getFirstNames().length()==0? " " : c.getFirstNames();
                List<String> contacts = new ArrayList<>();
                c.getContactsAsIterable().forEach(contacts::add);
                int sepLine = contacts.size() > 1? 1 : 0;   // add seperator line for multi-contact rows
                int rowsLen = Math.max(1, contacts.size()) + sepLine;
                rows = new String[rowsLen][];
                for(int i=0; i < rows.length; i++) {
                    var contact = i < contacts.size()? contacts.get(i) : "---";
                    rows[i] = i==0? new String[] {id, name, firstNames, contact} :
                            i==rows.length-1 && sepLine==1? new String[] {"{---}", "{---}", "{---}", "{---}"} :
                            new String[] {" ", " ", " ", contact};
                }
            }
            return rows;
        };
    }

    /**
     * Row mapper maps a {@link Article} object to {@code String[]} fields
     * that corresond to consecutive fields in one table row.
     * <pre>
     * +------------+-------------------------------+-----+------------+
     * | ID         | DESCRIPTION                   | TAX | UNIT_PRICE |
     * +------------+-------------------------------+-----+------------+
     * | SKU-458362 | Tasse                         |     |        299 |
     * | SKU-693856 | Becher                        |     |        149 |
     * | SKU-638035 | Kanne                         |     |       1999 |
     * | SKU-278530 | Buch "Java"                   |   R |       4990 |
     * | SKU-425378 | Buch "OOP"                    |   R |       7995 |
     * +------------+-------------------------------+-----+------------+
     * </pre>
     */
    Function<Article, String[]> articleRowMapper() {
        return a -> {
            var id = a.id();
            var description = a.description();
            var unit_price = Long.toString(a.unitPrice());
            var tax = a.tax()==TAX.GER_VAT_REDUCED? "R" : " ";
            return new String[] {id, description, tax, unit_price};
        };
    }

    /**
     * Row mapper maps a {@link Order} object to {@code String[]} fields
     * that corresond to consecutive fields in one table row.
     * <pre>
     * +------------+-------------------------------+-----+------------+
     * | ORDER-ID   | CUSTOMER                      | CID |      ITEMS |
     * +------------+-------------------------------+-----+------------+
     * | 8592356245 | Meyer, Eric                   | 100 |          4 |
     * | 3563561357 | Bayer, Anne                   | 101 |          2 |
     * | 5234968294 | Meyer, Eric                   | 100 |          1 |
     * | 6135735635 | Blumenfeld, Nadine-Ulla       | 103 |          3 |
     * | 6173043537 | Neumann, Lena                 | 105 |          2 |
     * | 7372561535 | Meyer, Eric                   | 100 |          2 |
     * | 4450305661 | Meyer, Eric                   | 100 |          3 |
     * +------------+-------------------------------+-----+------------+
     * </pre>
     */
    Function<Order, String[]> orderRowMapper() {
        return o -> {
            var id = Long.toString(o.id());
            var customerName = String.format("%s, %s", o.customer().getName(), o.customer().getFirstNames());
            var customerId = Long.toString(o.customer().getId());
            var items = Integer.toString(o.items().size());
            return new String[] {id, customerName, customerId, items};
        };
    }
}
