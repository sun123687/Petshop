package com.example.agricultureproducts.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agricultureproducts.R;
import com.example.agricultureproducts.cart.CartDBOpenHelper;

import java.util.ArrayList;

public class GoodsListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Goods> goodsList;
    private CartDBOpenHelper dbOpenHelper;

    public GoodsListAdapter(Context mContext, ArrayList<Goods> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView goods_photo;
        TextView goods_name;
        TextView goods_price;
        TextView goods_sales;
        TextView goods_shop;
        ImageView goods_buy;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item, parent, false);
            holder = new ViewHolder();
            holder.goods_photo = convertView.findViewById(R.id.goods_item_photo);
            holder.goods_name = convertView.findViewById(R.id.goods_item_name);
            holder.goods_price = convertView.findViewById(R.id.goods_item_price);
            holder.goods_sales = convertView.findViewById(R.id.goods_item_sales);
            holder.goods_shop = convertView.findViewById(R.id.goods_item_shop);
            holder.goods_buy = convertView.findViewById(R.id.goods_item_buy);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.goods_photo.setBackgroundResource(goodsList.get(position).getG_photo());
        holder.goods_name.setText(goodsList.get(position).getG_name());
        String price = goodsList.get(position).getG_price() + "";
        holder.goods_price.setText(price);
        String sales = goodsList.get(position).getG_sales() + "万+条评价 99%好评";
        holder.goods_sales.setText(sales);
        holder.goods_shop.setText(goodsList.get(position).getG_shop());

        holder.goods_buy.setOnClickListener(v ->{
            long i = -1;
            //创建数据库
            dbOpenHelper = new CartDBOpenHelper(mContext,1);
            //商品数量
            int goods_num = isExist(goodsList.get(position).getG_id());
            if (goods_num == 0){
                //如果数据库中不存在当前商品
                //Toast.makeText(mContext,"当前数据库不存在改商品！", Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                //cart(g_id,g_photo,g_name,g_type,g_price,g_num,g_check)
                values.put("g_id",String.valueOf(goodsList.get(position).getG_id()));
                values.put("g_photo",String.valueOf(goodsList.get(position).getG_photo()));
                values.put("g_name",goodsList.get(position).getG_name());
                values.put("g_type",goodsList.get(position).getG_type());
                values.put("g_price",String.valueOf(goodsList.get(position).getG_price()));
                values.put("g_num","1");
                values.put("g_check","false");
                i = dbOpenHelper.getWritableDatabase().insert("cart", null, values);
            }else {
                //如果数据库中存在当前商品
                //Toast.makeText(mContext,"当前数据库存在改商品！！！", Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                values.put("g_num",goods_num + 1);
                //参数:表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                i = dbOpenHelper.getWritableDatabase().update(
                        "cart",
                        values,
                        "g_id=?",
                        new String[]{String.valueOf(goodsList.get(position).getG_id())});
            }
            if (i != -1)
                Toast.makeText(mContext, goodsList.get(position).getG_name() + "加入购物车成功！", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(mContext, "加入购物车失败！", Toast.LENGTH_SHORT).show();
            dbOpenHelper.close();
        });
        return convertView;
    }

    //判断商品id是否存在,存在返回商品数量，不存在返回0
    private int isExist(int g_id){
        dbOpenHelper = new CartDBOpenHelper(mContext,1);
        //表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
        Cursor cursor = dbOpenHelper.getReadableDatabase().query(
                "cart",
                new String[]{"g_num"},
                "g_id=?",
                new String[]{String.valueOf(g_id)},
                null,
                null,
                null);
        boolean idIsExist = cursor.moveToFirst();
        if (idIsExist){
            String str = cursor.getString(0);
            cursor.close();
            dbOpenHelper.close();
            //Toast.makeText(mContext,"当前数据库存在改商品！！！" + str, Toast.LENGTH_SHORT).show();
            return Integer.parseInt(str);
        }
        //Toast.makeText(mContext,"当前数据库不存在改商品！", Toast.LENGTH_SHORT).show();
        cursor.close();
        dbOpenHelper.close();
        return 0;
    }
}
