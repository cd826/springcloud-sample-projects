/***
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.twostepsfromjava.cloud.product.api;

import io.twostepsfromjava.cloud.product.dto.ProductDto;
import io.twostepsfromjava.cloud.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ProductDto API服务
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDto> list() {
        return this.productService.findAll();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public ProductDto detail(@PathVariable String itemCode) {
        return this.productService.findOne(itemCode);
    }

    // FIXME: 该端点仅仅是用来测试消息发送，并不包含任何业务逻辑处理
    @RequestMapping(value = "/{itemCode}", method = RequestMethod.POST)
    public ProductDto save(@PathVariable String itemCode) {
        ProductDto productDto = this.productService.findOne(itemCode);
        if (null != productDto) {
            this.productService.save(productDto);
        }
        return productDto;
    }
}
