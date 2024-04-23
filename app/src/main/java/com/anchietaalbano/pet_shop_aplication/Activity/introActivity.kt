package com.anchietaalbano.pet_shop_aplication.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anchietaalbano.pet_shop_aplication.R
import com.anchietaalbano.pet_shop_aplication.databinding.ActivityIntroBinding

class introActivity : BaseActivity() {
    private lateinit var binding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener{
            startActivity(Intent(this@introActivity, MainActivity::class.java))
        }
    }
}