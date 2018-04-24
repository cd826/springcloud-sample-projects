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
package io.twostepsfromjava.cloud.web.mall.service;


import com.netflix.hystrix.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * Product Service
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */

public class CommandUsingRequestCache extends HystrixCommand<String> {
    private String name;

    public CommandUsingRequestCache(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld")));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return this.name;
    }

    @Override
    protected String getCacheKey() {
        return this.name;
    }

    public void evictCache(String name) {
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("HelloWorld"),
                HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }
}
