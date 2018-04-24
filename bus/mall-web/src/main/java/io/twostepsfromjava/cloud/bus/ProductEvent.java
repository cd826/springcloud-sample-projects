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
package io.twostepsfromjava.cloud.bus;


import com.google.common.base.MoreObjects;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 商品事件
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
public class ProductEvent extends RemoteApplicationEvent {
    /** 消息类型：更新商品，值为: {@value} */
    public static final String ET_UPDATE = "update";
    /** 消息类型：删除商品，值为: {@value} */
    public static final String ET_DELETE = "delete";

    // ========================================================================
    // fields =================================================================
    private String action;
    private String itemCode;

    // ========================================================================
    // constructor ============================================================
    public ProductEvent() {
        super();
    }

    public ProductEvent(Object source, String originService, String destinationService, String action, String itemCode) {
        super(source, originService, destinationService);
        this.action = action;
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("itemCode", this.getItemCode()).toString();
    }

    // ==================================================================
    // setter/getter ====================================================
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
