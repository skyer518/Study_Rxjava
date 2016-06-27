package cn.com.u2be.chart1.study_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.u2be.chart1.study_rxjava.entity.Contact;

public class RecyclerListActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_recycler_view);
        ButterKnife.inject(this);
        //适配器提供的内容的大小不会改变 RecyclerView的大小
        recyclerView.setHasFixedSize(true);
        // 设置布局管理器
//        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//GridLayout
        //
        RecyclerAdapter adapter = new RecyclerAdapter(initDatat());
        recyclerView.setAdapter(adapter);


    }



    public List<Contact> initDatat() {


        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1, "张三0", "18654978526"));
        contacts.add(new Contact(2, "张三1", "18654978536"));
        contacts.add(new Contact(3, "张三2", "18654978546"));
        contacts.add(new Contact(4, "张三3", "18654978556"));
        contacts.add(new Contact(5, "张三4", "18654978566"));
        contacts.add(new Contact(6, "张三5", "18654978576"));
        contacts.add(new Contact(7, "张三6", "18654978586"));


        contacts.add(new Contact(11, "张三10", "18654978521"));
        contacts.add(new Contact(12, "张三11", "18654978531"));
        contacts.add(new Contact(13, "张三12", "18654978541"));
        contacts.add(new Contact(14, "张三13", "18654978551"));
        contacts.add(new Contact(15, "张三14", "18654978561"));
        contacts.add(new Contact(16, "张三15", "18654978571"));
        contacts.add(new Contact(17, "张三16", "18654978581"));

        return contacts;
    }


    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


        public RecyclerAdapter(List<Contact> mData) {
            this.mData = mData;
        }

        private List<Contact> mData;

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(getClass().getName(), "onCreateViewHolder:" + viewType);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_line, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.tvKey.setText("");
            final Contact contact = mData.get(position);
            holder.tvKey.setText(contact.getId() + "");
            holder.tvName.setText(contact.getName());
            holder.tvTel.setText(contact.getTel());
        }


        @Override
        public int getItemCount() {
            return mData.size();
        }

        public List<Contact> getmData() {
            return mData;
        }

        public void setmData(List<Contact> mData) {
            this.mData = mData;
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_Key)
        TextView tvKey;
        @InjectView(R.id.tv_Name)
        TextView tvName;

        @InjectView(R.id.tv_Tel)
        TextView tvTel;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvKey = (TextView) itemView.findViewById(R.id.tv_Key);
            tvName = (TextView) itemView.findViewById(R.id.tv_Name);
            tvTel = (TextView) itemView.findViewById(R.id.tv_Tel);
        }
    }


}
