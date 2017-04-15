package com.innovate.himnario;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Joel on 12-Apr-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabsCount;
    String callingClass;
    Coro coro;

    public PagerAdapter(FragmentManager fm, int tabsCount, String callingClass, Coro coro){
        super(fm);
        this.tabsCount = tabsCount;
        this.callingClass = callingClass;
        this.coro = coro;
    }

    @Override
    public Fragment getItem(int position) {
        if (callingClass.equals("MainActivity")) {
            switch (position) {
                case 0:
                    BusquedaTab tabBusqueda = new BusquedaTab();
                    return tabBusqueda;
                case 1:
                    ListasTab tabListas = new ListasTab();
                    return tabListas;
                case 2:
                    AjustesTab tabAjustes = new AjustesTab();
                    return tabAjustes;
                default:
                    return null;
            }
        } else if (callingClass.equals("CoroDetailActivity")) {
            if (position == 0) {
                CoroDetailPhoneFragment detailPhoneFragment = new CoroDetailPhoneFragment();
                detailPhoneFragment.coro = coro;
                return detailPhoneFragment;
            } else {
                CoroDetailTabletFragment detailTabletFragment = new CoroDetailTabletFragment();
                return detailTabletFragment;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
