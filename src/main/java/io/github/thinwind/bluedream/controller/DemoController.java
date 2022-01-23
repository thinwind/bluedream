package io.github.thinwind.bluedream.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.thinwind.bluedream.entity.Demo;
import io.github.thinwind.bluedream.service.DemoService;


/**
 *
 * demo web接口
 *
 * @author ShangYh 
 * @since 2020-09-01 15:03
 *
 */
@Validated
@RestController
@RequestMapping("/webapi/demo")
public class DemoController {

    private final DemoService service;

    @PostMapping("/")
    public Object create(@RequestBody @Validated final Demo demo) {
        demo.setId(null);
        return service.add(demo);
    }


    @GetMapping("/")
    public Object get(
            @RequestParam(required = false, defaultValue = "1") @Min(value = 1,
                    message = "当前页不能小于1") final int currentPage,
            @RequestParam(required = false, defaultValue = "10") @Min(value = 1,
                    message = "每页记录数不能小于1") final int pageSize) {
        return service.getPagedResult(currentPage, pageSize);
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable final long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable final long id,
            @RequestBody @Validated final Demo demo) {
        demo.setId(id);
        return service.update(demo);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable final int id) {
        service.delete(id);
        return "ok";
    }

    @DeleteMapping("/")
    public Object deleteByIds(@RequestParam final List<Long> ids) {
        service.deleteByIds(ids);
        return "ok";
    }

    public DemoController(final DemoService service) {
        this.service = service;
    }

}
