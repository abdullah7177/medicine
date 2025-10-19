package medicine;

public class Product {

    private String itemCode;
    private String productName;
    private String batchNo;
    private String bonus;
    private String companyName;
    private String retail;
    private String salePrice;
    private String group;
    private String companyDis;
    private String costPrice;
    private String saleTax;
    private String companyName2;
    private String piecesPick;
    private String tradePrice;
    private String discount;
    private String bonusPrice;

    // Constructor
    public Product(String itemCode, String productName, String batchNo, String bonus,
                   String companyName, String retail, String salePrice, String group,
                   String companyDis, String costPrice, String saleTax, String companyName2,
                   String piecesPick, String tradePrice, String discount, String bonusPrice) {
        this.itemCode = itemCode;
        this.productName = productName;
        this.batchNo = batchNo;
        this.bonus = bonus;
        this.companyName = companyName;
        this.retail = retail;
        this.salePrice = salePrice;
        this.group = group;
        this.companyDis = companyDis;
        this.costPrice = costPrice;
        this.saleTax = saleTax;
        this.companyName2 = companyName2;
        this.piecesPick = piecesPick;
        this.tradePrice = tradePrice;
        this.discount = discount;
        this.bonusPrice = bonusPrice;
    }

    // Getters
    public String getItemCode() { return itemCode; }
    public String getProductName() { return productName; }
    public String getBatchNo() { return batchNo; }
    public String getBonus() { return bonus; }
    public String getCompanyName() { return companyName; }
    public String getRetail() { return retail; }
    public String getSalePrice() { return salePrice; }
    public String getGroup() { return group; }
    public String getCompanyDis() { return companyDis; }
    public String getCostPrice() { return costPrice; }
    public String getSaleTax() { return saleTax; }
    public String getCompanyName2() { return companyName2; }
    public String getPiecesPick() { return piecesPick; }
    public String getTradePrice() { return tradePrice; }
    public String getDiscount() { return discount; }
    public String getBonusPrice() { return bonusPrice; }
}
