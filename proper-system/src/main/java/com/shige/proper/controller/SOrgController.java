package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SOrg;
import com.shige.proper.service.SOrgService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/system/sOrg")
public class SOrgController extends BaseController{
    @Autowired
    private SOrgService orgService;

    @GetMapping("/listOrg")
    public Result listOrg(@RequestHeader(ShigeConstant.TOKEN) String token){
        return ResultUtil.success(orgService.listOrg(token));
    }

    @GetMapping("/{id}")
    public Result getOrg(@PathVariable("id") Long id){
        return ResultUtil.success(orgService.getById(id));
    }

    @GetMapping("/getChildOrg")
    public Result getChildOrg(Long id){
        return ResultUtil.success(orgService.getById(id));
    }

    @PostMapping("/insertOrg")
    public Result insertOrg(@RequestHeader(ShigeConstant.TOKEN) String token, SOrg org){
        return ResultUtil.success(orgService.save(org,token));
    }

    @PutMapping("/updateOrg")
    public Result updateOrg(@RequestHeader(ShigeConstant.TOKEN) String token,SOrg org){
        return ResultUtil.success(orgService.saveOrUpdate(org,token));
    }
    @DeleteMapping("/{id}")
    public Result deleteOrg(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token){
        return ResultUtil.success(orgService.removeById(id,token));
    }

    @GetMapping("/getBaseOrg")
    public Result getBaseOrg(Long compId){
        return ResultUtil.success(orgService.getBaseOrg(compId));
    }

    @GetMapping("/getOnlyChildOrg")
    public Result getOnlyChildOrg(Long id){
        return ResultUtil.success(orgService.getOnlyChildOrg(id));
    }
}

