package com.another1dd.balinasofttest.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.another1dd.balinasofttest.R;
import com.another1dd.balinasofttest.rest.model.Offer;
import com.another1dd.balinasofttest.rest.model.Param;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferDetailFragment extends Fragment {


    public OfferDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_offer_detail, container, false);

        Bundle bundle = getArguments();
        Long id = bundle.getLong("id");

        Offer offer = Offer.findById(Offer.class, id);

        String name = offer.getName();
        String description = offer.getDescription();
        String price = "Цена: " + offer.getPrice();
        String picture = offer.getPicture();
        String weight = "";
        List<Param> params = Param.findWithQuery(Param.class, "Select * from Param where offer_Id = ?", String.valueOf(offer.getId()));
        if (params.size()==0)
        {
            weight = "Вес: -";
        }else {
            for (Param param: params)
            {

                if (param.getName().equals("Вес"))
                {
                    weight = "Вес: " + param.getContent();
                }
            }
        }

        TextView nameView = (TextView) view.findViewById(R.id.offer_name);
        TextView descView = (TextView) view.findViewById(R.id.offer_description);
        TextView priceView = (TextView) view.findViewById(R.id.offer_price);
        TextView weightView = (TextView) view.findViewById(R.id.offer_weight);
        ImageView imageView = (ImageView) view.findViewById(R.id.offer_image);

        nameView.setText(name);
        descView.setText(description);
        priceView.setText(price);
        weightView.setText(weight);
        Picasso.with(getContext()).load(picture).into(imageView);

        return view;


    }

}
