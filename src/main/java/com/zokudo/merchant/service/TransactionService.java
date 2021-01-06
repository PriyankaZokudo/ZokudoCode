/*
package com.zokudo.merchant.service;

import com.zokudo.merchant.model.MerchantTransactionModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class TransactionService implements ITransactionService{

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<MerchantTransactionModel> getMerchanatTransactionbyEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t.* from Transaction t inner join MerchantModel m on t.merchantId= m.mId and m.email='"+email+"'");
        @SuppressWarnings("unchecked")
        List<MerchantTransactionModel> list =(List<MerchantTransactionModel>)query.getResultList();
       // System.out.println("Student Name :");
        em.close();

        return list;

    }
}
*/
