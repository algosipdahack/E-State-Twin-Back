package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.user.domain.dao.BrokerDAO;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrokerDAOImpl implements BrokerDAO {
    BrokerRepository brokerRepository;

    @Override
    public Broker findBroker(Long brokerId) {
        return brokerRepository.findById(brokerId).orElseThrow(()-> new IllegalArgumentException("해당 id를 가진 broker가 존재하지 않습니다. id = "+brokerId));
    }

    //broker 회원가입
    @Override
    public Broker signUp(Broker broker, User user) {
        broker.setUser(user);
        return brokerRepository.save(broker);
    }
}
