package ${package_name}.controller;

import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.model.${table_name};
import ${package_name}.model.dto.${table_name}DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/bak/${table_name?uncap_first}")
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
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建${table_annotation}
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(${table_name?uncap_first}Service.create${table_name}(${table_name?uncap_first}DTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除${table_annotation}
    * @param id ${table_annotation}id
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteById(@RequestParam String id) throws Exception {
        try {
            ResultData resultData = new ResultData();
            ${table_name?uncap_first}Service.deleteById(id);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：更新${table_annotation}
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData update${table_name}(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(${table_name?uncap_first}Service.update${table_name}(${table_name?uncap_first}DTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除${table_annotation}
    * @param ${table_name?uncap_first}DTO ${table_annotation}ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            ${table_name?uncap_first}Service.deleteByIds(${table_name?uncap_first}DTO.getIds());
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = "/get${table_name}List", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData get${table_name}List(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<${table_name}DTO> list = ${table_name?uncap_first}Service.get${table_name}List(map);
        PageInfo<${table_name}DTO> pageInfo = new PageInfo<CouponDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}