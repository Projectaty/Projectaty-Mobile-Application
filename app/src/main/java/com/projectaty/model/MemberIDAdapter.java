package com.projectaty.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectaty.R;

import java.util.List;

public class MemberIDAdapter extends RecyclerView.Adapter<MemberIDAdapter.MemberIDViewHolder> {

    private List<String> memberIDsList;

    public MemberIDAdapter(List<String> memberIDsList) {
        this.memberIDsList = memberIDsList;
    }

    @NonNull
    @Override
    public MemberIDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_id_item, parent, false);
        return new MemberIDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberIDViewHolder holder, int position) {
        String memberID = memberIDsList.get(position);
        holder.bind(memberID);
    }

    @Override
    public int getItemCount() {
        return memberIDsList.size();
    }

    public static class MemberIDViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMemberID;

        public MemberIDViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMemberID = itemView.findViewById(R.id.textViewMemberID);
        }

        public void bind(String memberID) {
            textViewMemberID.setText(R.string.member_id + memberID);
        }
    }
}
