package ${package_name}.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.CouponDTO;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.model.${table_name};
import ${package_name}.model.dto.${table_name}DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

import java.util.List;
import java.util.Map;

/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${table_name?uncap_first}")
public class ${table_name}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${table_name}Controller.class);

    @Autowired
    private I${table_name}Service ${table_name?uncap_first}Service;

    /**
    * 描述：根据Id 查询
    * @param id  ${table_annotation}id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}Service.findDTOById(id);
            resultData.setData(${table_name?uncap_first}DTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            throw e;
        }
    }

    /**
    * 描述:创建或更新${table_annotation}
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(${table_name?uncap_first}Service.createOrUpdate${table_name}(${table_name?uncap_first}DTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            throw e;
        }
    }

    /**
    * 描述：删除${table_annotation}
    * @param ids ${table_annotation}ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            ${table_name?uncap_first}Service.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            throw e;
        }
    }

    /**
    * 描述：分页查询${table_annotation}
    * @param
    */
    @RequestMapping(value = "/get${table_name}List", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData get${table_name}List(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<${table_name}DTO> list = ${table_name?uncap_first}Service.get${table_name}List(map);
        PageInfo<${table_name}DTO> pageInfo = new PageInfo<${table_name}DTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}