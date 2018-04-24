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
package io.twostepsfromjava.cloud.product.service;

import io.twostepsfromjava.cloud.bus.ProductEvent;
import io.twostepsfromjava.cloud.product.dto.ProductDto;
import io.twostepsfromjava.cloud.product.mq.ProductMsg;
import io.twostepsfromjava.cloud.product.util.ApplicationContextHolder;
import io.twostepsfromjava.cloud.product.util.RemoteApplicationEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品服务
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@Service
public class ProductService {
    protected Logger logger = LoggerFactory.getLogger(ProductService.class);

    private List<ProductDto> productList;

    @Autowired
    public ProductService() {
        this.productList = this.buildProducts();
    }

    /**
     * 获取商品列表
     * @return
     */
    public List<ProductDto> findAll() {
        return this.productList;
    }

    /**
     * 根据ItemCode获取
     * @param itemCode
     * @return
     */
    public ProductDto findOne(String itemCode) {
        for (ProductDto productDto : this.productList) {
            if (productDto.getItemCode().equalsIgnoreCase(itemCode))
                return productDto;
        }

        return null;
    }

    /**
     * 保存或更新商品信息
     * @param productDto
     * @return
     */
    public ProductDto save(ProductDto productDto) {
        // TODO: 实现商品保存处理
        for (ProductDto sourceProductDto : this.productList) {
            if (sourceProductDto.getItemCode().equalsIgnoreCase(productDto.getItemCode())) {
                sourceProductDto.setName(sourceProductDto.getName() + "-new");
                sourceProductDto.setPrice(sourceProductDto.getPrice() + 100);
                productDto = sourceProductDto;
                break;
            }
        }

        // 发送商品消息
        // this.sendMsg(ProductMsg.MA_UPDATE, productDto.getItemCode());
        this.fireEvent(ProductEvent.ET_UPDATE, productDto);

        return productDto;
    }

    protected void sendMsg(String msgAction, String itemCode) {
        ProductMsg productMsg = new ProductMsg(msgAction, itemCode);
        this.logger.debug("发送商品消息:{} ", productMsg);

        // 发送消息
        // this.source.output().send(MessageBuilder.withPayload(productMsg).build());
    }

    protected void fireEvent(String eventAction, ProductDto productDto) {
        ProductEvent productEvent = new ProductEvent(productDto,
                ApplicationContextHolder.getApplicationContext().getId(), "*:**",
                eventAction, productDto.getItemCode());

        // 发布事件
        RemoteApplicationEventPublisher.publishEvent(productEvent);
    }

    protected List<ProductDto> buildProducts() {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto("item-1", "测试商品-1", "TwoStepsFromJava", 100));
        products.add(new ProductDto("item-2", "测试商品-2", "TwoStepsFromJava", 200));
        products.add(new ProductDto("item-3", "测试商品-3", "TwoStepsFromJava", 300));
        products.add(new ProductDto("item-4", "测试商品-4", "TwoStepsFromJava", 400));
        products.add(new ProductDto("item-5", "测试商品-5", "TwoStepsFromJava", 500));
        products.add(new ProductDto("item-6", "测试商品-6", "TwoStepsFromJava", 600));
        return products;
    }
}
