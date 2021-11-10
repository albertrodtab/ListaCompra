package com.alberto.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.alberto.listacompra.domain.Product;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }

    public void add(View view){

        EditText etName = findViewById(R.id.product_name);
        EditText etCategory = findViewById(R.id.product_category);
        EditText etQuantity = findViewById(R.id.product_quantity);
        EditText etPrice = findViewById(R.id.product_price);
        CheckBox checkImportant = findViewById(R.id.important_product);

        etName.requestFocus();
        checkImportant.setChecked(false);

        String name = etName.getText().toString();
        if(etName.getText().toString().equals("")){
            Toast.makeText(this, "Debes escribir el nombre del producto", Toast.LENGTH_LONG).show();
        }else {
            String category = etCategory.getText().toString();
            if (category.equals(""))
                category = "Sin categoría";
            String quantity = etQuantity.getText().toString();
            if (quantity.equals(""))
                quantity = "1";
            String price = etPrice.getText().toString();
            if (price.equals(""))
                price = "0";
            Boolean important = checkImportant.isChecked();

            Product product = new Product(name, category, Integer.parseInt(quantity), Float.parseFloat(price), important);

            /*
            como no tenemos base de datos todavía, debemos hacer publico y estatico el array de
            productos, para poder utilizarlo desde cualquier sitio.
             */
            MainActivity.products.add(product);
            Toast.makeText(this, "Producto " + name + " añadido a la lista",Toast.LENGTH_SHORT).show();

            etName.setText("");
            etCategory.setText("");
            etQuantity.setText("");
            etPrice.setText("");
            checkImportant.setChecked(false);
            etName.requestFocus();
        }

    }
}