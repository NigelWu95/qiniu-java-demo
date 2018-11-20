package com.qiniu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class HashCheckUtil {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        System.out.println(isEqualWithHash("/Users/wubingheng/Downloads/paper-hor-1.jpg",
                "01105c5436820997b0588ef323c83ca326d79805"));
        System.out.println(isEqualWithHash("/Users/wubingheng/Downloads/parsed-3.jpg",
                "3edc2d8fd7d1c88e109c6fe64fb70a72ea1ab80c"));
        try {
            System.out.println(isEqualWithHash(new File("/Users/wubingheng/Downloads/result1.jpg"),
                    "71fe3926b6c47a2c96e8130da96783a25cc570d6"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static boolean isEqualWithHash(String filePath, String fileSha1sum) throws IOException, NoSuchAlgorithmException {
        File file = new File(filePath);
        return isEqualWithHash(file, fileSha1sum);
    }

    public static boolean isEqualWithHash(File file, String fileSha1sum) throws IOException, NoSuchAlgorithmException {

        if (!(file.exists() && file.isFile() && file.canRead())) {
            throw new IOException("Error: no such file");
        }
        long fileLength = file.length();
        FileInputStream inputStream = new FileInputStream(file);
        byte[] hashDataSha1;
        if (fileLength <= QiniuEtagUtil.CHUNK_SIZE) {
            hashDataSha1 = QiniuEtagUtil.lessThan4mHash(inputStream);
        } else {
            hashDataSha1 = QiniuEtagUtil.greaterThan4mHash(inputStream, fileLength);
        }

        inputStream.close();
        String calculatedSha1 = CharactersUtil.bytesToHexString(hashDataSha1);
        return fileSha1sum.equals(calculatedSha1);
    }
}