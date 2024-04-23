package com.anchietaalbano.pet_shop_aplication.ViewlModel

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anchietaalbano.pet_shop_aplication.Model.CategoryModel
import com.anchietaalbano.pet_shop_aplication.Model.SlideModel
import com.anchietaalbano.pet_shop_aplication.Model.itemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel:ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SlideModel>>()
    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    private val _bestSeller=MutableLiveData<MutableList<itemsModel>>()


    val banners: LiveData<List<SlideModel>> = _banner
    val category: LiveData<MutableList<CategoryModel>> = _category
    val bestSeller: LiveData<MutableList<itemsModel>> = _bestSeller

    fun loadBanners(){
        val Ref=firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SlideModel>()
                for(childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SlideModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                    _banner.value=lists
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadCategory(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<CategoryModel>()
                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _category.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun loadBestSeller(){
        val Ref=firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<itemsModel>()

                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(itemsModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _bestSeller.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}