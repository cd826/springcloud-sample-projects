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
package io.twostepsfromjava.cloud.web.mall.mq;


import io.twostepsfromjava.cloud.web.mall.dto.ProductDto;
import io.twostepsfromjava.cloud.web.mall.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 商品消息监听器
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
public class ProductMsgListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ProductService productService;

    public void onProductMsgSink(ProductMsg productMsg) {
        if (ProductMsg.MA_UPDATE.equalsIgnoreCase(productMsg.getAction())) {
            this.logger.debug("收到商品变更消息，商品货号: {}", productMsg.getItemCode());
            // 重新获取该商品信息
            ProductDto productDto = this.productService.loadByItemCode(productMsg.getItemCode());
            if (null != productDto)
                this.logger.debug("重新获取到的商品信息为:{}", productDto);
            else
                this.logger.debug("货号为:{} 的商品不存在", productMsg.getItemCode());
        } else if (ProductMsg.MA_DELETE.equalsIgnoreCase(productMsg.getAction())) {
            this.logger.debug("收到商品删除消息，所要删除商品货号为: {}", productMsg.getItemCode());
        } else {
            this.logger.debug("收到未知商品消息: {}", productMsg);
        }
    }
}
