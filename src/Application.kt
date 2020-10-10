package org.csuf.cspc411

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import org.csuf.cspc411.Dao.claim.ClaimDao
import org.csuf.cspc411.Dao.person.Claim
//import org.csuf.cspc411.Dao.person.Person
//import org.csuf.cspc411.Dao.person.PersonDao

fun main(args: Array<String>): Unit {
    // Register PersonStore callback functions

    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // extension
    // @annotation
    // routing constructor takes only one parameter which is a lambda function
    // DSL - Domain Specific Language
    routing{
        this.post("/ClaimService/add"){
            println("HTTP message is using GET method with /get ")
            val id = call.request.queryParameters["id"]
            val title : String? = call.request.queryParameters["title"]
            val date : String? = call.request.queryParameters["date"]
            val isSolved : String? = call.request.queryParameters["isSolved"]
            val response = String.format("id: %s and title: %s and date: %s and isSolved: %s", id, title, date, isSolved)
            //
            if (isSolved is String) {
                val cObj = Claim(id, title, date, isSolved.toInt())
                val dao = ClaimDao().addClaim(cObj)
                call.respondText(response, status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
            }
        }

        get("/ClaimService/getAll"){
            val pList = ClaimDao().getAll()
            println("The number of claims : ${pList.size}")
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(pList)
            call.respondText(respJsonStr, status= HttpStatusCode.OK, contentType= ContentType.Application.Json)
        }

        post("/ClaimService/add"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)    // for further processing

            // JSON serialization/deserialization
            // GSON (Google library)

            println("HTTP message is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed. ",
                    status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }
    }

}

