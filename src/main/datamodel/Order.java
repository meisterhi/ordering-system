package datamodel;

import java.util.List;

public class Order {
    private List<ItemOrder> itemOrders;
    private final Customer customer;
    private final long orderId;

    public record ItemOrder(Article article, int unitsOrdered){}

    public Order(long orderid, Customer customer) {
        this.orderId = orderid;
        this.customer = customer;

    }
    

    public Order addItem(Article article, int unitsOrdered) {
        if(article == null || unitsOrdered <= 0) {
            throw new IllegalArgumentException("article must not be null and unitsOrdered must be > 0");
        }
        if(this.itemOrders == null) {
            this.itemOrders = new java.util.ArrayList<>();
        }
        this.itemOrders.add( new ItemOrder(article, unitsOrdered) );
        return this;
    }


    public Customer customer() {return customer;}
    public long id() {return orderId;}
    public List<ItemOrder> items() {return itemOrders;}
}
