package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.dao.BrokerDAO;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrokerDAOImpl implements BrokerDAO {
    BrokerRepository brokerRepository;
    UserRepository userRepository;
    @Override
    public Broker findBrokerByEmail(String email) {
        return brokerRepository.findByUserEmailWithUserUsingJoin(email).orElseThrow(()-> new IllegalArgumentException("해당 email를 가진 user가 존재하지 않습니다. email = "+email));
    }

    //broker 회원가입
    @Override
    public Broker signUp(Broker broker, String brokerLicense, String businessLicense, Media brokerPhoto, User user) {
        //broker로 설정
        user.setIsBroker();
        userRepository.save(user);

        broker.setString(brokerLicense,businessLicense);
        broker.setUser(user);
        broker.setPhoto(brokerPhoto);

        return brokerRepository.save(broker);
    }
}
