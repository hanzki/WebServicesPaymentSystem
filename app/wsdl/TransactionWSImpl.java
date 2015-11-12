package wsdl;

import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.Seq;
import services.TransactionService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;

/**
 * Created by hanzki on 12.11.2015.
 */
@WebService(endpointInterface = "wsdl.TransactionWS")
public class TransactionWSImpl implements TransactionWS {

    private static String SECRET_KEY = "KEKKONEN";

    @WebMethod
    @Override
    public WSTransaction newTransaction(String sellerId, int amount, String mac, String cardNumber) {
        Tuple2<String, Object> t1 = new Tuple2<>("SELLERID", sellerId);
        Tuple2<String, Object> t2 = new Tuple2<>("AMOUNT", amount);
        Seq tuples = JavaConversions.asScalaBuffer(Arrays.asList(t1, t2)).toSeq();

        String checksum = TransactionService.calculateChecksum(SECRET_KEY, tuples);

        TransactionService.Transaction t = TransactionService.newTransaction(sellerId, amount);


        return new WSTransaction(t.id(), t.sellerId(), t.amount(), t.mac());

    }
}
