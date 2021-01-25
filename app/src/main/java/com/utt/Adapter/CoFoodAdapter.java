package com.utt.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.utt.config.Config;
import com.utt.model.DataProducts;
import com.utt.moonlight.R;

import java.util.List;

public class CoFoodAdapter extends RecyclerView.Adapter<CoFoodAdapter.RecyclerViewHolder> {


    private List<DataProducts> dataList;
    private List<DataProducts> dataListFiltered;
    private GridCommonAdapter gridCommonAdapter;
    public static boolean check = false;
    Context context;
    private boolean aBoolean = false;
    OnShareClickedListener mCallback;
    public interface OnShareClickedListener {
        void ShareDesClicked(boolean position);
    }

    public CoFoodAdapter(List<DataProducts> data, Context context, OnShareClickedListener onShareClickedListener) {
        this.dataList = data;
        this.context = context;
        this.dataListFiltered = data;
        this.mCallback = onShareClickedListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view_grid, parent, false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.txtNameProduct.setText( dataList.get(position).getTensanpham());
        holder.txtPrice.setText(String.valueOf(dataList.get(position).getGiagoc()));
        final String urlImage = Config.API_URL+""+dataList.get(position).getUrl();
        Log.e("url",urlImage+"");
        Picasso.with(context).load(urlImage).into(holder.imgProduct);
        aBoolean = true;
        mCallback.ShareDesClicked(aBoolean);

        holder.imgProduct.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialog(urlImage);
                return false;
            }
        });
        check = true;



//        holder.imgProduct.setImageDrawable(LoadImageFromWebOperations(urlImage));


//        if (dataList.get(position).getHasbusstop_home() == aBoolean){
//            holder.imageView.setImageResource(R.drawable.success);
//        }else {
//            holder.imageView.setImageResource(R.drawable.success);
//        }
    }

    private void showDialog(String url){
//        if (list == null){
//            return;
//        }
        final Dialog dialog;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_image);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ImageView back = (ImageView) dialog.findViewById(R.id.imgBackImageLayout);
        ImageView image = (ImageView) dialog.findViewById(R.id.imgImageLayoutDialog);
        Picasso.with(context).load(url).into(image);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    private static OnItemClickListener listener;

//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    dataListFiltered = dataList;
//                } else {
//                    List<Data> filteredList = new ArrayList<>();
//                    for (Data row : dataList) {
//                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
//                            filteredList.add(row);
//                        }
//                    }
//                    dataListFiltered = filteredList;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = dataListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                dataList.clear();
//                dataList = (ArrayList<Data>) filterResults.values;
//                dataList.addAll(dataListFiltered);
//                Log.d("aaa",filterResults+" ");
//
//
//                // refresh the list with filtered DataPothole
//                notifyDataSetChanged();
//            }
//        };
//    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct,imgAddToCart;
        TextView txtNameProduct;
        TextView txtPrice;
        public RecyclerViewHolder(final View itemView) {
            super(itemView);

            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            txtNameProduct = (TextView) itemView.findViewById(R.id.txtNameProduct);
            imgAddToCart = (ImageView) itemView.findViewById(R.id.imgAddToCart);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);


        }
    }


}

