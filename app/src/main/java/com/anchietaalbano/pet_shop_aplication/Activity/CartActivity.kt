package com.anchietaalbano.pet_shop_aplication.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.anchietaalbano.pet_shop_aplication.Adapter.CartAdapter
import com.anchietaalbano.pet_shop_aplication.Helper.ChangeNumberItemsListener
import com.anchietaalbano.pet_shop_aplication.R
import com.anchietaalbano.pet_shop_aplication.databinding.ActivityCartBinding
import com.anchietaalbano.pet_shop_aplication.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        setVariable()
        initCartList()
        calculateCart()
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100.0
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun initCartList() {
        binding.cartView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}