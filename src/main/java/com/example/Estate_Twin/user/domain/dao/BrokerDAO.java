package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.user.domain.entity.*;

public interface BrokerDAO {
    Broker findBrokerByEmail(String Email);
    Broker signUp(Broker broker, User user);
}
