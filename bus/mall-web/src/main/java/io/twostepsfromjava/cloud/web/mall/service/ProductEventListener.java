/*
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
package io.twostepsfromjava.cloud.web.mall.service;

import io.twostepsfromjava.cloud.bus.ProductEvent;
import io.twostepsfromjava.cloud.web.mall.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * 远程事件监听
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@Component
public class ProductEventListener implements ApplicationListener<ProductEvent> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ProductService productService;

    @Override
    public void onApplicationEvent(ProductEvent productEvent) {
        if (ProductEvent.ET_UPDATE.equalsIgnoreCase(productEvent.getAction())) {
            this.logger.debug("Web微服务收到商品变更事件，商品货号: {}", productEvent.getItemCode());
            // 重新获取该商品信息  
            ProductDto productDto = this.productService.loadByItemCode(productEvent.getItemCode());
            if (null != productDto)
                this.logger.debug("重新获取到的商品信息为:{}", productDto);
            else
                this.logger.debug("货号为:{} 的商品不存在", productEvent.getItemCode());
        } else if (ProductEvent.ET_DELETE.equalsIgnoreCase(productEvent.getAction())) {
            this.logger.debug("Web微服务收到商品删除事件，所要删除商品货号为: {}", productEvent.getItemCode());
        } else {
            this.logger.debug("Web微服务收到未知商品事件: {}", productEvent);
        }
    }
}
