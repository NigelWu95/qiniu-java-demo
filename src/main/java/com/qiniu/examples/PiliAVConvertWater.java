package com.qiniu.examples;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.*;

public class PiliAVConvertWater {

    public static void main(String[] args) {

        Config config = Config.getInstance();
        String accessKey = config.getAccesskey();
        String secretKey = config.getSecretKey();
        Auth auth = Auth.create(accessKey, secretKey);

        CreateWatertemplate(auth);
        QueryWatertemplate(auth);
        ListWatertemplate(auth);

    }

    public static void CreateWatertemplate(Auth auth) {

        String hub = "nigel-pili";
        String watermarktemplateName = "test";
        String comment = "<Comment>";
        String left = "10%";
        String top = "10%";
        String width = "10%";
        String imageURL = "https://odum9helk.qnssl.com/4c0aa36a9aafb42adeb9e9c173c62a13?imageView2/2/format/png";
        String imageData = "imageData"; // 与 imageURL 二选一

        StringMap stringMap = new StringMap();
        stringMap.put("name", watermarktemplateName);
        stringMap.put("comment", comment);
        stringMap.put("left", left);
        stringMap.put("top", top);
        stringMap.put("width", width);
        stringMap.put("imageURL", imageURL);

        String url = "http://pili.qiniuapi.com/v2/hubs/" + hub + "/watermarktemplate";
        String contentType = "application/json";

        String qiniuToken = "Qiniu " + auth.signRequestV2(url, "POST", StringUtils.utf8Bytes(Json.encode(stringMap)), contentType);
        StringMap headers = new StringMap().put("Authorization", qiniuToken);
        headers.put("Content-Type", contentType);

        Client client = new Client();
        Response response = null;
        try {
            response = client.post(url, StringUtils.utf8Bytes(Json.encode(stringMap)), headers, contentType);
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        response.close();
    }

    public static void QueryWatertemplate(Auth auth) {

        String hub = "nigel-pili";
        String watermarktemplate = "test";

        String url = "http://pili.qiniuapi.com/v2/hubs/" + hub + "/watermarktemplate/" + watermarktemplate;
        String contentType = "application/json";

        String qiniuToken = "Qiniu " + auth.signRequestV2(url, "GET", null, contentType);
        StringMap headers = new StringMap().put("Authorization", qiniuToken);
        headers.put("Content-Type", contentType);

        Client client = new Client();
        Response response = null;
        try {
            response = client.get(url, headers);
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        response.close();
    }

    public static void ListWatertemplate(Auth auth) {

        String hub = "nigel-pili";
        String watermarktemplate = "test";

        String url = "http://pili.qiniuapi.com/v2/hubs/" + hub + "/watermarktemplate/" + watermarktemplate + "/image";
        String contentType = "application/json";

        String qiniuToken = "Qiniu " + auth.signRequestV2(url, "GET", null, contentType);
        StringMap headers = new StringMap().put("Authorization", qiniuToken);
        headers.put("Content-Type", contentType);

        Client client = new Client();
        Response response = null;
        try {
            response = client.get(url, headers);
            System.out.println(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }

        response.close();
    }
}