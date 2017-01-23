package com.another1dd.balinasofttest.app;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.another1dd.balinasofttest.R;


public class CatalogFragment extends Fragment {


    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        public TextView name;
        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_catalog, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.cat_picture);
            name = (TextView) itemView.findViewById(R.id.cat_title);
        }
    }



    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private int length = 0;
        private final String[] mCategories;
        private final Drawable[] mCategoriesPictures;

        ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mCategories = resources.getStringArray(R.array.categories);
            length = mCategories.length;
            TypedArray a = resources.obtainTypedArray(R.array.categories_picture);
            mCategoriesPictures = new Drawable[a.length()];
            for (int i = 0; i < mCategoriesPictures.length; i++) {
                mCategoriesPictures[i] = a.getDrawable(i);
            }

            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mCategoriesPictures[position % mCategoriesPictures.length]);
            holder.name.setText(mCategories[position % mCategories.length]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", holder.getAdapterPosition());
                    FragmentTransaction fragmentTransaction  =  getFragmentManager().beginTransaction();
                    CategoryDetailsFragment fragment = new CategoryDetailsFragment();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.containerView, fragment).addToBackStack("CategoryDetails").commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return length;
        }


    }
}
