package statistic;

public class CashierStatistic {
    private String issuer;
    private int bills;
    private int items;
    private double profit;

    public CashierStatistic() {
    }

    public CashierStatistic(String issuer, int bills, int items, double profit) {
        this.issuer = issuer;
        this.bills = bills;
        this.items = items;
        this.profit = profit;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getBills() {
        return bills;
    }

    public void setBills(int bills) {
        this.bills = bills;
    }

    public int getItems(){
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
