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
package io.twostepsfromjava.cloud.user;

import com.google.common.base.MoreObjects;

/**
 * 用户信息DTO对象
 *
 * @author CD826(CD826Dong@gamil.com)
 * @since 1.0.0
 */
public class User {
    private static final long serialVersionUID = 1L;
    // ========================================================================
    // fields =================================================================
    private String loginName;                                   // 用户登陆名称
    private String name;                                        // 用户姓名
    private String avatar;                                      // 用户头像
    private String memos;                                       // 信息备注

    // ========================================================================
    // constructor ============================================================
    public User() {
    }

    public User(String loginName, String name, String avatar, String memos) {
        this.loginName = loginName;
        this.name = name;
        this.avatar = avatar;
        this.memos = memos;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("loginName", this.getLoginName())
                .add("name", this.getName())
                .add("avatar", this.getAvatar())
                .add("memos", this.getMemos()).toString();
    }

    // ==================================================================
    // setter/getter ====================================================
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMemos() {
        return memos;
    }
    public void setMemos(String memos) {
        this.memos = memos;
    }
}
