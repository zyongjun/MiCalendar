/*
 * Copyright (c) 2016.
 * wb-lijinwei.a@alibaba-inc.com
 */

package com.windhike.calendar.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.windhike.calendar.model.CustomDate;
import com.windhike.calendar.widget.CalendarView;
import com.windhike.calendar.widget.MonthPager;

public class CalendarViewAdapter<V extends View> extends PagerAdapter {
	public static final String TAG = "CalendarViewAdapter";

	private V[] views;

	public CalendarViewAdapter(V[] views) {
		super();
		this.views = views;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		boolean isInitToday = true;
		if (container.getChildCount() == views.length) {
			isInitToday = false;
			container.removeView(views[position % views.length]);
		}
		View view = views[position % views.length];
		if(view instanceof CalendarView){
			((CalendarView) view).setmShowDate(CustomDate.addMonth(new CustomDate(), position - MonthPager.CURRENT_DAY_INDEX));

			if(position == MonthPager.CURRENT_DAY_INDEX && isInitToday){
				((CalendarView) view).setInitPage();
			}
		}
		container.addView(view, 0);
		return view;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((View) object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(container);
	}

	public void updateDay(CustomDate date, int position){
		MonthPager.CURRENT_DAY_INDEX = position;
		if(views != null){
			if(views[position % views.length] instanceof CalendarView){
				CalendarView v = (CalendarView) views[position % views.length];
				v.setmShowDate(date);

				CalendarView v2 = (CalendarView) views[(position - 1) % views.length];
				v2.setmShowDate(CustomDate.addMonth(date, -1));

				CalendarView v3 = (CalendarView) views[(position + 1) % views.length];
				v3.setmShowDate(CustomDate.addMonth(date, 1));
			}
		}
	}

	public V[] getAllItems() {
		return views;
	}

	public void setViews(V[] views) {
		this.views = views;
	}
}
