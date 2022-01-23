/* 
 * Copyright 2022 Shang Yehua <niceshang@outlook.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.thinwind.bluedream.service;

import java.util.List;

import io.github.thinwind.bluedream.dto.PagedResult;
import io.github.thinwind.bluedream.entity.Demo;

/**
 *
 * TODO DemoService说明
 *
 * @author Shang Yehua <niceshang@outlook.com>
 * @since 2022-01-23  20:42
 *
 */
public interface DemoService {
    
    long add(Demo demo);
    
    Demo findById(long id);
    
    List<Demo> findAll();
    
    /**
     * 查询分页结果
     * @param currentPage 当前页
     * @param pageSize 条数
     * @return 分页结果
     */
    PagedResult<Demo> getPagedResult(int currentPage, int pageSize);
    
    /**
     * 更新记录
     * 
     * @param demo 要更新的记录
     * @return 更新后的记录
     */
    Demo update(Demo demo);
    
    void delete(long id);
    
    /**
     * 根据一组id删除记录
     *
     * @param ids 要删除的id数组
     */
    void deleteByIds(List<Long> ids);
}
