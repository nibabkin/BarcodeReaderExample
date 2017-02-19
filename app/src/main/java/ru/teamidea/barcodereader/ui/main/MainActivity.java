package ru.teamidea.barcodereader.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import ru.teamidea.barcodereader.R;
import ru.teamidea.barcodereader.data.Product;
import ru.teamidea.barcodereader.data.ProductsData;
import ru.teamidea.barcodereader.ui.BarcodeCaptureActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productsRecycler;
    private ProductsAdapter adapter;
    private EditText searchByCode;
    private View clear;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        productsRecycler = (RecyclerView) findViewById(R.id.productRecycler);
        productsRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductsAdapter();
        productsRecycler.setAdapter(adapter);
        productsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 10) {
                    fab.hide();
                } else if (dy < -10) {
                    fab.show();
                }
            }
        });

        searchByCode = (EditText) findViewById(R.id.searchByCode);
        searchByCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    clear.setVisibility(View.INVISIBLE);
                    adapter.clearList();
                } else {
                    clear.setVisibility(View.VISIBLE);
                    ArrayList<Product> serchedProducts = ProductsData.getInstance().getProductsStartsWith(charSequence.toString());
                    adapter.updateProducts(serchedProducts);
                }
                showEmptyViewIfNeeded();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByCode.setText("");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByCode.setText("");
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setCaptureActivity(BarcodeCaptureActivity.class);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        fab.requestFocus();

        empty = (TextView) findViewById(R.id.productRecyclerEmpty);
        showEmptyViewIfNeeded();
    }


    private void showEmptyViewIfNeeded() {
        if (adapter.getItemCount() > 0) {
            empty.setVisibility(View.INVISIBLE);
            productsRecycler.setVisibility(View.VISIBLE);
        } else {
            productsRecycler.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
            if (searchByCode.getText().toString().isEmpty()) {
                empty.setText("Отсканируйте штрихкод продукта или введите номер вручную");
            } else {
                empty.setText("К сожалению, в базе нет товаров с таким номeром :(");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Snackbar.make(searchByCode, "Сканирование отменено", Snackbar.LENGTH_SHORT).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Product scannedProduct = ProductsData.getInstance().getProductByCode(result.getContents());
                if (scannedProduct == null) {
                    Snackbar.make(searchByCode, "Такого товара в базе нет :(", Snackbar.LENGTH_SHORT).show();
                }

                adapter.updateProduct(scannedProduct);
                showEmptyViewIfNeeded();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
