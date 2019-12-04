/*
 * Project: biz-api
 * 
 * File Created at 2017年3月30日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package cn.eastlegend.util;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/**
 * SHA1签名工具
 * 
 * @author chenxian
 * @date 2017年4月1日 - 下午4:33:15
 * @history
 *          2017年4月1日 - 下午4:33:15 chenxian create.
 */
public class Sha1Util {
    /**
     * 
     * 获取时间戳
     * 
     * @return String
     * @author chenxian
     * @date 2017年4月1日 - 下午4:34:02
     * @history
     *          2017年4月1日 - 下午4:34:02 chenxian create.
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 创建签名SHA1
     * 
     * @param signParams
     *            创建签名的参数列表
     * @return String
     * @throws Exception
     *             异常
     * @author chenxian
     * @date 2017年4月1日 - 下午4:34:20
     * @history
     *          2017年4月1日 - 下午4:34:20 chenxian create.
     */
    public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
        StringBuffer sb = new StringBuffer();
        Set<Entry<String, String>> es = signParams.entrySet();
        Iterator<Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            sb.append(k + "=" + v + "&");

        }
        String params = sb.substring(0, sb.lastIndexOf("&"));

        return getSha1(params);
    }

    /**
     * 创建签名SHA1
     * 
     * @param signParam1
     *            参数1
     * @param signParam2
     *            参数2
     * @param signParam3
     *            参数3
     * @return String
     * @throws Exception
     *             异常
     * @author chenxian
     * @date 2017年4月1日 - 下午4:35:36
     * @history
     *          2017年4月1日 - 下午4:35:36 chenxian create.
     */
    public static String createSHA1Sign(String signParam1, String signParam2, String signParam3) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(signParam1);
        sb.append(signParam2);
        sb.append(signParam3);

        return getSha1(sb.toString());
    }

    /**
     * Sha1签名
     * 
     * @param str
     *            输入字符串
     * @return String
     * @author chenxian
     * @date 2017年4月1日 - 下午4:36:40
     * @history
     *          2017年4月1日 - 下午4:36:40 chenxian create.
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
