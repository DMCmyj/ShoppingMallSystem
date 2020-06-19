package team.sys;

import java.util.Date;

public class Goods {
    private String goods_name;
    private double goods_price;
    private String goods_type;
    private int goods_num;
    private Date sell_date;
    private Date buy_date;

    public Goods(String goods_name, double goods_price, String goods_type, int goods_num, Date sell_date, Date buy_date) {
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_type = goods_type;
        this.goods_num = goods_num;
        this.sell_date = sell_date;
        this.buy_date = buy_date;
    }

    public Goods(String goods_name, double goods_price, String goods_type, int goods_num) {
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_type = goods_type;
        this.goods_num = goods_num;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public Date getSell_date() {
        return sell_date;
    }

    public void setSell_date(Date sell_date) {
        this.sell_date = sell_date;
    }

    public Date getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }
}
