package com.example.agricultureproducts.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agricultureproducts.R;
import com.example.agricultureproducts.base.BaseFragment;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    private ListView lv_home;
    private ImageView ib_top;
    private TextView tv_search_home, tv_message_home;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        lv_home = view.findViewById(R.id.lv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        initListener();
        return view;
    }

    public void initData(){
        super.initData();
//        List data =Arrays.asList("第1个", "第2个", "第3个", "第4个", "第5个", "第6个", "第7个", "第8个", "第9个", "第10个", "第11个"
//                , "第12个", "第13个", "第14个", "第15个", "第16个", "第17个");
//        ArrayAdapter adapter = new ArrayAdapter<String>(
//                mContext,
//                android.R.layout.simple_list_item_1,
//                data
//        );
//        lv_home.setAdapter(adapter);
        ArrayList<Goods> goods = new ArrayList<>();
        //int g_id, int g_photo, String g_name, String g_type, double g_price, int g_sales, String g_shop
        goods.add(new Goods(1,R.drawable.b1,"猫粮","五公斤",129.00,500,"喵星人旗舰店"));
        goods.add(new Goods(2,R.drawable.b2,"狗粮","十公斤",199.00,300,"汪汪宠物用品店"));
        goods.add(new Goods(3,R.drawable.b3,"猫抓板","加厚版",39.90,800,"猫咪天堂"));
        goods.add(new Goods(4,R.drawable.b4,"宠物洗澡露","五百毫升",49.90,1200,"宠物护理中心"));
        goods.add(new Goods(5,R.drawable.b5,"狗狗零食","牛肉干一百克",25.00,1500,"毛孩子食品专营店"));
        goods.add(new Goods(6,R.drawable.b6,"宠物窝","中号",89.90,700,"温馨宠物家居"));
        goods.add(new Goods(7,R.drawable.b7,"猫砂","六升",59.00,2000,"猫咪生活馆"));
        goods.add(new Goods(8,R.drawable.b8,"宠物牵引绳","可伸缩款",35.00,1100,"户外遛狗装备店"));

        GoodsListAdapter adapter = new GoodsListAdapter(mContext, goods);
        lv_home.setAdapter(adapter);
    }

    private void initListener(){

        ib_top.setOnClickListener(v -> {
            //滑动回到顶部
            lv_home.smoothScrollToPosition(0);
        });

        tv_search_home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
        });

        tv_message_home.setOnClickListener(v -> {
            Toast.makeText(mContext, "进入消息中心！", Toast.LENGTH_SHORT).show();
        });
        
    }
}
