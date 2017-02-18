package ru.teamidea.barcodereader.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                    adapter.clearList();
                } else {
                    ArrayList<Product> serchedProducts = ProductsData.getInstance().getProductsStartsWith(charSequence.toString());
                    adapter.updateProducts(serchedProducts);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Сканирование отменено", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Product scannedProduct = ProductsData.getInstance().getProductByCode(result.getContents());
                adapter.updateProduct(scannedProduct);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
