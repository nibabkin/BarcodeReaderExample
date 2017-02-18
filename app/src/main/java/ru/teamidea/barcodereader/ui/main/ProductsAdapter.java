package ru.teamidea.barcodereader.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.teamidea.barcodereader.R;
import ru.teamidea.barcodereader.data.Product;

/**
 * Created by nikita on 2/18/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private ArrayList<Product> products;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View rootView) {
            super(rootView);
            name = (TextView) rootView.findViewById(R.id.name);
        }
    }

    public ProductsAdapter() {
        products = new ArrayList<>();
    }

    public void updateProducts(ArrayList<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public void updateProduct(Product product) {
        this.products.clear();
        this.products.add(product);
        notifyDataSetChanged();
    }

    public void clearList() {
        this.products.clear();
        notifyDataSetChanged();
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = getProductByPosition(position);
        if (product != null) {
            holder.name.setText(product.getName());
        }

    }

    private Product getProductByPosition(int position) {
        return products.get(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

