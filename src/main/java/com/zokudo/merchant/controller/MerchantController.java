package com.zokudo.merchant.controller;

import com.zokudo.merchant.configuration.TransactionExcelExporter;
import com.zokudo.merchant.helper.ConfigHelper;
import com.zokudo.merchant.model.MerchantModel;
import com.zokudo.merchant.model.Transaction;
import com.zokudo.merchant.repository.MerchantRepository;
import com.zokudo.merchant.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
public class MerchantController {

    @Value("${merchant_service_api_header_key}")
    private String merchant_service_api_header_key;

    @Value("${merchant_host}")
    private String merchant_host;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public static final String REST_SERVICE_URL = "http://localhost:8091/merchant/";

    @RequestMapping(value= {"/merchant/dashboard"}, method= RequestMethod.GET)
    public ModelAndView getDashboard() {
        log.debug("******* Entering into getDashboard() *******");
        ModelAndView model = ConfigHelper.sessionModelAndView();
        model.addObject("merchant_host", merchant_host);
        model.addObject("merchant_service_api_header_key", merchant_service_api_header_key);
        model.setViewName("/merchant/dashboard");
        log.debug("******* Exiting from getDashboard() *******");
        return model;
    }

    @RequestMapping(value= {"/merchant/publish"}, method= RequestMethod.GET)
    public ModelAndView getPublish() {
        log.debug("******* Entering into getPublish() *******");
        ModelAndView model = ConfigHelper.sessionModelAndView();
        model.addObject("merchant_host", merchant_host);
        model.addObject("merchant_service_api_header_key", merchant_service_api_header_key);
        model.addObject("campaignNumber",ConfigHelper.getCampaignNumber());
        model.setViewName("/merchant/publish");
        log.debug("******* Exiting from getPublish() *******");
        return model;
    }

    @RequestMapping(value= {"/merchant/publish-data"}, method= RequestMethod.POST)
    public ModelAndView getPublishData() {
        log.debug("******* Entering into getPublishData() *******");
        ModelAndView model = ConfigHelper.sessionModelAndView();
        model.addObject("merchant_host", merchant_host);
        model.addObject("merchant_service_api_header_key", merchant_service_api_header_key);
        model.setViewName("/merchant/publish-data");
        log.debug("******* Exiting from getPublishData() *******");
        return model;
    }
    @GetMapping(value = {"/merchant/report/transaction-report"})
    public ModelAndView transactionReport() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MerchantModel merchantModel = merchantRepository.findMerchantByEmail(auth.getName());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", merchant_service_api_header_key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Transaction>> response = restTemplate.exchange(merchant_host+"/merchant/get-transaction-report-data/"+auth.getName(), HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<Transaction>>() {});
        List<Transaction> transactionList = response.getBody();
        log.info("merchant_id Rest...:: "+transactionList.get(1).getMerchantId());
        model.addObject("title", "Transaction Report");
        model.addObject("user_name", merchantModel.getMerchantFullName());
        model.addObject("transactionList", transactionList);
        model.setViewName("/merchant/reports/transaction-report");
        return model;
    }
    @GetMapping("/merchant/report/transaction-report/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String merchant_id=request.getParameter("merchant_id");
        log.info("merchant_id:: "+auth.getName());
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //or yyyy-MM-dd_HH:mm:ss
        String currentDateTime = dateFormat.format(new Date());
        String filename = "transaction-report_" + currentDateTime + ".xlsx"; //can be change to ".csv"

        String headerValue = "attachment; filename=" + filename;
        response.setHeader(headerKey, headerValue);
        // List<Transaction> transactionList1=transactionRepository.getMerchanatTransactionbyEmail(auth.getName());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", merchant_service_api_header_key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Transaction>> restResponse = restTemplate.exchange(merchant_host+"/merchant/get-transaction-report-data/"+auth.getName(), HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<Transaction>>() {});
        List<Transaction> transactionList = restResponse.getBody();
        TransactionExcelExporter transactionExcelExporter = new TransactionExcelExporter(transactionList);
        transactionExcelExporter.export(response);
    }

}

