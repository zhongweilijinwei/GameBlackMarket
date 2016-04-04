package com.aipai.ljw.gameblackmarket.View;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * Created by PC 303 on 2016/4/4.
 */
public class AttachRecyclerViewFloatingButton extends FloatingActionButton{

    public AttachRecyclerViewFloatingButton(Context context) {
        super(context);
    }

    public AttachRecyclerViewFloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AttachRecyclerViewFloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    ViewGroup.MarginLayoutParams params;
    int bottomMargin=0;
    int flotingButtonhigh=0;



    public void attachRecyclerView(RecyclerView recyclerView){


        if (recyclerView==null)return;
        recyclerView.addOnScrollListener(new RecyclerViewSollListener());
    }







    class RecyclerViewSollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            Log.i("lihao","dy===="+dy);
            boolean isSignificantDelta=Math.abs(dy) > 8;

//            if (isSignificantDelta){//滑动越快 dy越大 或越小    往上滑动 是正数，反则负数
                if (dy > 0) {
                    onScrollUp();
                } else {
                    onScrollDown();
                }
//            }

        }
    }

    private boolean mVisible;
    //按钮要往上移动
    private void onScrollUp(){
        if (mVisible) {
            mVisible=false;
            statrAmnimator(true);
        }
    }
   //按钮要往下移动
    private void onScrollDown(){
        if (!mVisible) {
            mVisible=true;
            statrAmnimator(true);
        }
    }


    private  void statrAmnimator(boolean animator){

        if (flotingButtonhigh==0){
           ViewTreeObserver viewTreeObserver= getViewTreeObserver();//获取一个给view 状态改变监听的注册观察者的注册接口
            if (viewTreeObserver.isAlive()){
                viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        ViewTreeObserver currentVto = getViewTreeObserver();
                        if (currentVto.isAlive()) {
                            currentVto.removeOnPreDrawListener(this);
                        }

                        ViewGroup.LayoutParams params= AttachRecyclerViewFloatingButton.this.getLayoutParams();
                        if ( params instanceof  ViewGroup.MarginLayoutParams){
                            bottomMargin= ((ViewGroup.MarginLayoutParams)params).bottomMargin;
                        }
                        flotingButtonhigh=getHeight();

                        return true;
                    }
                });
            }
        }


        int translationY = mVisible ? 0 : bottomMargin +flotingButtonhigh;
        if (animator) {
           this.animate().translationY(translationY).setDuration(200).start();
        }
    }
}
