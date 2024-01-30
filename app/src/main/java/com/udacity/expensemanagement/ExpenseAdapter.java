package com.udacity.expensemanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ExpenseAdapter extends FragmentStateAdapter {
    private final int ITEM_COUNT = 12;

    public ExpenseAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        // The object is just an integer.
        args.putInt(ChartFragment.ARG_MONTH, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }
}
