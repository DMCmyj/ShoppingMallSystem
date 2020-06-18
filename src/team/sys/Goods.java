package team.sys;

public class Goods {
    private String goods_name;
    private String goods_price;
    private String goods_type;
    private String goods_num;

    public Goods(String goods_name, String goods_price, String goods_type, String goods_num) {
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

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
