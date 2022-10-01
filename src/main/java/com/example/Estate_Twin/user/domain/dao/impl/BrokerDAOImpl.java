package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.user.domain.dao.BrokerDAO;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import com.example.Estate_Twin.user.web.dto.BrokerListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Component
@AllArgsConstructor
public class BrokerDAOImpl implements BrokerDAO {
    BrokerRepository brokerRepository;
    UserRepository userRepository;
    @Override
    public Broker findBrokerByEmail(String email) {
        return brokerRepository.findByUserEmailWithUserUsingJoin(email).orElseThrow(()-> new IllegalArgumentException("해당 email를 가진 user가 존재하지 않습니다. email = "+email));
    }
    @Override
    public Broker findBrokerById(Long id) {
        return brokerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 broker가 존재하지 않습니다. id = " + id));
    }

    @Override
    public List<BrokerListDto> getBrokerList() {
        return brokerRepository.getBrokerList();
    }

    /*@Override
    public List<BrokerEstateDto> getBrokerEstate(Long brokerId, State state) {
        return brokerRepository.findAllWithEstateByState(brokerId,state);
    }*/


    //broker 회원가입
    @Override
    public Broker signUp(Broker broker, User user, Address address) {
        //broker로 설정
        user.setIsBroker();
        broker.setUser(user);
        broker.setAddress(address);
        return brokerRepository.save(broker);
    }
}
