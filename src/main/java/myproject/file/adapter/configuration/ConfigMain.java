package myproject.file.adapter.configuration;

import java.io.IOException;

public class ConfigMain {

    public static void main(String[] args) {
        ConfigLoader cfl = new ConfigLoader();
        try {
            cfl.Conffile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("accesskey : " + cfl.s3_1);
        System.out.println("secretkey : " + cfl.s3_2);
        System.out.println("url : " + cfl.s3_3);
        System.out.println("home : " + cfl.s3_4);

        System.out.println("mapper : " + cfl.system1);
        System.out.println("cpu : " + cfl.system2);
        System.out.println("memory : " + cfl.system3);
        String path = cfl.system4;

        System.out.println("hdfspath : " + cfl.constant1);
        System.out.println("localpath : " + cfl.constant2);

        System.out.println("topic : " + cfl.kafka1);


    }

}
