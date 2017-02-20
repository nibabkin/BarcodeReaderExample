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
        public TextView code;
        public TextView name;
        public TextView category;
        public TextView price;
        public TextView quantity;
        public TextView firm;

        public ViewHolder(View rootView) {
            super(rootView);
            code = (TextView) rootView.findViewById(R.id.code);
            name = (TextView) rootView.findViewById(R.id.name);
            category = (TextView) rootView.findViewById(R.id.category);
            price = (TextView) rootView.findViewById(R.id.price);
            quantity = (TextView) rootView.findViewById(R.id.quantity);
            firm = (TextView) rootView.findViewById(R.id.firm);
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
            holder.code.setText(product.getCode());
            holder.name.setText(product.getName());
            holder.category.setText(product.getCategory());
            holder.price.setText(product.getPrice() + " \u20BD");
            holder.quantity.setText("В наличии: " + product.getQuantity());
            holder.firm.setText(product.getFirm());
        }

    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    private Product getProductByPosition(int position) {
        return products.get(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

