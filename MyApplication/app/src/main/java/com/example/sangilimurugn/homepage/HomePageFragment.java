package com.example.sangilimurugn.homepage;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sangilimurugn.homepage.customviews.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment {

    Context context;
    private ViewGroup parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page, container, false);
        context = getContext();
        parent = (ViewGroup) rootView.findViewById(R.id.container);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View tabListContainer = view.findViewById(R.id.ll_tab_list_container);
        if (tabListContainer != null) {
            tabListContainer.setVisibility(View.VISIBLE);
        }

        final SlidingTabLayout stlTabs = (SlidingTabLayout) view.findViewById(R.id.stl_tabs);

        ViewPager vpListViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        if (vpListViewPager != null) {
            List<String> tabNames = new ArrayList<>();
            tabNames.add("VIDEOS");
            tabNames.add("IMAGES");
            tabNames.add("MILESTONE");

            final ListPagerAdapter listPagerAdapter = new ListPagerAdapter(getChildFragmentManager(), getFragmentList(), tabNames);
            vpListViewPager.setAdapter(listPagerAdapter);
            if (stlTabs != null) {
                stlTabs.setSelectedIndicatorColors(ContextCompat.getColor(getContext(), R.color.list_item_title));
                stlTabs.setDistributeEvenly(true);
                stlTabs.setViewPager(vpListViewPager, true);
            }
            vpListViewPager.setCurrentItem(tabNames.indexOf("0"));

            vpListViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (stlTabs != null) {
                        stlTabs.setOtherIconColor(position);
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        }
        setMoviesList(formMovieList());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    /**
     * Adapter class to add the tab fragments with pager
     *
     */
    public static class ListPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> fragments;
        private final List<String> tabNames;

        public ListPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabNames) {

            super(fm);
            this.fragments = fragments;
            this.tabNames = tabNames;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {

            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }
    }

    /**
     * Get the list of fragments to be used in Tabs
     *
     * @return List of Fragments
     */
    private List<Fragment> getFragmentList() {

        List<Fragment> tabs = new ArrayList<>();

        VideoFragment videoFragment = new VideoFragment();
        tabs.add(videoFragment);

        VideoFragment imageFragment = new VideoFragment();
        tabs.add(imageFragment);

        VideoFragment mileStoneFragment = new VideoFragment();
        tabs.add(mileStoneFragment);

        return tabs;
    }

    /**
     * set the movies into Horizontal view
     * @param movieDetailList List
     */

    private void setMoviesList(List movieDetailList) {
        for(int index = 0 ; index < movieDetailList.size(); index++) {
            final View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.grid_view_row, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.list_item_title));

            Object object = movieDetailList.get(index);

            if (object != null && object instanceof  DropDown) {
                String movieName = ((DropDown)object).getName();
                textView.setText(movieName);
                textView.setVisibility(View.GONE);
                imageView.setImageResource(((DropDown)object).getDrawble());
                convertView.setTag(object);
                parent.addView(convertView);
            }

        }
    }

    private List<DropDown> formMovieList() {
        List<DropDown> dropdowList = new ArrayList<>();
        for(int index  = 0; index < 5; index++) {
            DropDown dropDown = new DropDown();
            dropDown.setName("Movie" + index + 1);
            dropDown.setDrawble(R.drawable.download_3);
            dropdowList.add(dropDown);
        }
        return dropdowList;
    }


}
