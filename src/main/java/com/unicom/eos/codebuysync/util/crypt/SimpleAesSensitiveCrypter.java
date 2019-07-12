package com.unicom.eos.codebuysync.util.crypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleAesSensitiveCrypter
 *
 * @author unisk1123
 * @create 2018/7/31
 */
public class SimpleAesSensitiveCrypter {
    private static final Logger logger = LoggerFactory.getLogger(SimpleAesSensitiveCrypter.class);

    private AesCryptor aesCryptor;
    private String password = "a47bEA5c02655520";
    private static SimpleAesSensitiveCrypter simpleAesSensitiveCrypter = new SimpleAesSensitiveCrypter();;

    public SimpleAesSensitiveCrypter() {
        this.aesCryptor = new AesCryptor(password);
    }
    //类资源占用不大，而且经常会用到，饿汉式即可
    public static SimpleAesSensitiveCrypter getInstance() {
        return simpleAesSensitiveCrypter;
    }

    //加密
    public String encrypt(String data) {
        try{
            return aesCryptor.encrypt(data);
        }catch (Exception e){
            logger.error("encrypt error.data:" + data, e);
            return data;
        }
    }

    //解密
    public String decrypt(String data) {
        try{
            return aesCryptor.decrypt(data);
        }catch (Exception e){
            logger.error("encrypt error.data:" + data, e);
            return data;
        }
    }

}
