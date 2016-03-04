package com.example.wb_lijinweia.mockmicalendar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.wb_lijinweia.mockmicalendar.micalendar.views.CalendarView;
import com.example.wb_lijinweia.mockmicalendar.micalendar.adpter.CalendarViewAdapter;
import com.example.wb_lijinweia.mockmicalendar.micalendar.model.CustomDate;
import com.example.wb_lijinweia.mockmicalendar.micalendar.MonthPager;
import com.example.wb_lijinweia.mockmicalendar.micalendar.adpter.NormalRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MonthPager mViewPager;
    private int mCurrentIndex = 498;
    private CalendarView[] mShowViews;
    private CalendarViewAdapter<CalendarView> adapter;
    private SildeDirection mDirection = SildeDirection.NO_SILDE;
    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE;
    }

    private RecyclerView rvToDoList;
    private TextView textViewYearDisplay;
    private TextView textViewMonthDisplay;
    private TextView textViewWeekDisplay;
    private TextView monthCalendarView;
    private TextView weekCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewYearDisplay  = (TextView) findViewById(R.id.show_year_view);
        textViewMonthDisplay = (TextView) findViewById(R.id.show_month_view);
        textViewWeekDisplay  = (TextView) findViewById(R.id.show_week_view);
        monthCalendarView = (TextView) this.findViewById(R.id.month_calendar_button);
        weekCalendarView = (TextView) this.findViewById(R.id.week_calendar_button);

        mViewPager = (MonthPager) this.findViewById(R.id.vp_calendar);

        rvToDoList = (RecyclerView) findViewById(R.id.list);
        rvToDoList.setHasFixedSize(true);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        rvToDoList.setAdapter(new NormalRecyclerViewAdapter(this));

        CalendarView.OnCellCallBack mCallback;
        mCallback = new CalendarView.OnCellCallBack() {
            @Override
            public void clickDate(CustomDate date) {
                textViewYearDisplay.setText(date.getYear() + "");
                textViewMonthDisplay.setText(date.getMonth() + "月");
                textViewWeekDisplay.setText( date.getDisplayWeek(date.getWeek()) + "");
            }

            @Override
            public void clickPosition(int position) {
                mViewPager.setSelectedIndex(position);
            }

            @Override
            public void onMesureCellHeight(int cellSpace) {

            }

            @Override
            public void changeDate(CustomDate date) {
                textViewYearDisplay.setText(date.getYear() + "");
                textViewMonthDisplay.setText(date.getMonth() + "月");
                textViewWeekDisplay.setText( date.getDisplayWeek(date.getWeek()) + "");
            }
        };

        CalendarView[] viewsMonth = new CalendarView[3];
        for (int i = 0; i < 3; i++) {
            viewsMonth[i] = new CalendarView(this, CalendarView.MONTH_STYLE, mCallback);
        }

        adapter = new CalendarViewAdapter<CalendarView>(viewsMonth);
        setViewPager();

        weekCalendarView.setOnClickListener(this);
        monthCalendarView.setOnClickListener(this);
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setViewPager() {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(498);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void measureDirection(int arg0) {
        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }

    private void updateCalendarView(int arg0) {
        mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            mShowViews[arg0 % mShowViews.length].rightSilde();
        } else if (mDirection == SildeDirection.LEFT) {
            mShowViews[arg0 % mShowViews.length].leftSilde();
        }
        mDirection = SildeDirection.NO_SILDE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.month_calendar_button:
                break;
            case R.id.week_calendar_button:
                break;
        }
    }
}
