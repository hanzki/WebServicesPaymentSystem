package services

import java.security.MessageDigest
import java.util.UUID

import play.api.Play

/**
  * Created by hanzki on 12.11.2015.
  */
object TransactionService {

  case class Transaction(id: String, sellerId: String, amount: Int) {
    val mac = calculateChecksum(
      Play.current.configuration.getString("bank.secretkey").get,
      ("transactionId", id),
      ("sellerId", sellerId),
      ("amount", amount)
    )
  }

  def newTransaction(sellerId: String, amount: Int) = {
    Transaction(UUID.randomUUID().toString, sellerId, amount)
  }

  def calculateChecksum(key: String, values: (String, Any)*): String = {
    val input: String = values.
      sortBy(t => t._1.toUpperCase).
      map(t => s"${t._1}=${t._2}")
      .mkString("&") + s"&SECRETKEY=$key"

    val md: MessageDigest = MessageDigest.getInstance("SHA-256")
    val outputBytes = md.digest(input.toUpperCase.getBytes("UTF-8"))

    outputBytes.map("%02X" format _).mkString
  }

}
