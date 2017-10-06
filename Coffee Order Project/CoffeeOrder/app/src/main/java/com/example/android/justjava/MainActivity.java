/*
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    CheckBox whippedCreamCheckBox;
    CheckBox chocolateCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatePrice();
        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        whippedCreamCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculatePrice();
        }});

        chocolateCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculatePrice();
        }});
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText customerName = (EditText) findViewById(R.id.name_edit_field);
        String custName = customerName.getText().toString();

        float price = calculatePrice();
        String priceMessage = createOrderSummary(custName, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + custName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /*
     * Creates the order summary for the order placed.
     */
    private String createOrderSummary(String custName, float price)
    {
        String orderSummary = "Name: " + custName;

        if (whippedCreamCheckBox.isChecked())
        {
            orderSummary += "\nAdded: whipped cream";
        }

        if (chocolateCheckBox.isChecked())
        {
            orderSummary += "\nAdded: chocolate ";
        }
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity++;
            displayQuantity(quantity);
            calculatePrice();
        }
        else
        {
            // Show error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
            calculatePrice();
        }
        else
        {
            // Show error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    public void selectSize(View view)
    {
        calculatePrice();
    }

    /*
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @return total price
     */
    public float calculatePrice()
    {
        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        RadioButton smallSizeRadioButton = (RadioButton) findViewById(R.id.size_small_radiobutton);
        boolean smallSize = smallSizeRadioButton.isChecked();

        RadioButton mediumSizeRadioButton = (RadioButton) findViewById(R.id.size_medium_radiobutton);
        boolean mediumSize = mediumSizeRadioButton.isChecked();

        RadioButton largeSizeRadioButton = (RadioButton) findViewById(R.id.size_large_radiobutton);
        boolean largeSize = largeSizeRadioButton.isChecked();

        float basePrice = 0;

        if (smallSize)
        {
            basePrice += 3;
        }
        else if(mediumSize)
        {
            basePrice += 4;
        }
        else if(largeSize)
        {
            basePrice += 5;
        }
        if (whippedCreamCheckBox.isChecked())
        {
            basePrice += 1;
        }
        if (chocolateCheckBox.isChecked())
        {
            basePrice += 2;
        }


        NumberFormat format = NumberFormat.getCurrencyInstance();
        TextView totalTextView = (TextView) findViewById(R.id.total_textView);
        totalTextView.setText(String.valueOf(format.format(quantity * basePrice)));

        return quantity * basePrice;
    }
}