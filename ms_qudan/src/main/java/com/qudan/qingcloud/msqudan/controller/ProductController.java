package com.qudan.qingcloud.msqudan.controller;

import com.qudan.qingcloud.msqudan.service.Impl.ProductServiceImpl;
import com.qudan.qingcloud.msqudan.util.ComUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class ProductController {

    @Autowired
    public ProductServiceImpl productService;

    @GetMapping("/products/hot")
    public ResponseEntity<Map<String, Object>> hots(@RequestParam("type") Integer type) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(productService.hotProducts(ARE, type));
        return ARE.createResponseEntity();
    }

    @GetMapping("/products/")
    public ResponseEntity<Map<String, Object>> list(@RequestParam(value = "type", required = false) Integer type,
                                                    @RequestParam(value = "keyword", required = false)String keyword,
                                                    HttpServletRequest request
                                                    ) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)){
            ARE.setData(productService.products(ARE, type, keyword, ComUtils.getPage(request), ComUtils.getPerPage(request)));
        }
        return ARE.createResponseEntity();
    }

    @GetMapping("/product/${id}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable("id")Integer id,
                                                    HttpServletRequest request
    ) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        if(ComUtils.validPage(ARE, request)){
            ARE.setData(productService.productDetail(ARE, id));
        }
        return ARE.createResponseEntity();
    }
}
