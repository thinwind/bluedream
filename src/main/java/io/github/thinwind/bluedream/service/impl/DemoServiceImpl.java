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
package io.github.thinwind.bluedream.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.thinwind.bluedream.dto.PagedResult;
import io.github.thinwind.bluedream.entity.Demo;
import io.github.thinwind.bluedream.exception.BlueDreamException;
import io.github.thinwind.bluedream.exception.RecordNotFoundException;
import io.github.thinwind.bluedream.misc.Errors;
import io.github.thinwind.bluedream.repo.DemoRepo;
import io.github.thinwind.bluedream.service.DemoService;

/**
 *
 * TODO DemoServiceImpl说明
 *
 * @author Shang Yehua <niceshang@outlook.com>
 * @since 2022-01-23  20:52
 *
 */
@Service
public class DemoServiceImpl implements DemoService {
    
    @Autowired
    private DemoRepo demoRepo;

    @Override
    public long add(Demo demo) {
        demoRepo.save(demo);
        return demo.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Demo findById(long id) {
        return demoRepo.findById(id).orElseThrow(() -> new RecordNotFoundException(
            Errors.RECORD_NOT_FOUND.getErrorCode(), "id为" + id + "的记录未找到"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Demo> findAll() {
        return demoRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PagedResult<Demo> getPagedResult(int currentPage, int pageSize) {
        Pageable pageable =
                PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Demo> resultPage = demoRepo.findAll(pageable);
        var result = new PagedResult<Demo>();
        result.setContent(resultPage.getContent());
        result.setCurrentPage(currentPage);
        result.setPageSize(pageSize);
        result.setTotal((int) resultPage.getTotalElements());
        return result;
    }

    @Override
    public Demo update(Demo demo) {
        if (demoRepo.existsById(demo.getId())) {
            return demoRepo.save(demo);
        } else {
            throw new BlueDreamException("id为" + demo.getId() + "的记录不存在");
        }
    }

    @Override
    public void delete(long id) {
        demoRepo.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        demoRepo.deleteAllById(ids);
    }
    
}
