package bo.com.bolventur.ui.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import bo.com.bolventur.R;
import bo.com.bolventur.ui.fragments.MainMenuBtn1TabFragment;
import bo.com.bolventur.ui.fragments.MainMenuBtn2TabFragment;
import bo.com.bolventur.ui.fragments.MainMenuBtn4TabFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MainMenuTabPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_4};
    private final Context mContext;
    private List<Fragment> fragments = new ArrayList<>();

    public MainMenuTabPagerAdapter(Context context, FragmentManager fm, String user) {
        super(fm);
        mContext = context;

        fragments.add(MainMenuBtn1TabFragment.newInstance(user));
        fragments.add(MainMenuBtn2TabFragment.newInstance(user));
        fragments.add(MainMenuBtn4TabFragment.newInstance(user));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}