package cn.com.easyExcel.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("employee")
public class Employee {

    @TableId
    private Long id;

    private String userName;

    private String gender;
    private int age;

    private String birthday;

    private String maritalStatus;

    private String education;

    private String bloodType;
    private String mobile;

    private String departmentName;

    private String nationalArea;

    private String province;
    private String city;
    private String idCardNumber;
    private String personalMailBox;

}
