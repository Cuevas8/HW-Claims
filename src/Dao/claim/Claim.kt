package org.csuf.cspc411.Dao.person

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Claim (var id:String?, var title:String?, var date:String?, var isSolved:Int)

fun main() {
    // JSON Serialization
    //var pObj = Person("George", "Sampson", "6475847474")
    var claimObj = Claim("testID", "TestTitle","2020 10-09", 0 )


    //var jsonStr = Gson().toJson(pObj)
    var jsonStr = Gson().toJson(claimObj)




    println("The converted JSON string : ${jsonStr}")

    // Serialization of List<Person>
    var cList : MutableList<Claim> = mutableListOf()
    cList.add(claimObj)
    cList.add(Claim("testID2", "testTitle2","2020 10-09", 0))


    val listJsonString = Gson().toJson(cList)
    //JSONArray object
    println("${listJsonString}")

    // List<Person> object deserialization
    val claimListType : Type = object : TypeToken<Claim>(){}.type

    // JSON Deserialization
    var pObj1 : Claim
    jsonStr = "{\"id\":\"testID3\", \"title\":\"testTitle3\", \"date\":\"2020 10-10\", \"isSolved\":\"0\"}"
    pObj1 = Gson().fromJson(jsonStr, Claim::class.java)
    println("${pObj1.toString()}")
   // (id text, title text, date text, isSolved int)
}