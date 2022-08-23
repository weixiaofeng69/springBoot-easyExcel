package cn.com.easyExcel.service.impl;

import cn.com.easyExcel.excel.listener.ExportListener;
import cn.com.easyExcel.excel.listener.ImportListener;
import cn.com.easyExcel.mapper.EmployeeMapper;
import cn.com.easyExcel.pojo.Employee;
import cn.com.easyExcel.service.EmployeeService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void initData() {
        long beforeTime = System.currentTimeMillis();
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < 500000; i++) {
            Employee employee = new Employee();
            employee.setId(getUUID32());
            employee.setUserName(getRandomName());
            employee.setGender(getRandomGender());
            employee.setAge(getRandomAge());
            employee.setMaritalStatus(getRandomGender());
            employee.setEducation(getRandomEducation());
            employee.setMobile("18866998888");
            employee.setDepartmentName(getRandomDP());
            employee.setNationalArea("中国");
            employee.setCity("深圳");
            employees.add(employee);
            employeeMapper.insert(employee);
        }
        long afterTime = System.currentTimeMillis();
        log.info("耗时:{}", afterTime - beforeTime);
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        long beforeTime = System.currentTimeMillis();
        EasyExcel.read(file.getInputStream(),
                Employee.class,
                new ImportListener(employeeMapper)).sheet().headRowNumber(1).doRead();
        long afterTime = System.currentTimeMillis();
        log.info("耗时:{}", afterTime - beforeTime);
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        long beforeTime = System.currentTimeMillis();

        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
        queryWrapper.gt("age", 20);
        queryWrapper.between("education", "0", "3");

        new ExportListener<>(employeeMapper).
                exportExcel(response, "员工信息", Employee.class,
                        queryWrapper);


        long afterTime = System.currentTimeMillis();
        log.info("耗时:{}", afterTime - beforeTime);
    }

    /**
     * 随机取名字
     * @return
     */
    public String getRandomName(){
        String[] doc = {"朝歌晚酒", "都怪时光太动听", "笑我孤陋", "水墨青花","时光清浅", "草帽撸夫", "江山如画",
                "热度不够", "盏茶浅抿", "把酒临风", "且听风吟", "梦忆笙歌", "倾城月下", "清风墨竹", "自愈心暖", "几许轻唱",
                "平凡之路", "半夏倾城", "南栀倾寒", "孤君独战", "温酒杯暖", "眉目亦如画", "旧雪烹茶", "律断华章", "清酒暖风",
                "清羽墨安", "一夕夙愿", "南顾春衫", "和云相伴", "夕颜若雪", "时城旧巷", "梦屿千寻"};
        int index = (int) (Math.random() * doc.length);
        return doc[index];
    }

    /**
     * 性别随机
     * @return
     */
    public String getRandomGender(){
        String[] doc = {"0", "1"};
        int index = (int) (Math.random() * doc.length);
        return doc[index];
    }

    /**
     * 年龄随机
     * @return
     */
    public int getRandomAge(){
        int[] doc = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int index = (int) (Math.random() * doc.length);
        return doc[index];
    }

    public String getRandomEducation(){
        String[] doc = {"0", "1", "2", "3"};
        int index = (int) (Math.random() * doc.length);
        return doc[index];
    }

    public String getRandomDP(){
        String[] doc = {"行政部", "财务部", "技术部", "市场部", "公关部"};
        int index = (int) (Math.random() * doc.length);
        return doc[index];
    }
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
