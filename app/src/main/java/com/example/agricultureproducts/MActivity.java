package com.example.agricultureproducts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.agricultureproducts.base.BaseFragment;
import com.example.agricultureproducts.cart.CartFragment;

import com.example.agricultureproducts.home.HomeFragment;

import com.example.agricultureproducts.user.UserFragment;

import java.util.ArrayList;

public class MActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private BaseFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //顶部任务栏改成透明
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        //解决软键盘弹出时底部布局上移
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_m);

        rg_main = findViewById(R.id.rg_main);
        initListener();
        initFragment();
        rg_main.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());


        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }

    //设置监听
    private void initListener() {
        rg_main.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_cart:
                    fragments.clear();
                    fragments.add(new HomeFragment());

                    //fragments.add(new CommunityFragment());
                    fragments.add(new CartFragment());
                    fragments.add(new UserFragment());
                    position = 1;
                    break;
                case R.id.rb_user:
                    position = 2;
                    break;
                default:
                    position = 0;
                    break;
            }
            BaseFragment baseFragment = getFragment(position);
            switchFragment(tempFragment, baseFragment);
        });
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    //    //切换Fragment
//    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment){
//
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        if (tempFragment != nextFragment){
//            tempFragment = nextFragment;
//            if (nextFragment != null){
//
//
//                //判断nextFragment是否有添加
//                if (!nextFragment.isAdded()){
//                    //隐藏当前的fragment
//                    transaction.hide(fromFragment);
//                }
//                transaction.add(R.id.frame1,nextFragment).commit();
//            }else {
//                if (fromFragment != null){
//                    transaction.hide(fromFragment);
//                }
//                transaction.add(nextFragment).commit();
//            }
//        }
//
//    }
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                //开启事务
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                //判断nextFragment是否有添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前的fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frame1, nextFragment).commit();
                } else {
                    if (fromFragment != null) {

                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
