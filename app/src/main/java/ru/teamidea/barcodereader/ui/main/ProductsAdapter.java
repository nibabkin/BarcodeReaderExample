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
        public TextView productId;
        public TextView code;
        public TextView name;
        public TextView category;
        public TextView price;
        public TextView quantity;

        public ViewHolder(View rootView) {
            super(rootView);
            productId = (TextView) rootView.findViewById(R.id.productId);
            code = (TextView) rootView.findViewById(R.id.code);
            name = (TextView) rootView.findViewById(R.id.name);
            category = (TextView) rootView.findViewById(R.id.category);
            price = (TextView) rootView.findViewById(R.id.price);
            quantity = (TextView) rootView.findViewById(R.id.quantity);
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
            holder.productId.setText("Идентификацонный номер: " + product.getId());
            holder.code.setText("Штрих-код: " + product.getCode());
            holder.name.setText("Наименование: " + product.getName());
            holder.category.setText("Категория: " + product.getCategory());
            holder.price.setText("Цена: " + product.getPrice() + " руб.");
            holder.quantity.setText("Количество: " + product.getQuantity());
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

