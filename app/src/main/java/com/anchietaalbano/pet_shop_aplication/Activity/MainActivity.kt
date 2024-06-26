package com.anchietaalbano.pet_shop_aplication.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.anchietaalbano.pet_shop_aplication.Adapter.BestSellerAdapter
import com.anchietaalbano.pet_shop_aplication.Adapter.CategoryAdapter
import com.anchietaalbano.pet_shop_aplication.Adapter.SliderAdapter
import com.anchietaalbano.pet_shop_aplication.Model.SlideModel
import com.anchietaalbano.pet_shop_aplication.ViewlModel.MainViewModel
import com.anchietaalbano.pet_shop_aplication.databinding.ActivityIntroBinding
import com.anchietaalbano.pet_shop_aplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanners()
        initCategories()
        initBestSeller()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener{startActivity(Intent(this, CartActivity::class.java))}
    }

    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility=View.VISIBLE
        viewModel.bestSeller.observe(this, Observer{
            binding.viewBestSeller.layoutManager=GridLayoutManager(this, 2)
            binding.viewBestSeller.adapter= BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility = View.GONE
        })
        viewModel.loadBestSeller()
    }

    private fun initCategories() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this, Observer{
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initBanners() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer{
            banners(it)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<SlideModel>) {
        binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }

    }

}