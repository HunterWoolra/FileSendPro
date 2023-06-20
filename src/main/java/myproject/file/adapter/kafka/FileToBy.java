package myproject.file.adapter.kafka;

import java.io.*;
import java.util.Base64;

public class FileToBy {
    public static String fileToBinary(File file) {
        String out = new String();
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            fis = new FileInputStream(file);

        }catch (FileNotFoundException e){
            System.out.println("Exception position : FileUtil - fileToString");
        }

        int len = 0;
        byte[] buf = new byte[1024];
        try{
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] fileArray = baos.toByteArray();
            out = new String(base64Enc(fileArray));

            fis.close();
            baos.close();
        }catch (IOException e) {
            System.out.println("IOException");
        }


        return out;
    }

    public static byte[] base64Enc(byte[] buffer) {
        return Base64.getEncoder().encode(buffer);
    }

}
