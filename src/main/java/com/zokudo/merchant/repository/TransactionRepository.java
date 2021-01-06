package com.zokudo.merchant.repository;


import com.zokudo.merchant.model.MerchantTransactionModel;
import com.zokudo.merchant.model.Transaction;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    /*@Query(value =
    List<MerchantTransactionModel> getMerchanatTransactionbyEmail(String name);*/

    @Query(value = "SELECT t.* FROM `transaction` AS t JOIN `merchant` AS m ON t.merchant_id= m.m_id WHERE m.email = :email", nativeQuery = true)
    List<Transaction> getMerchanatTransactionbyEmail(@Param("email") String email);

   /* @Query(value = "SELECT t.* FROM `transaction` AS t LEFT JOIN `mandate` AS m ON t.umrn = m.umrn WHERE m.merchant_id = :merchant_id", nativeQuery = true)
    List<Transaction> getTransactionByMerchantId(@Param("merchant_id") Integer merchant_id);

    @Query(value = "SELECT t.corporate_config_id,t.umrn,t.corporate_account_number,t.customer_name,t.customer_account_number,t.settlement_date,t.destination_bank_id,t.amount,t.frequency,t.unique_key,t.narration FROM transaction AS t WHERE t.api_flag = 0 AND t.settlement_date = CURDATE();", nativeQuery = true)
    List<JSONObject>  getAllCronTransaction();

    Transaction findTransactionByUniqueKey(String unique_key);

    @Query(value = "SELECT transaction_id FROM transaction order by transaction_id desc limit 1", nativeQuery = true)
    Integer getLastInsertedId();

    @Query(value = "SELECT COUNT(*) AS cnt, CAST(SUM(amount) AS DECIMAL(12,2)) AS sum_amnt FROM transaction", nativeQuery = true)
    JSONObject getTransactionCntAndSumAmount();*/
}
