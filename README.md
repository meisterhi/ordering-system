<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- A1 (SE-2)
-->
<<<<<<< HEAD
# C2: *Customer* class

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
=======
# C3: *Datamodel*, Classes: *Customer*, *Article*, *Order*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- 
>>>>>>> origin/c3-datamodel
The assignment presents a simple ordering system with *Customers*, *Orders*,
*OrderItems* and *Articles*:

<img src="https://raw.githubusercontent.com/sgra64/ordering-system/refs/heads/markup/img/customer-1.png" width="660"/>
<<<<<<< HEAD


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

=======
 -->

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- 
>>>>>>> origin/c3-datamodel
&nbsp;

---

Steps:

<<<<<<< HEAD
- [Fetch Code-drop *c2-customer*](#1-fetch-code-drop-c2-customer)
=======
- [Fetch Code-drop *c1-customer*](#1-fetch-code-drop-c1-customer)
>>>>>>> origin/c3-datamodel

- [Implement Class *Customer*](#2-implement-class-customer)

- [Sanitize Class *Customer*](#3-sanitize-class-customer)

- [*Customer* Contacts](#4-customer-contacts)

- [*Customer* Names](#5-customer-names)

- [Run all *Customer* Tests](#6-run-all-customer-tests)
<<<<<<< HEAD


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Fetch Code-drop *c2-customer*

Fetch a code-drop from branch
[*c2-customer*](https://github.com/sgra64/ordering-system/tree/c2-customer)
from the [*se1-repo*](https://github.com/sgra64/ordering-system.git).

Make sure you have added the remote *se1-repo*:

```sh
git remote -v
```
```
se1-repo        https://github.com/sgra64/ordering-system (fetch)
se1-repo        https://github.com/sgra64/ordering-system (push)
```

```sh
# fetch branch 'c2-customer' from 'se1-repo'
git fetch se1-repo c2-customer

# show new remote branch
git branch -avv
```
```
remotes/origin/c2-customer    363dcbb add README.md
```

Create new local branch `c2-customer` off the *main* branch and show that
you are on that branch:

```sh
git branch
```
```
c2-customer
```

Chek-out paths `src/main` and `src/resources` from the code-drop from the
new branch and see the new additions:

```sh
git checkout se1-repo/c2-customer -- src/main
git checkout se1-repo/c2-customer -- src/resources

git status                      # see the delta of the code-drop

find src/main src/resources     # show new content under 'src'
```

Understand the new additions and the structure of the project, specifically:

- [*Application.java*](src/main/application/Application.java)

- [*ApplicationContext.java*](src/main/application/ApplicationContext.java)

- [*Runnable.java*](src/main/application/Runnable.java)

- [*RunCustomerDemo.java*](src/main/application/runnables/RunCustomerDemo.java)

- [*NameSplitter.java*](src/main/datamodel/NameSplitter.java)

- [*ContactsSplitter.java*](src/main/datamodel/ContactsSplitter.java)

- [*application.properties*](src/resources/application.properties)

- [*log4j2.properties*](src/resources/log4j2.properties)

```
src/main
src/main/application
src/main/application/Application.java               <-- Application main()
src/main/application/ApplicationContext.java        <-- ApplicationContext
src/main/application/package-info.java
src/main/application/Runnable.java                  <-- Runnable interface

src/main/application/runnables
src/main/application/runnables/RunCustomerDemo.java <-- demo
src/main/application/runnables/TableFormatter.java

src/main/datamodel                                  <-- add 'Customer.java'
src/main/datamodel/ContactsSplitter.java
src/main/datamodel/NameSplitter.java                <-- add implementation class
                                                    <-- 'NameSplitterImpl.java'
src/main/module-info.java

src/resources
src/resources/application.properties                <-- application.properties
src/resources/log4j2.properties                     <-- logger configuration
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
```

Answer questions:

1. What is *"ApplicationContext"* used for?

1. Name the three properties of the *"Singleton"* pattern used in
    [*ContactsSplitter.java*](src/main/datamodel/ContactsSplitter.java).

1. Understand the *"Builder"* pattern used in
    [*TableFormatter.java*](src/main/application/runnables/TableFormatter.java).

1. What is the purpose of configuration in
    [*application.properties*](src/resources/application.properties)

1. What is the purpose of
    [*NameSplitter.java*](src/main/datamodel/NameSplitter.java)?

1. What is the purpose of
    [*ContactsSplitter.java*](src/main/datamodel/ContactsSplitter.java)?

1. What are chainable methods, see
    [*RunCustomerDemo.java*](src/main/application/runnables/RunCustomerDemo.java)?


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Implement Class *Customer*

Implement class *Customer* in package *"datamodel"* according to:
<img src="https://raw.githubusercontent.com/sgra64/ordering-system/refs/heads/markup/img/customer-1.png" width="660"/>

Checkout the test for *Customer*:

```sh
git checkout se1-repo/c2-customer -- src/tests/datamodel/Customer_0_BaseTests.java
```

Make sure your *Customer*-implementation compiles with the test.

Run the test in your IDE and in the terminal:

```sh
mk run-tests -c datamodel.Customer_0_BaseTests
```
```
╷
├─ JUnit Jupiter ✔
│  └─ Customer_0_BaseTests ✔
│     ├─ test_061_chainable_methods_contacts() ✔
│     ├─ test_060_chainable_methods() ✔
│     ├─ test_050_contacts_getter_setter() ✔
│     ├─ test_030_name_getter_setter() ✔
│     ├─ test_010_initialization() ✔
│     ├─ test_040_firstNames_getter_setter() ✔
│     └─ test_020_id_getter_setter() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 436 ms
[         7 tests successful      ]
[         0 tests failed          ]
```

<!-- 
src/main/datamodel/Customer.java
src/main/datamodel/NameSplitterImpl.java
 -->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Sanitize Class *Customer*

In software development, *"sanitization"* is used as chargon for hardening an
implementation, which means making it compliant to more detailed specifications,
which are often given as *"sanity tests"*.

Checkout the test for *Customer*:

```sh
git checkout se1-repo/c2-customer -- src/tests/datamodel/Customer_1_SanityTests.java
```

Make sure your *Customer*-implementation still compiles with the test.

Understand the root causes when tests fail and fix your implementation in
*Customer.java*.

Run *sanity test* in your IDE and in the terminal:

```sh
mk run-tests -c datamodel.Customer_1_SanityTests
```
```
╷
├─ JUnit Jupiter ✔
│  └─ Customer_1_SanityTests ✔
│     ├─ test_130_null_names() ✔
│     ├─ test_100_positive_id() ✔
│     ├─ test_102_zero_id() ✔
│     ├─ test_101_negative_id() ✔
│     ├─ test_120_empty_names() ✔
│     ├─ test_140_empty_contacts() ✔
│     ├─ test_131_null_firstNames() ✔
│     ├─ test_141_null_contacts() ✔
│     └─ test_110_set_id_only_once() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 507 ms
[         9 tests successful      ]
[         0 tests failed          ]
```



<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. *Customer* Contacts

Understand [*ContactsSplitter.java*](src/main/datamodel/ContactsSplitter.java)
and how it represents multiple customer contacts.

Check *Customer* contacts tests and validate your implementation:

```sh
git checkout se1-repo/c2-customer -- src/tests/datamodel/Customer_3_ContactsTests.java
```
```sh
mk run-tests -c datamodel.Customer_3_ContactsTests
```
```
╷
├─ JUnit Jupiter ✔
│  └─ Customer_3_ContactsTests ✔
│     ├─ test_300_contact_initialization() ✔
│     ├─ test_310_single_contact() ✔
│     ├─ test_320_multiple_contacts() ✔
│     ├─ test_330_out_of_bounds_contacts() ✔
│     └─ test_321_multiple_contacts_list() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 468 ms
[         5 tests successful      ]
[         0 tests failed          ]
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. *Customer* Names

Understand interface [*NameSplitter.java*](src/main/datamodel/NameSplitter.java)
and how it deals with single-String names.

```java
package datamodel;

import java.util.Optional;

/**
 * Class splits <i>single-String</i> name into last- and first name parts
 * according to rules:
 * <ul>
 * <li> if a name contains no seperators (comma or semicolon {@code [,;]}),
 *      the trailing consecutive part is the last name, all prior parts
 *      are first name parts, e.g. {@code "Tim Anton Schulz-Müller"}, splits
 *      into <i>first name:</i> {@code "Tim Anton"} and <i>last name</i>
 *      {@code "Schulz-Müller"}.
 * <li> names with seperators (comma or semicolon {@code [,;]}) split into
 *      a last name part before the seperator and a first name part after
 *      the seperator, e.g. {@code "Schulz-Müller, Tim Anton"} splits into
 *      <i>first name:</i> {@code "Tim Anton"} and <i>last name</i>
 *      {@code "Schulz-Müller"}.
 * <li> leading and trailing white spaces {@code [\s]}, commata {@code [,;]}
 *      and quotes {@code ["']} must be trimmed from names, e.g.
 *      {@code "  'Schulz-Müller, Tim Anton'    "}.
 * <li> interim white spaces between name parts must be trimmed, e.g.
 *      {@code "Schulz-Müller, <white-spaces> Tim <white-spaces> Anton <white-spaces> "}.
 * </ul>
 * <pre>
 * Examples:
 * +------------------------------------+-----------------------+-----------------------+
 * |Single-String name                  |first name parts       |last name parts        |
 * +------------------------------------+-----------------------+-----------------------+
 * |"Eric Meyer"                        |"Eric"                 |"Meyer"                |
 * |"Meyer, Anne"                       |"Anne"                 |"Meyer"                |
 * |"Meyer; Anne"                       |"Anne"                 |"Meyer"                |
 * |"Tim Schulz‐Mueller"                |"Tim"                  |"Schulz‐Mueller"       |
 * |"Nadine Ulla Blumenfeld"            |"Nadine Ulla"          |"Blumenfeld"           |
 * |"Nadine‐Ulla Blumenfeld"            |"Nadine‐Ulla"          |"Blumenfeld"           |
 * |"Khaled Saad Mohamed Abdelalim"     |"Khaled Saad Mohamed"  |"Abdelalim"            |
 * +------------------------------------+-----------------------+-----------------------+
 * 
 * Trim leading, trailing and interim white spaces and quotes:
 * +------------------------------------+-----------------------+-----------------------+
 * |" 'Eric Meyer'  "                   |"Eric"                 |"Meyer"                |
 * |"Nadine     Ulla     Blumenfeld"    |"Nadine Ulla"          |"Blumenfeld"           |
 * +------------------------------------+-----------------------+-----------------------+
 * </pre>
 */
public interface NameSplitter {

    /**
     * {@link NameSplitter} <i>Singleton</i> instance getter.
     * @return reference to <i>Singleton</i> instance
     */
    static NameSplitter getInstance() {
        return NameSplitterImpl.getInstance();
    }

    /**
     * Record of a name split into {@code name} and {@code firstNames} parts.
     */
    public record SplitName(String name, String firstNames) { }


    /**
     * Split single-String name into last- and first name parts.
     * @param name single-String name to split into first- and last name parts
     * @returns record {@link SplitName} or empty {@link Optional} if name
     * is illegal or could not be split
     */
    public Optional<SplitName> split(String name);

}
```

Implement the interface [*NameSplitter.java*](src/main/datamodel/NameSplitter.java)
with a new *Singleton* class *NameSplitterImpl.java*.

Implement all three properties of the *Singleton* pattern as in
[*ContactsSplitter.java*](src/main/datamodel/ContactsSplitter.java).

When you are done, check *Customer* names tests and validate your implementation:

```sh
git checkout se1-repo/c2-customer -- src/tests/datamodel/Customer_2_NamesTests.java
```
```sh
mk run-tests -c datamodel.Customer_2_NamesTests
```
```
╷
├─ JUnit Jupiter ✔
│  └─ Customer_2_NamesTests ✔
│     ├─ test_205_name_in_order_dashed_first() ✔
│     ├─ test_221_quotes() ✔
│     ├─ test_223_whitespaces() ✔
│     ├─ test_210_last_name_first_comma() ✔
│     ├─ test_230_extreme_long_names() ✔
│     ├─ test_212_last_name_with_dash() ✔
│     ├─ test_200_name_in_order() ✔
│     ├─ test_211_last_name_first_semicolon() ✔
│     ├─ test_213_last_name_with_dash() ✔
│     ├─ test_204_name_in_order_triple_dashed_last() ✔
│     ├─ test_206_many_names_in_order() ✔
│     ├─ test_214_many_last_names() ✔
│     ├─ test_207_many_names_in_order() ✔
│     ├─ test_220_spaces() ✔
│     ├─ test_202_name_in_order_triple_first() ✔
│     ├─ test_222_double_quotes() ✔
│     ├─ test_203_name_in_order_dashed_last() ✔
│     ├─ test_209_many_names_in_order() ✔
│     └─ test_201_name_in_order_double_first() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 180 ms
[        19 tests successful      ]
[         0 tests failed          ]
```

=======
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
>>>>>>> origin/c3-datamodel


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

<<<<<<< HEAD
## 6. Run all *Customer* Tests

Run all *Customer* Tests 


```sh
git checkout se1-repo/c2-customer -- src/tests/datamodel/Customer_3_ContactsTests.java
```
```sh
mk run-tests
```
```
Test run finished after 662 ms
[        42 tests successful      ]     <-- 42 tests successfull
[         0 tests failed          ]
```

If all tests pass, commit your implementation to branch `c2-customer`.
=======

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
>>>>>>> origin/c3-datamodel
