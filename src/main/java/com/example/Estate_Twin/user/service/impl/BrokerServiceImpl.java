package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.media.service.AwsS3Service;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveRequestDto;
import com.example.Estate_Twin.user.domain.dao.*;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.BrokerService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {
    private final BrokerDAO brokerDAO;
    private final UserDAO userDAO;
    private final AwsS3Service awsS3Service;
    @Override
    public BrokerResponseDto getBroker(String userEmail) {
        return new BrokerResponseDto(brokerDAO.findBrokerByEmail(userEmail));
    }

    @Override
    public BrokerResponseDto signUpBroker(String userEmail, BrokerSignUpDto brokerSignUpDto) {
        String brokerLicense = awsS3Service.multipartTostring(brokerSignUpDto.getBrokerageRegistrationLicense()).getFilePath();
        String businessLicense = awsS3Service.multipartTostring(brokerSignUpDto.getBusinessLicense()).getFilePath();
        MediaSaveRequestDto brokerPhoto = awsS3Service.multipartTostring(brokerSignUpDto.getBrokerPhoto());

        User user = userDAO.findUserByEmail(userEmail);
        return new BrokerResponseDto(brokerDAO.signUp(brokerSignUpDto.toEntity(),brokerLicense,businessLicense,brokerPhoto.toEntity(), user));
    }
}
