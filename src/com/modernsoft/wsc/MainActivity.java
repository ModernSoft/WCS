package com.modernsoft.wsc;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTabChangeListener, OnPageChangeListener
{
    public final int[] TAB_LAYOUT = {R.layout.tab_news, R.layout.tab_story, R.layout.tab_talk, R.layout.tab_settings};
    public final int[] TAB_TITLE = {R.string.title_news, R.string.title_story, R.string.title_talk, R.string.title_settings};
    public final int TAB_COUNT = 4;
    
    TabHost mTabHost;
    ViewPager mViewPager;
    ActionBar mActionBar;
    
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mActionBar = getActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0xFF, 0xBB, 0)));
        mActionBar.setIcon(null);
        mActionBar.setLogo(null);
        
        mTabHost = (TabHost) findViewById (R.id.mainTabHost);
        
        mTabHost.setup();
        
        int i;
        
        for(i = 0; i < TAB_COUNT; i ++) {
        	TabSpec mTabSpec = mTabHost.newTabSpec(getString(TAB_TITLE[i]));
        	
        	mTabSpec.setIndicator(getString(TAB_TITLE[i]));
        	mTabSpec.setContent(new TabHost.TabContentFactory() {
				@Override
				public View createTabContent(String tag) {
					return new View(MainActivity.this);
				}
			});
        	
        	mTabHost.addTab(mTabSpec);
        }
        
        mTabHost.getTabWidget().setBackgroundColor(Color.rgb(0xFF, 0xBB, 0));
        mTabHost.setOnTabChangedListener(this);
        
        mTabHost.setCurrentTab(0);
        
        mActionBar.setTitle(TAB_TITLE[0]);
        
        mViewPager = (ViewPager) findViewById (R.id.mainTabPager);
        
        mViewPager.setAdapter(new TabPagerAdapter(this));
        mViewPager.setOnPageChangeListener(this);
        
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        param.topMargin = mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height;
        
        mViewPager.setLayoutParams(param);
    }

    public class TabPagerAdapter extends PagerAdapter {
    	
    	LayoutInflater mInflater;
    	
    	public TabPagerAdapter(Context context) {
    		super();
    		mInflater = LayoutInflater.from(context);
    	}
    	
		@Override
		public int getCount() {
			return TAB_COUNT;
		}

		public Object instantiateItem(View pager, int position) {
			View v = mInflater.inflate(TAB_LAYOUT[position], null);
			
			((ViewPager) pager).addView(v, null);
			
			return v;
		}
		
		public void destroyItem(View pager, int position, Object view) {
			((ViewPager) pager).removeView((View) view);
		}
		
		@Override
		public boolean isViewFromObject(View v, Object obj) {
			return v == obj;
		}
    }

	@SuppressLint("NewApi")
	@Override
	public void onTabChanged(String tabId) {
		int position = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(position);
		mActionBar.setTitle(TAB_TITLE[position]);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int position) {
		mTabHost.setCurrentTab(position);
	}
}
