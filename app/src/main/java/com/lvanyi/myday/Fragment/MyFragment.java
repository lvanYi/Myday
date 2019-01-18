package com.lvanyi.myday.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lvanyi.myday.Activity.MainActivity;
import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.MyrecyclerViewAdapter;
import com.lvanyi.myday.R;
import com.lvanyi.myday.ShowDayActivity;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {
    private RecyclerView recyclerView;

    private List<ItemModel> list = new ArrayList<ItemModel>();
    private MyrecyclerViewAdapter myrecyclerViewAdapter;
    getItemData getItemData;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.list = ((MainActivity) context).getList();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_fragment_layout, container, false);
        recyclerView = view.findViewById(R.id.rec_my);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        myrecyclerViewAdapter = new MyrecyclerViewAdapter(getActivity(), list);
        recyclerView.setAdapter(myrecyclerViewAdapter);

        myrecyclerViewAdapter.setOnClickListener(new MyrecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ItemModel itemModel) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ShowDayActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putString("a", itemModel.getTitle());
                intent.putExtra("title", itemModel.getTitle());
                intent.putExtra("remark", itemModel.getRemark());
                intent.putExtra("type", itemModel.getType());
                intent.putExtra("startdate", itemModel.getStartDate().toString());
                //intent.putExtras(bundle);
                getActivity().startActivity(intent);
               //getItemData.SendData(itemModel);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        myrecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 向ShowDayActivity传递书籍，回调接口
     */
    public interface getItemData{
        public void SendData(ItemModel itemModel);
    }
}
