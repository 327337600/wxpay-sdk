package com.github.cuter44.wxpay.reqs;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.github.cuter44.wxpay.resps.OrderQueryResponse;
import com.github.cuter44.wxpay.WxpayException;
import com.github.cuter44.wxpay.WxpayProtocolException;

/**
 * Created by Mklaus on 15/4/21.
 */
public class OrderQuery extends WxpayRequestBase{

    public static final String URL_API_BASE = "https://api.mch.weixin.qq.com/pay/orderquery";

    protected static final String KEY_OUT_TRADE_NO      = "out_trade_no";
    protected static final String KEY_TRANSACTION_ID    = "transaction_id";

    public static final List<String> KEYS_PARAM_NAME = Arrays.asList(
            "appid",
            "mch_id",
            "nonce_str",
            "out_trade_no",
            "transaction_id",
            "sign"
    );

    //CONSTRUCT
    public OrderQuery(Properties prop){
        super(prop);

        return;
    }

    //BUILD
    @Override
    public OrderQuery build()
    {
        return(this);
    }

    //SIGN
    @Override
    public OrderQuery sign()
        throws UnsupportedEncodingException
    {
        this.sign(KEYS_PARAM_NAME);

        return(this);
    }

    // TO_URL
    public String toURL()
        throws UnsupportedOperationException
    {
        throw(
            new UnsupportedOperationException("This request does not execute on client side")
        );
    }

    // EXECUTE
    @Override
    public OrderQueryResponse execute()
        throws WxpayException, WxpayProtocolException, IOException
    {
        String url = URL_API_BASE;
        String body = this.toXml(KEYS_PARAM_NAME);

        String respXml = this.executePostXML(url, body);

        return(new OrderQueryResponse(respXml));
    }

    /** 商户系统内部的订单号,32个字符内、可包含字母
     */
    public OrderQuery setOutTradeNo(String outTradeNo)
    {
        this.setProperty(KEY_OUT_TRADE_NO, outTradeNo);

        return(this);
    }

    /** 微信的订单号，优先使用
     */
    public OrderQuery setTransactionId(String transactionId)
    {
        this.setProperty(KEY_TRANSACTION_ID,transactionId);

        return (this);
    }
}