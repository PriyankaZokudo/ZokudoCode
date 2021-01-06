package com.zokudo.merchant.helper;

import com.zokudo.merchant.model.MerchantModel;
import com.zokudo.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ConfigHelper {

    @Autowired
    private static MerchantRepository merchantRepository;

    @Autowired
    public void setSmsLogRepository(MerchantRepository merchantRepository) {
        ConfigHelper.merchantRepository = merchantRepository;
    }

    public static String getCampaignNumber() {
        return "ZOK" + System.currentTimeMillis() / 1000L;
    }
    public static ModelAndView sessionModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MerchantModel merchantModel = merchantRepository.findMerchantByEmail(auth.getName());
        modelAndView.addObject("user_name", merchantModel.getMerchantFullName());
        modelAndView.addObject("mid", merchantModel.getmId());
        modelAndView.addObject("tid", merchantModel.gettId());
        modelAndView.addObject("merchant_id", merchantModel.getId());
        modelAndView.addObject("mobile", merchantModel.getMobileNo());
        return modelAndView;
    }
}
