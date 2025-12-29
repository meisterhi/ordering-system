package datamodel;

public class Article {
    public enum TAX { GER_VAT, GER_VAT_REDUCED, TAX_FREE };
 
    private final String name;
    private final long pricePerUnit;
    private final TAX taxType;
    private final String id;

    public Article(String sku, String name, long pricePerUnit) {
        this(sku, name, pricePerUnit, TAX.GER_VAT);
    }

    public Article(String sku, String name, long pricePerUnit, TAX taxTyp) {
        this.id = sku;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.taxType = taxTyp;
    }

    public Article tax(TAX taxType) {
        return this.taxType == null ? this : new Article(this.id, this.name, this.pricePerUnit, taxType);
    }

    public String description() {return name;}
    public Long unitPrice() {return pricePerUnit;}
    public TAX tax() {return taxType;}
    public String id() {return id;}


}
