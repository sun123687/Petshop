package com.example.agricultureproducts.cart;

import com.example.agricultureproducts.home.Goods;

public class CartGoods extends Goods {
    private int c_num;
    private String c_checked;

    public CartGoods(int g_id, int g_photo, String g_name, String g_type, double g_price, int c_num) {
        super(g_id, g_photo, g_name, g_type, g_price, 9, "京东");
        this.c_num = c_num;
        this.c_checked = "false";
    }

    public CartGoods(int g_id, int g_photo, String g_name, String g_type, double g_price, int c_num, String c_checked) {
        super(g_id, g_photo, g_name, g_type, g_price, 9, "京东");
        this.c_num = c_num;
        this.c_checked = c_checked;
    }

    public int getC_num() {
        return c_num;
    }

    public String getC_checked() {
        return c_checked;
    }
}
