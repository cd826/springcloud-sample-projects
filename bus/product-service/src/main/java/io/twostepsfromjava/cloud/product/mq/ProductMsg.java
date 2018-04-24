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
package io.twostepsfromjava.cloud.product.mq;


import com.google.common.base.MoreObjects;

/**
 * 商品消息
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
public class ProductMsg {
    /** 消息类型：更新商品，值为: {@value} */
    public static final String MA_UPDATE = "update";
    /** 消息类型：删除商品，值为: {@value} */
    public static final String MA_DELETE = "delete";

    // ========================================================================
    // fields =================================================================
    private String action;
    private String itemCode;

    // ========================================================================
    // constructor ============================================================
    public ProductMsg() {  }

    public ProductMsg(String action, String itemCode) {
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
