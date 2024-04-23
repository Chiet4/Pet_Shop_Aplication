package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import com.anchietaalbano.pet_shop_aplication.Helper.ChangeNumberItemsListener
import com.anchietaalbano.pet_shop_aplication.Helper.TinyDB
import com.anchietaalbano.pet_shop_aplication.Model.itemsModel


class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItems(item: itemsModel) {
        var listItems = getListCart()
        val existAlready = listItems.any { it.title == item.title }
        val index = listItems.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItems[index].numberInCart = item.numberInCart
        } else {
            listItems.add(item)
        }
        tinyDB.putListObject("CartList", listItems)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<itemsModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(listItems: ArrayList<itemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listItems[position].numberInCart == 1) {
            listItems.removeAt(position)
        } else {
            listItems[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun plusItem(listItems: ArrayList<itemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        listItems[position].numberInCart++
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}