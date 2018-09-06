package com.qiniu.examples;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class PfopVframe {

    public static void main(String[] args) throws QiniuException {
        Config config = Config.getInstance();

        //设置好账号的ACCESS_KEY和SECRET_KEY
        String ACCESS_KEY = config.getAccesskey();
        String SECRET_KEY = config.getSecretKey();
        //要上传的空间
        String bucketname = config.getFirstBucketName();

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        //新建一个OperationManager对象
        OperationManager operater = null;//new OperationManager(auth);

        //设置要转码的空间和key，并且这个key在你空间中存在
        String bucket = "Bucket_Name";
        String key = "Bucket_key";
        //设置转码操作参数
        String fops = "vframe/jpg/offset/1/w/480/h/360/rotate/90";
        //设置转码的队列
        String pipeline = "yourpipelinename";
        //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        String urlbase64 = UrlSafeBase64.encodeToString("目标Bucket_Name:自定义文件key");
        String pfops = fops + "|saveas/" + urlbase64;
        //设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
        try {
            String persistid = operater.pfop(bucket, key, pfops, params);
            //打印返回的persistid
            System.out.println(persistid);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}