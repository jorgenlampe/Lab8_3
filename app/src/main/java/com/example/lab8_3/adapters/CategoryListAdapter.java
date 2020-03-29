package com.example.lab8_3.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8_3.R;
import com.example.lab8_3.activities.CategoryListActivity;
import com.example.lab8_3.entities.Category;
import com.example.lab8_3.entities.Contact;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>{
    private List<Category> categories;
    private final LayoutInflater mInflater;
    private Category selectedCategory;

    public interface OnCheckedChangeListener {
        void onItemChanged(int position, String answer);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView categoryView;
        public MyViewHolder(View v) {
            super(v);
            categoryView = v.findViewById(R.id.category);
        }
        public void Bind(int position) {
            System.out.println(categories.get(position).getDescription());
            if (categories != null) {
                Category current = categories.get(position);
                categoryView.setText(current.getDescription().toString());
            } else {
                categoryView.setText("No name");
            }
            categoryView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Category category = categories.get(getAdapterPosition());

                    if(category == getSelected()) {
                        categoryView.setBackgroundColor(Color.WHITE);
                        setSelected(null);
                    } else {
                        categoryView.setBackgroundColor(Color.LTGRAY);
                        setSelected(category);
                    }
                    System.out.println(category.getDescription());
                }
            });
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryListAdapter(List<Category> myDataset, Context context) {
        categories = myDataset;
        mInflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view

        View itemView = mInflater.inflate(R.layout.recyclerview_category_item, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Bind(position);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();
    }
    public Category getSelected() {

        return selectedCategory;
    }


    public void setSelected(Category category){

        selectedCategory = (category);
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }
}
