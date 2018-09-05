package com.action;

import com.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product>  {



    public String queryJoinAccount(){

        //用来存储分页的数据
        pageMap = new HashMap<String, Object>();

        //根据关键字和分页的参数查询相应的数据。这个方法我们在Service中写过了，当时完成级联查询
        List<Product> productList = productService.queryJoinProduct(model.getName(), page, rows);
        pageMap.put("rows", productList); //存储为JSON格式，从上一节的json文件可以看出，一个key是total,一个key是rows，这里先把rows存放好
        //根据关键字查询总记录数
        Long total = productService.getCount(model.getName()); //这个方法没写，我们等会儿去Service层完善一下
        pageMap.put("total", total); //存储为JSON格式，再把total存放好
        return "jsonMap";

    }

    public String deleteByIds(){

        productService.deleteByIds(ids);

        inputStream = new ByteArrayInputStream("true".getBytes());

        return "stream";
    }

    public void save() throws IOException {
        //fileUpload工具类被抽取了，uploadFile方法直接接受一  个fileImage对象，返回新的图片名
        String pic = fileUpload.uploadFile(fileImage);

        model.setPic(pic);
        model.setDate(new Date());
        System.out.println(model);
        System.out.println(productService);
        productService.save(model);

    }
    public void update() throws IOException {
        System.out.println("awdaw88888***************");
        String pic = fileUpload.uploadFile(fileImage);
        System.out.println("********");

        model.setPic(pic);
        model.setDate(new Date());
        productService.update(model);

    }
}
