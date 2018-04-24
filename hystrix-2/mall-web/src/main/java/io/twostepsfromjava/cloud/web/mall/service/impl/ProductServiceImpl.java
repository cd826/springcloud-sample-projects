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
package io.twostepsfromjava.cloud.web.mall.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.twostepsfromjava.cloud.product.dto.Product;
import io.twostepsfromjava.cloud.web.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Product Service
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<Product> findAll() {
        return this.restTemplate.getForEntity("http://PRODUCT-SERVICE/products", List.class).getBody();
    }

    @Override
    public Product loadByItemCode(String itemCode) {
        return this.restTemplate.getForEntity("http://PRODUCT-SERVICE/products/" + itemCode, Product.class).getBody();
    }

    public List<Product> findAllFallback() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Failure-1", "固定商品-1", "Hystrix-Fallback", 100));
        products.add(new Product("Failure-2", "固定商品-2", "Hystrix-Fallback", 100));
        products.add(new Product("Failure-3", "固定商品-3", "Hystrix-Fallback", 100));
        products.add(new Product("Failure-4", "固定商品-4", "Hystrix-Fallback", 100));
        products.add(new Product("Failure-5", "固定商品-5", "Hystrix-Fallback", 100));
        products.add(new Product("Failure-6", "固定商品-6", "Hystrix-Fallback", 100));
        return products;
    }
}
