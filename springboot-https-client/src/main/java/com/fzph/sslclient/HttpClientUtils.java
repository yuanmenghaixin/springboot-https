package com.fzph.sslclient;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Http请求Utils
 */
public class HttpClientUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        InputStream inputStream = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            inputStream = connection.getInputStream();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (StringUtils.isNoneBlank(result) && 2001 < result.length()) {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result.substring(0, 200));
            } else {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result);
            }
        } catch (Exception e) {
            System.out.println("路径:" + url + "参数" + param + "{发送GET请求出现异常}:" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                System.out.println("路径:" + url + "参数" + param + "{发送GET请求出现异常}" + e2);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//      conn.setRequestProperty("Content-Type", "application/json;charset=UTF8");
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            outputStream = conn.getOutputStream();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(outputStream, "UTF-8");      // 发送请求参数
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            inputStream = conn.getInputStream();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (StringUtils.isNoneBlank(result) && 2001 < result.length()) {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result.substring(0, 2000));
            } else {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result);
            }
        } catch (Exception e) {
            System.out.println("路径:" + url + "参数" + param + "{发送 POST 请求出现异常}" + e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String contentType) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setConnectTimeout(90000);
            conn.setReadTimeout(90000);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            outputStream = conn.getOutputStream();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(outputStream, "UTF-8");      // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            inputStream = conn.getInputStream();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (StringUtils.isNoneBlank(result) && 2001 < result.length()) {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result.substring(0, 2000));
            } else {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result);
            }
        } catch (Exception e) {
            System.out.println("路径:" + url + "参数" + param + "{发送 POST 请求出现异常}" + e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("{}" + ex);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, Map<String, String> contents) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            for (Map.Entry<String, String> entry : contents.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setConnectTimeout(360000);
            conn.setReadTimeout(360000);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            outputStream = conn.getOutputStream();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(outputStream, "UTF-8");      // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            inputStream = conn.getInputStream();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (StringUtils.isNoneBlank(result) && 2001 < result.length()) {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result.substring(0, 2000));
            } else {
                System.out.println("路径:" + url + "参数" + param + "返回结果:" + result);
            }
        } catch (Exception e) {
            System.out.println("路径:" + url + "参数" + param + "{发送 POST 请求出现异常}" + e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("{}" + ex);
            }
        }
        return result;
    }
}
