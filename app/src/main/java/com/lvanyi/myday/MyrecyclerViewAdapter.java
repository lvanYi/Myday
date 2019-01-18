package com.lvanyi.myday;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.Util.TimeUtil;

import java.util.Date;
import java.util.List;

public class MyrecyclerViewAdapter extends RecyclerView.Adapter<MyrecyclerViewAdapter.ItemHolder> {
    private Context mContext;

    private List<ItemModel> mList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position,ItemModel itemModel);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public MyrecyclerViewAdapter(Context context, List<ItemModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        holder.setDatas(mList, position);
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position,mList.get(position));
                }
            });
        }
    }

    public void addItem(ItemModel itemModel){
        mList.add(itemModel);
        notifyItemChanged(mList.size());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mDays, mRemark;

        private List<ItemModel> mDatas;

        public ItemHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_item_title);
            mDays = itemView.findViewById(R.id.tv_item_days);
            mRemark = itemView.findViewById(R.id.tv_item_remark);
        }

        /*public void setmDays(TextView mDays) {
            this.mDays = mDays;
        }

        public void setmRemark(TextView mRemark) {
            this.mRemark = mRemark;
        }

        public void setmTitle(TextView mTitle) {
            this.mTitle = mTitle;
        }*/
        public void setDatas(List<ItemModel> list, int position) {
            mTitle.setText(list.get(position).getTitle());
            mRemark.setText(list.get(position).getRemark());
            mDays.setText(TimeUtil.getnewDateDiff(list.get(position).getStartDate(),new Date(System.currentTimeMillis())));
            Log.d("____________", "setDatas: "+list.get(position).getStartDate()+"_______"+new Date(System.currentTimeMillis()));
        }

    }
}
