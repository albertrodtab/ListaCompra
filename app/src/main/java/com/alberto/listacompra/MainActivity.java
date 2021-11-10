 package com.alberto.listacompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alberto.listacompra.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static ArrayList<Product> products;
    private ArrayAdapter<Product> productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Esta lista es la que guarda los datos de mi app, pero debo vuncularla con la lista
        de mi layout que mostrará los datos de la lista en pantalla.
         */
        products = new ArrayList<>();

        /*
        Creo un ArrayAdapter, que listará automaticamente mi lista, lo instancio y le digo como quiero
        que lo muestre y de donde coge los datos.
        Por ultimo le digo que la listview y products adapter esten siempre emparejados
         */
        ListView lvProducts = findViewById(R.id.products_list);
        productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        lvProducts.setAdapter(productsAdapter);
        /*
        En la clase product debo elegir como quiero que muestre el dato ya que sino lo muestra
        segun el metodo to String, lo adaptamos a como nosotros queremos.
         */
        /*
        esta clase implementa de AdapterView.OnItemClickListener para que sepa que tiene que estar
        atenta a los clicks en los items de la lista
        Luego con el metodo onItemClick le digo lo que tiene que hacer cuando hagan click en un item
         */
        lvProducts.setOnItemClickListener(this);
    }

     @Override
     protected void onResume() {
         super.onResume();

         productsAdapter.notifyDataSetChanged();
         makeSummary();

     }

     private void makeSummary(){
        int productCount = products.size();
        double totalPrice = products.stream()
                .map(Product::getPrice)
                .mapToDouble(price -> price).sum();

        /*
        Como controlar el formato del número decimal.
        en el decimal formar a la hora de imprimirlo deberíamos escribir
        formateador.format(totalPrice);
         */
        DecimalFormat formateador = new DecimalFormat("##.##");

        /*
        Con BigDecimal lo que hacemos es definir el numero de decimales y a hacia donde debe redondear.
         */

        BigDecimal bigDecimal = new BigDecimal(totalPrice).setScale(2,RoundingMode.UP);


        TextView tvSummary = findViewById(R.id.summary);
        tvSummary.setText("Tu compra tiene: " + productCount + " productos y te cuesta: " + bigDecimal.toString() + " €.");
    }

     /*
     Con esto vinculamos la barra de menú para que salga en nuestro Layaout principal
      */
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.actionbar_main, menu);
         return true;
     }

     /*
     Con este metodo hacemos que el boton de nuestra actionbar se vincule con nuestra otra activity.class
      */
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if (item.getItemId() == R.id.new_item){
             Intent intent = new Intent(this, NewItemActivity.class);
             startActivity(intent);
             return  true;

         }
         return false;
     }

     /*
     Al hacer click me está diciendo la posición del elemento entonces le digo que vaya a la activity
     productDetail, para que muestre la información.
     A
      */
     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         Product product = products.get(position);

         Intent intent = new Intent(this, ProductDetail.class);
         /*
         esto me permite meter información en el Intent y enviarla a la otra activity
          */
         intent.putExtra("name", product.getName());
         startActivity(intent);

     }
 }