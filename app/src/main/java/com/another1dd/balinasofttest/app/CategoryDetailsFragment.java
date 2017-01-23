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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CategoryDetailsFragment extends Fragment {
    static int categoryId;
    HashMap<Integer,Integer> categories;

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

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return recyclerView;
    }



    public void fillHashMap()
    {
        categories = new HashMap<>();
        categories.put(0,25);
        categories.put(1,1);
        categories.put(2,2);
        categories.put(3,18);
        categories.put(4,5);
        categories.put(5,23);
        categories.put(6,10);
        categories.put(7,9);
        categories.put(8,20);
        categories.put(9,3);
        categories.put(10,7);
        categories.put(11,6);
        categories.put(12,8);
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
        private final String[] mNames;
        private final String[] mPrices;
        private final String[] mWeight;
        private final String[] mPictures;
        private final Long[] mId;

        ContentAdapter(Context context) {

            ArrayList<String> aNames = new ArrayList<>();
            ArrayList<String> aPrice = new ArrayList<>();
            ArrayList<String> aWeight = new ArrayList<>();
            ArrayList<String> aPictures = new ArrayList<>();
            ArrayList<Long> aId = new ArrayList<>();
            List<Offer> offers = Offer.findWithQuery(Offer.class, "Select * from Offer where category_Id = ?", String.valueOf(categoryId));

            for (Offer offer: offers)
            {
                List<Param> params = Param.findWithQuery(Param.class, "Select * from Param where offer_Id = ?", String.valueOf(offer.getId()));
                if (params.size()==0)
                {
                    aWeight.add("Вес: -");
                }else {
                    for (Param param: params)
                    {

                        if (param.getName().equals("Вес"))
                        {
                            aWeight.add("Вес: " + param.getContent());
                        }
                    }
                }
                aPictures.add(offer.getPicture());
                aNames.add(offer.getName());
                aId.add(offer.getId());
                aPrice.add("Цена: " + offer.getPrice());
            }

            mNames = aNames.toArray(new String[aNames.size()]);
            mPrices = aPrice.toArray(new String[aPrice.size()]);
            mWeight = aWeight.toArray(new String[aWeight.size()]);
            mPictures = aPictures.toArray(new String[aPictures.size()]);
            mId = aId.toArray(new Long[aId.size()]);
            this.context = context;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            //holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            holder.name.setText(mNames[position % mNames.length]);
            holder.price.setText(mPrices[position % mPrices.length]);
            holder.weight.setText(mWeight[position % mWeight.length]);
            Picasso.with(context).load(mPictures[position % mWeight.length]).into(holder.avator);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("id",mId[holder.getAdapterPosition() % mId.length]);
                    FragmentTransaction fragmentTransaction  =  getFragmentManager().beginTransaction();
                    OfferDetailFragment offerDetailFragment = new OfferDetailFragment();
                    offerDetailFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.containerView, offerDetailFragment).addToBackStack("OfferDetails").commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNames.length;
        }
    }

}
