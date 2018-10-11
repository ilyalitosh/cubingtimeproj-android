package com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.SolveViewHolder;

import java.util.List;

/**
 * SolvesListAdapter
 *
 * @author Ilya Litosh
 */
public class SolvesListAdapter extends RecyclerView.Adapter<SolveViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Solve> mSolves;

    public SolvesListAdapter(Context context, List<Solve> solves) {
        mLayoutInflater = LayoutInflater.from(context);
        mSolves = solves;
    }

    @NonNull
    @Override
    public SolveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(
                R.layout.activity_timer_viewpager_tab_solves_solves_list_solve_item,
                parent,
                false);

        return new SolveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolveViewHolder holder, int position) {
        holder.getTime().setText(mSolves.get(holder.getAdapterPosition()).getTime().toString());
        holder.getScramble().setText(mSolves.get(holder.getAdapterPosition()).getScramble());
    }

    @Override
    public int getItemCount() {
        if (mSolves != null) {
            return mSolves.size();
        } else {
            return 0;
        }
    }

    public void addItem(Solve solve) {
        mSolves.add(solve);
        notifyDataSetChanged();
    }

}
