package cn.com.easyExcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan({"cn.com.easyExcel.mapper"})
@SpringBootApplication
public class easyExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(easyExcelApplication.class, args);
    }
}
