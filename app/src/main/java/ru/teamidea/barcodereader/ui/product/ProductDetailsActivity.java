package ru.teamidea.barcodereader.ui.product;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ru.teamidea.barcodereader.R;
import ru.teamidea.barcodereader.data.Product;
import ru.teamidea.barcodereader.data.ProductsData;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMITION = 2;

    private final static String EXTRA_PRODUCT_ID = "product_id";

    public static Intent getLaunchIntent(Context context, int productId) {
        Intent launchIntent = new Intent(context, ProductDetailsActivity.class);
        launchIntent.putExtra(EXTRA_PRODUCT_ID, productId);
        return launchIntent;
    }

    private Product product;

    public TextView code;
    public TextView name;
    public TextView category;
    public TextView price;
    public TextView quantity;
    public TextView firm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey(EXTRA_PRODUCT_ID)) {
            onBackPressed();
        }

        product = ProductsData.getInstance().getProductById(extras.getInt(EXTRA_PRODUCT_ID));
        if (product == null) {
            onBackPressed();
        }

        code = (TextView) findViewById(R.id.code);
        name = (TextView) findViewById(R.id.name);
        category = (TextView) findViewById(R.id.category);
        price = (TextView) findViewById(R.id.price);
        quantity = (TextView) findViewById(R.id.quantity);
        firm = (TextView) findViewById(R.id.firm);

        code.setText(product.getCode());
        name.setText(product.getName());
        category.setText(product.getCategory());
        price.setText(product.getPrice() + " \u20BD");
        quantity.setText("В наличии: " + product.getQuantity());
        firm.setText(product.getFirm());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(product.getName());


        Button ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button error = (Button) findViewById(R.id.button_error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(ProductDetailsActivity.this, Manifest.permission.CAMERA);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    ActivityCompat.requestPermissions(ProductDetailsActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_PERMITION);
                }
            }
        });

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(ProductsData.getInstance().getImageResourceByProductId(product.getId()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMITION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                    return;
                } else {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
            }
        }
    }
}
