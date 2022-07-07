package com.service;

/**
 * @Author Kevyn
 * @Date 2020/12/26 19:21
 * @Version 1.0
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HttpClientPoolUtil {

    public static void main(String[] args) {

        String logo           = " ____                        __                      \n" +
                "/\\  _`\\                     /\\ \\                     \n" +
                "\\ \\ \\L\\ \\    ___     ___ ___\\ \\ \\____     __   _ __  \n" +
                " \\ \\  _ <'  / __`\\ /' __` __`\\ \\ '__`\\  /'__`\\/\\`'__\\\n" +
                "  \\ \\ \\L\\ \\/\\ \\L\\ \\/\\ \\/\\ \\/\\ \\ \\ \\L\\ \\/\\  __/\\ \\ \\/ \n" +
                "   \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\_,__/\\ \\____\\\\ \\_\\ \n" +
                "    \\/___/  \\/___/  \\/_/\\/_/\\/_/\\/___/  \\/____/ \\/_/ \n" +
                "                                                     ";
        System.out.println(logo);

        Set<String> linksSet = getLinksSet("target_phone");

        Iterator<String> iterator = linksSet.iterator();

            while (iterator.hasNext()) {
                // System.out.println(iterator.next());
                doGet(iterator.next());
            }
    }

    /**
     * hashSet处理api.txt去重，返回Set类型的Links
     */
    public static Set<String> getLinksSet(String phone) {
        Set<String> links = new HashSet<>();

        BufferedReader reader = null;
        String url = "";

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\JavaCode\\sms_bomber\\sms\\api.txt")));
            //一次读取一行
            while (true) {
                if ((url = reader.readLine()) != null) {
                    links.add(url.replace("%s", phone));
                } else {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    public static void doGet(String URL){
        HttpURLConnection conn = null;
        try {
            //创建远程url连接对象
            URL url = new URL(URL);
            //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //设置连接超时时间
            conn.setConnectTimeout(100);
            conn.setRequestProperty("Accept", "application/json");
            //发送请求
            conn.connect();
            //通过conn取得输入流，并使用Reader读取
            if (200 != conn.getResponseCode()) {
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        } catch (IOException e) {
            // e.printStackTrace();

        } finally {
            conn.disconnect();
        }
    }
}
