package Hash;

import java.util.HashMap;
import java.util.Random;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 535，当然可以直接返回传入的参数，但这样就没有意义了
 * @date 2022/8/10 11:43
 */
public class EncodeAndDecodeTinyURL {
    HashMap<String, String> map = new HashMap<>();  //shortUrl和longUrl的映射
    String charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  //随机生成的shortUrl的字符在charSet中选
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (true){
            char[] chars = new char[10];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = charSet.charAt(new Random().nextInt(62));
            }
            String shortUrl = "https://newurl.com/" + new String(chars);
            //在map中没有发生碰撞则添加映射，否则重新生成shortUrl
            if (!map.containsKey(shortUrl)){
                map.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }

    public static void main(String[] args) {
        String url = "https://leetcode.com/problems/design-tinyurl";
        EncodeAndDecodeTinyURL encodeAndDecodeTinyURL = new EncodeAndDecodeTinyURL();
        String encodeurl = encodeAndDecodeTinyURL.encode(url);
        String decodeurl = encodeAndDecodeTinyURL.decode(encodeurl);
        System.out.println(encodeurl + "\n" + decodeurl);
    }
}
