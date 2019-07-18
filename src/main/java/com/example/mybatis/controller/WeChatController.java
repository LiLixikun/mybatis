package com.example.mybatis.controller;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.config.WeChatAccountConfig;
import com.example.mybatis.entity.UserInfo;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@RequestMapping("/wechat")
@Controller
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Autowired
    protected UserService userService;

    @GetMapping("/auth")
    public String auth(@RequestParam("returnUrl") String returnUrl) {
        String url = weChatAccountConfig.getBaseHttp() + "/sell/wechat/userInfo";
        log.info("return={}", returnUrl);
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/userInfo")
    public String getOpenid(@RequestParam("code") String code,
                            @RequestParam("state") String returnUrl) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String access_token = wxMpOAuth2AccessToken.getAccessToken();

        log.info("【微信网页授权】openId={}", openId);
        log.info("【微信网页授权】access_token={}", access_token);

        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        log.info("获取到的wxMpUser={}", wxMpUser);

        //如果用户没有关注公众号添加进去
        if(userService.getUserById(wxMpUser.getOpenId())==null){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(wxMpUser, userInfo);
            userService.addUser(userInfo);
        }

        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @GetMapping("/qrAuth")
    public String arAuth(@RequestParam("returnUrl") String returnUrl) {
        String url = weChatAccountConfig.getBaseHttp() + "/wechat/qrUserInfo";
        log.info("return={}", returnUrl);
        String redirectUrl = wxMpService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        log.info("【网页授权地址】redirectUrl=", redirectUrl);
        return redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String QrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) throws Exception {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();

        String access_token = wxMpOAuth2AccessToken.getAccessToken();

        log.info("【微信授权token是={}】", access_token);

        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        log.info("获取到的wxMpUser={}", wxMpUser);

        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @GetMapping("/creat")
    public ResultVO creat(){

        return ResultUtil.success();
    }
}
