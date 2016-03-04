/*
 * Copyright (c) 2016.
 * wb-lijinwei.a@alibaba-inc.com
 */

package com.example.wb_lijinweia.mockmicalendar.micalendar.model;

import com.example.wb_lijinweia.mockmicalendar.micalendar.utils.DateUtil;

import java.io.Serializable;
public class CustomDate implements Serializable{
	private static final long serialVersionUID = 1L;
	public int year;
	public int month;
	public int day;
	public int week;
	
	public CustomDate(int year,int month,int day){
		if(month > 12){
			month = 1;
			year++;
		}else if(month <1){
			month = 12;
			year--;
		}
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public CustomDate(){
		this.year = DateUtil.getYear();
		this.month = DateUtil.getMonth();
		this.day = DateUtil.getCurrentMonthDay();
	}

	public static CustomDate modifiDayForObject(CustomDate date,int day){
		CustomDate modifiDate = new CustomDate(date.year,date.month,day);
		return modifiDate;
	}
	@Override
	public String toString() {
		return year+"-"+month+"-"+day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return DateUtil.getWeekDayFromDate(year, month, day);
	}

	public String getDisplayWeek(int week){
		switch (week){
			case 0:
				return "周日";
			case 1:
				return "周一";
			case 2:
				return "周二";
			case 3:
				return "周三";
			case 4:
				return "周四";
			case 5:
				return "周五";
			case 6:
				return "周六";

		}
		return null;
	}
	public void setWeek(int week) {
		this.week = week;
	}

	public boolean equals(CustomDate o) {
		if(o == null){
			return false;
		}

		if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() == o.getDay()){
			return true;
		}
		return false;
	}
}
