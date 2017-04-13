package com.innovate.himnario;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Joel on 12-Apr-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabsCount;

    public PagerAdapter(FragmentManager fm, int tabsCount){
        super(fm);
        this.tabsCount = tabsCount;
    }

    @Override
    public Fragment getItem(int position) {
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
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
