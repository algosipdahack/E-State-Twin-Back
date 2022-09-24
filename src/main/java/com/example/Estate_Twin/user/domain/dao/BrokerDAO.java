package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.entity.*;

public interface BrokerDAO {
    Broker findBrokerByEmail(String Email);
    Broker signUp(Broker broker, String brokerLicense, String businessLicense, Media brokerPhoto, User user);
}
