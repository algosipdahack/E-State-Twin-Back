package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.dao.BrokerDAO;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import com.example.Estate_Twin.user.web.dto.BrokerListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
@Component
@AllArgsConstructor
public class BrokerDAOImpl implements BrokerDAO {
    BrokerRepository brokerRepository;
    EstateRepository estateRepository;
    UserRepository userRepository;

    @Override
    public Broker findBrokerByEmail(String email) {
        return brokerRepository.findByUserEmailWithUserUsingJoin(email).orElseThrow(()-> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
    }
    @Override
    public Broker findBrokerFetchJoinById(Long id) {
        return brokerRepository.findByIdUsingFetchJoin(id).orElseThrow(() -> new CheckHouseException(ErrorCode.BROKER_NOT_FOUND));
    }

    @Override
    public Broker findBrokerById(Long id) {
        return brokerRepository.findById(id).orElseThrow(()-> new CheckHouseException(ErrorCode.BROKER_NOT_FOUND));
    }

    @Override
    public Broker findBrokerByUserId(Long userId) {
        return brokerRepository.findBrokerByUser_Id(userId).orElseThrow(()-> new CheckHouseException(ErrorCode.BROKER_NOT_FOUND));
    }


    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerRepository.getBrokerList();
    }

    @Override
    public List<BrokerEstateDto> getBrokerEstate(Long brokerId, State state) {
        return estateRepository.findEstateBybrokerIdAndState(brokerId,state);
    }

    //broker 회원가입
    @Override
    @Transactional
    public Broker signUp(Broker broker, User user) {
        //broker로 설정
        user.setIsBroker();
        userRepository.save(user);
        broker.setUser(user);
        return brokerRepository.save(broker);
    }
}
