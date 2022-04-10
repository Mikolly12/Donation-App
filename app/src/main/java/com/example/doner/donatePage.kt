package com.example.doner

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_donate_page.*
import org.json.JSONObject
import org.w3c.dom.Text

class donatePage : AppCompatActivity(), PaymentResultListener {

    lateinit var nameE : Editable
    lateinit var amountE : Editable
    lateinit var emailE : Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_page)

        nameE = findViewById<EditText>(R.id.name).text
        amountE = findViewById<EditText>(R.id.amount).text

        emailE = findViewById<EditText>(R.id.emaill).text
        closeKeyBoard()

        val button = findViewById<Button>(R.id.donate)
        button.setOnClickListener {
            //Toast.makeText(this,"val  :  amount = $amountE || name = $nameE",Toast.LENGTH_SHORT).show()
            if(nameE.isEmpty()  )
            {
                Toast.makeText(this,"Please enter Details",Toast.LENGTH_SHORT).show()
            }
            if( amountE.isEmpty() )
            {

                Toast.makeText(this,"Please enter Details",Toast.LENGTH_SHORT).show()
            }
           if( emailE.isEmpty()) {
               Toast.makeText(this, "Please enter Details", Toast.LENGTH_SHORT).show()
           }
            else
            {
                donateMoney()
            }
        }

    }

    private fun donateMoney() {

        val a = amountE.append("00")

        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name",nameE)
            options.put("description","Donate")
            //You can omit the image option to fetch the image from dashboard
            options.put("theme.color", "#3399cc")
            options.put("currency","INR")
            options.put("amount",a)//pass amount in currency subunits

            val retryObj =  JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            val prefill = JSONObject()
            prefill.put("contact","")

            options.put("prefill",prefill)
            co.open(this,options)
        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }



    override fun onPaymentSuccess(p0: String?) {
        nameE.clear()
        amountE.clear()
        emailE.clear()
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_LONG).show()
        val int = Intent(this,thanks::class.java)
        startActivity(int)
        finish()
        
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        nameE.clear()
        amountE.clear()
        emailE.clear()
        Toast.makeText(this,"Error in payment",Toast.LENGTH_LONG).show()

    }

    fun backButton(view: android.view.View) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}