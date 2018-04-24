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
package io.twostepsfromjava.cloud.product.entity;

import com.google.common.base.MoreObjects;

/**
 * 商品信息实体对象
 *
 * @author CD826(CD826Dong@gamil.com)
 * @since 1.0.0
 */
public class Product {
    private static final long serialVersionUID = 1L;

    // ========================================================================
    // fields =================================================================
    private String itemCode;                                    // 商品货号
    private String name;                                        // 商品名称
    private String bandName;                                    // 商品品牌名称
    private int price;                                          // 商品价格(分)

    // ========================================================================
    // constructor ============================================================
    public Product() {
    }

    public Product(String itemCode, String name, String bandName, int price) {
        this.itemCode = itemCode;
        this.name = name;
        this.bandName = bandName;
        this.price = price;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("itemCode", this.getItemCode())
                .add("name", this.getName())
                .add("bandName", this.getBandName())
                .add("price", this.getPrice()).toString();
    }

    // ==================================================================
    // setter/getter ====================================================
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBandName() {
        return bandName;
    }
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
