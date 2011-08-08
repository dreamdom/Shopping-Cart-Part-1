package com.dreamdom.tutorials.shoppingcart;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productdetails);
		
		List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());
		final List<Product> cart = ShoppingCartHelper.getCart();
		
		int productIndex = getIntent().getExtras().getInt(ShoppingCartHelper.PRODUCT_INDEX);
		final Product selectedProduct = catalog.get(productIndex);
		
		// Set the proper image and text
		ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);
		productImageView.setImageDrawable(selectedProduct.productImage);
		TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
		productTitleTextView.setText(selectedProduct.title);
		TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);
		productDetailsTextView.setText(selectedProduct.description);
		
		Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
		addToCartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				cart.add(selectedProduct);
				finish();
			}
		});
		
		// Disable the add to cart button if the item is already in the cart
		if(cart.contains(selectedProduct)) {
			addToCartButton.setEnabled(false);
			addToCartButton.setText("Item in Cart");
		}
	}
	
}
