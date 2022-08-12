package com.example.mentalaidkit;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



import java.util.ArrayList;

public class WallpaperFragment extends Fragment {

    private ArrayList<image> image = new ArrayList<>();
    private static final int NUM_COLUMNS = 2;
    public static int theme = -1;
    Button d;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_wallpaper, container, false);
        d = root.findViewById(R.id.def);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                theme = -1;
                Toast.makeText(getContext(), "Default Wallpaper Set", Toast.LENGTH_SHORT).show();
                Intent i = new Intent( getContext(), HomeActivity.class);
                startActivity(i);
            }
        });
        getImages(root);
        return root;
    }

    private void getImages(View root){
        Log.d("tag", "initImageBitmaps: preparing bitmaps.");

        image.add(new image(R.drawable.wallpaper1));
        image.add(new image(R.drawable.wallpaper2));
        image.add(new image(R.drawable.wallpaper3));
        image.add(new image(R.drawable.wallpaper4));
        image.add(new image(R.drawable.wallpaper5));




        System.out.println(image);
        initRecyclerView(root);
    }

    private void initRecyclerView(View root){
        Log.d("tag", "initRecyclerView: initializing staggered recyclerview.");


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setItemViewCacheSize(20);
        RecyclerViewAdapter staggeredRecyclerViewAdapter = new RecyclerViewAdapter(this.getContext(), image);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        //vars
        private ArrayList<image> image;
        private Context mContext;

        public RecyclerViewAdapter(Context context, ArrayList<image> image) {
            this.image= image;
            this.mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.wallpaperlayout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageView.setImageResource(image.get(position).getImage());
            final int pos = position;

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theme = image.get(pos).getImage();
                    Toast.makeText(getContext(), "New Wallpaper Set", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent( getContext(), HomeActivity.class);
                    startActivity(i);
                }
            });




        }

        @Override
        public int getItemCount() {
            return image.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView;


            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);

            }
        }
    }
}


