package controllers

import play.api.Play
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.TransactionService
import services.TransactionService.Transaction

/**
  * Created by hanzki on 12.11.2015.
  */
object TransactionCtrl extends Controller {

  val SECRET_KEY = Play.current.configuration.getString("bank.secretkey").get

  implicit val transactionWrites = new Writes[Transaction] {
    def writes(transaction: Transaction) = Json.obj(
      "transactionId" -> transaction.id,
      "sellerId" -> transaction.sellerId,
      "amount" -> transaction.amount,
      "mac" -> transaction.mac
    )
  }

  def newTransaction(sellerId: String, amount: Int, mac: String, cardNumber: String) = Action {

    val checksum = TransactionService.calculateChecksum(SECRET_KEY, ("SELLERID", sellerId), ("AMOUNT", amount))

    if(mac.toUpperCase != checksum){
      BadRequest
    } else {
      val transaction = TransactionService.newTransaction(sellerId, amount)
      Ok(Json.toJson(transaction))
    }
  }



}
