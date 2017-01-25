package com.another1dd.balinasofttest.app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.another1dd.balinasofttest.R;
import com.another1dd.balinasofttest.rest.model.Offer;
import com.another1dd.balinasofttest.rest.model.Param;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;


public class CategoryDetailsFragment extends Fragment {
    static int categoryId;
    HashMap<Integer, Integer> categories;
    List<Offer> offersList;

    public CategoryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        fillHashMap();
        Bundle bundle = getArguments();
        Integer position = bundle.getInt("pos");
        categoryId = categories.get(position);
        offersList = Offer.find(Offer.class, "category_Id = ?", String.valueOf(categoryId));
        for (Offer offer : offersList) {
            List<Param> params = Param.find(Param.class, "offer_Id = ?", String.valueOf(offer.getId()));

            if (params.size() == 0) {
                offer.setWeight("Вес: -");
            } else {
                for (Param param : params) {

                    if (param.getName().equals("Вес")) {
                        offer.setWeight("Вес: " + param.getContent());
                    }
                }
            }
        }

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return recyclerView;
    }


    public void fillHashMap() {
        categories = new HashMap<>();
        categories.put(0, 25);
        categories.put(1, 1);
        categories.put(2, 2);
        categories.put(3, 18);
        categories.put(4, 5);
        categories.put(5, 23);
        categories.put(6, 10);
        categories.put(7, 9);
        categories.put(8, 20);
        categories.put(9, 3);
        categories.put(10, 7);
        categories.put(11, 6);
        categories.put(12, 8);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avator;
        public TextView name;
        TextView price;
        TextView weight;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_category_details, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_name);
            price = (TextView) itemView.findViewById(R.id.list_price);
            weight = (TextView) itemView.findViewById(R.id.list_weight);

        }
    }


    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        Context context;


        ContentAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.name.setText(offersList.get(position).getName());
            holder.price.setText(offersList.get(position).getPrice());
            holder.weight.setText(offersList.get(position).getWeight());
            Picasso.with(context).load(offersList.get(position).getPicture()).into(holder.avator);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", offersList.get(holder.getPosition()).getId());
                    bundle.putParcelable("Offer", offersList.get(holder.getPosition()));
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    OfferDetailFragment offerDetailFragment = new OfferDetailFragment();
                    offerDetailFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.containerView, offerDetailFragment).addToBackStack("OfferDetails").commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return offersList.size();
        }
    }

}
