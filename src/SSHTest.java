import com.model.Account;
import com.model.Category;
import com.service.CategoryService;
import com.service.impl.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description TODO(采用Spring的注解调试，仅仅支持Spring3.1及以上)
 * @author Ni Shengwu
 *
 */
/*
 * Spring3.1后多了个spring-test-4.2.4.RELEASE.jar包，这个jar包专门用来支持JUnit基于注解的测试的，该jar包在spring-4.2.4-core中
 * 该jar包里有个SpringJUnit4ClassRunner.class，用@RunWith注解加进来即可
 *
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class SSHTest {

    @Resource
    private Date date;
    @Resource
    private CategoryService categoryService;


    @Test //测试Spring IOC的开发环境
    public void springIoc() {
        System.out.println(date);
    }
    @Test //测试Hibernate的开发环境，因为没有整合，可以直接new
    public void hibernate(){
        CategoryService categoryService = new CategoryServiceImpl();

        categoryService.save(new Category());
    }
    @Test //测试Hibernate和Spring整合后
    public  void SpringAndHibernate(){

        categoryService.update(new Category(5,"awd阿瓦打我",(byte)12,new Account()));
    }

    @Test //测试分页查询
    public void  testQueryJoinAccount(){
        for (Category c: categoryService.queryJoinAccount("",2,2)){
            System.out.println(c);
            System.out.println(c.getAccount());
        }
    }
    @Test //测试文件名修改
    public void testFileUpload(){
//        FileUploadUtil fileUploadUtil = new FileUploadUtil();
//        FileImage fileImage = new FileImage();
//        File file = new File("c:\\yibao - 副本.jpg");
//        fileImage.setFile(file);
//        fileUploadUtil.uploadFile(fileImage);
    }


}
