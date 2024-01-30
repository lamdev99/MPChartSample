package com.udacity.expensemanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class ChartFragment extends Fragment {
    public static String ARG_MONTH = "month";
    private BarChart barChartExpense;
    private PieChart pieChartExpense;
    private PieChart pieChartRevenue;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        int month = args.getInt(ARG_MONTH);
        barChartExpense = view.findViewById(R.id.barChartExpense);
        pieChartExpense = view.findViewById(R.id.pieChartExpense);
        pieChartRevenue = view.findViewById(R.id.pieChartRevenue);
        Description description = new Description();
        description.setText("Khoản chi");
        pieChartExpense.setDescription(description);

        Description description1 = new Description();
        description1.setText("Khoản thu");
        pieChartRevenue.setDescription(description1);
        initBarChart(month);
        initPieChart(pieChartExpense,10, 25);
        initPieChart(pieChartRevenue,10, 25);
    }

    private void initPieChart(PieChart pieChart ,int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        String[] parties = new String[] {
                "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
                "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
                "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
                "Party Y", "Party Z"
        };
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }


    private void initBarChart(int month) {
        Description description = new Description();
        description.setText("Thu nhập ròng");
        barChartExpense.setDescription(description);
        setBottomLabel();
        ArrayList<BarEntry> values = new ArrayList<>();
        int start = 0;
        int count = 12;
        int range = 100;
        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));
            values.add(new BarEntry(i, val));
        }
        BarDataSet set = new BarDataSet(values, "Month " + month + " 2023");
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        setDataToChart(dataSets);
    }

    private void setBottomLabel() {
        // init bottom label
        XAxis xAxis = barChartExpense.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(12);
    }

    private void setDataToChart(ArrayList<IBarDataSet> dataSets) {
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);

        barChartExpense.setData(data);
    }
}