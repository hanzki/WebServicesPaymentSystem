package wsdl;

import javax.jws.WebService;

/**
 * Created by hanzki on 12.11.2015.
 */
@WebService
public interface TransactionWS {

    class WSTransaction {
        public String transactionId;
        public String sellerId;
        public int amount;
        public String mac;

        public WSTransaction() {
        }

        public WSTransaction(String transactionId, String sellerId, int amount, String mac) {
            this.transactionId = transactionId;
            this.sellerId = sellerId;
            this.amount = amount;
            this.mac = mac;
        }
    }

    WSTransaction newTransaction(String sellerId, int amount, String mac, String cardNumber);
}
