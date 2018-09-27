package com.sckj.service;
 
import com.sckj.model.dto.PersonalInformationDTO;

import java.util.List;
 
public interface IUserService {
    List findAllUser();

    PersonalInformationDTO getPersonalInformation(String userId,String cartType) throws Exception;
}
