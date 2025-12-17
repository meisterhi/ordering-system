<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- A1 (SE-2)
-->
# C3: *Datamodel*, Classes: *Customer*, *Article*, *Order*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- 
The assignment presents a simple ordering system with *Customers*, *Orders*,
*OrderItems* and *Articles*:

<img src="https://raw.githubusercontent.com/sgra64/ordering-system/refs/heads/markup/img/customer-1.png" width="660"/>
 -->

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- 
&nbsp;

---

Steps:

- [Fetch Code-drop *c1-customer*](#1-fetch-code-drop-c1-customer)

- [Implement Class *Customer*](#2-implement-class-customer)

- [Sanitize Class *Customer*](#3-sanitize-class-customer)

- [*Customer* Contacts](#4-customer-contacts)

- [*Customer* Names](#5-customer-names)

- [Run all *Customer* Tests](#6-run-all-customer-tests)
-->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- 
&nbsp;

## 1. Fetch Code-drop *c1-customer*
 -->

Create a new local branch `c3-datamodel` and fetch the code-drop from the remote
[*se1-repo(c3-datamodel)*](https://github.com/sgra64/ordering-system/tree/c3-datamodel).

Merge the code-drop (squashed) into your code. Class *Customer.java* is re-used
from the previous assignment.

Re-engineer missing classes *Article.java* and *Order.java* from the output tables
and from the driver code:
[*runnables/RunDatamodelDemo.java*](src/main/application/runnables/RunDatamodelDemo.java)
that generates output tables.

Prices are shown in Cent (as *long*-values) in the *Article* table. The *TAX* column
indicates a reduced tax-rate of `7%` with `"R"` that applies to books while `19%`
apply to other articles. You may use an *enum TAX* in class *Article* to represent
this aspect:

```java
public enum TAX { GER_VAT, GER_VAT_REDUCED, TAX_FREE };
```

*Orders* store references to *Customers* who *"own"* orders. A class *OrderItem*
represents the items ordered with a reference to an *Article* and the number of
units ordered of this *Article*:

```java
class OrderItem {
    private final Article article;
    private final int unitsOrdered;
}
```

The tables produced by the driver code:
[*runnables/RunDatamodelDemo.java*](src/main/application/runnables/RunDatamodelDemo.java)
are:

```
found 'application.properties' with 5 properties
'SE-1 Ordering System' (modular)
executing: 'RunDatamodelDemo.class'
(6) Customer objects built.
(8) Article objects built.
(7) Order objects built.
---
+------+----------------+--------------------+----------------------------+
|   ID | NAME           | FIRSTNAMES         | CONTACTS                   |
+------+----------------+--------------------+----------------------------+
|  100 | Meyer          | Eric               | eme@gmail.com              |
|      |                |                    | +49 030 515 141345         |
|      |                |                    | fax: 030 234-134651        |
+------+----------------+--------------------+----------------------------+
|  101 | Bayer          | Anne               | anne24@yahoo.de            |
|      |                |                    | (030) 3481-23352           |
+------+----------------+--------------------+----------------------------+
|  102 | Schulz-Mueller | Tim                | tim2346@gmx.de             |
|  103 | Blumenfeld     | Nadine-Ulla        | +49 152-92454              |
|  104 | Abdelalim      | Khaled Saad Mohamed| +49 1524-12948210          |
+------+----------------+--------------------+----------------------------+

+------------+-------------------------------+-----+------------+
| ID         | DESCRIPTION                   | TAX | UNIT_PRICE |
+------------+-------------------------------+-----+------------+
| SKU-458362 | Tasse                         |     |        299 |
| SKU-693856 | Becher                        |     |        149 |
| SKU-638035 | Kanne                         |     |       1999 |
| SKU-278530 | Buch "Java"                   |   R |       4990 |
| SKU-425378 | Buch "OOP"                    |   R |       7995 |
| SKU-300926 | Pfanne                        |     |       4999 |
| SKU-663942 | Fahrradhelm                   |     |      16900 |
| SKU-583978 | Fahrradkarte                  |   R |        695 |
+------------+-------------------------------+-----+------------+

+------------+-------------------------------+-----+------------+
| ORDER-ID   | CUSTOMER                      | CID |      ITEMS |
+------------+-------------------------------+-----+------------+
| 8592356245 | Meyer, Eric                   | 100 |          4 |
| 3563561357 | Bayer, Anne                   | 101 |          2 |
| 5234968294 | Meyer, Eric                   | 100 |          1 |
| 6135735635 | Blumenfeld, Nadine-Ulla       | 103 |          3 |
| 6173043537 | Neumann, Lena                 | 105 |          2 |
| 7372561535 | Meyer, Eric                   | 100 |          2 |
| 4450305661 | Meyer, Eric                   | 100 |          3 |
+------------+-------------------------------+-----+------------+
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;


The driver code
[*runnables/RunDatamodelDemo.java*](src/main/application/runnables/RunDatamodelDemo.java):

```java
package application.runnables;

import java.util.*;
import java.util.function.Function;

import application.ApplicationContext;
import application.Runnable;
import datamodel.Customer;
import datamodel.Article;
import datamodel.Article.TAX;
import datamodel.Order;

/**
 * The driver creates some {@link Customer}, {@link Article} and {@link Order}
 * objects and prints tables.
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
```

Apply two patches to files
[*src/resources/application.properties*](src/resources/application.properties):

```patch
diff --git a/src/resources/application.properties b/src/resources/application.properties
index 4cf6c29..19aabfe 100644
--- a/src/resources/application.properties
+++ b/src/resources/application.properties
@@ -6,5 +6,5 @@ application.version = 1.0.0
 application.args = A BB CCC
 application.greeting = true
 #
-# property to define
-application.run = RunCustomerDemo
+# property to define: RunCustomerDemo, RunDatamodelDemo
+application.run = RunDatamodelDemo
```

and to file
[*src/main/application/Application.java*](src/main/application/Application.java):

```patch
diff --git a/src/main/application/Application.java b/src/main/application/Application.java
index 7bba494..bebc378 100644
--- a/src/main/application/Application.java
+++ b/src/main/application/Application.java
@@ -66,6 +66,7 @@ public class Application {
                     Runnable runnable = null;
                     switch(rn) {
                         case "RunCustomerDemo": runnable = new RunCustomerDemo(); break;
+                        case "RunDatamodelDemo": runnable = new RunDatamodelDemo(); break;
                     }
                     if(runnable != null) {
                         context.log().info(String.format("executing: '%s.class'", runnable.getClass().getSimpleName()));
```

Apply patches with *git*:

```sh
git apply <patch-file>      # apply patch
```

Create the missing datamodel classes and run the driver code
[*runnables/RunDatamodelDemo.java*](src/main/application/runnables/RunDatamodelDemo.java).

The code should produce the output tables.
